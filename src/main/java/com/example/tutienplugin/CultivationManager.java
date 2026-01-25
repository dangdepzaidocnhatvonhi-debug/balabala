package com.example.tutienplugin;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CultivationManager implements Listener {

    private final TuTienPlugin plugin;
    private final Map<UUID, PlayerData> playerDataMap;
    private final Map<UUID, Location> tuLuyenLocations = new HashMap<>();

    public CultivationManager(TuTienPlugin plugin) {
        this.plugin = plugin;
        this.playerDataMap = new HashMap<>();
        startTuLuyenTask();
        startLinhLucRegenTask();
        startAutoSaveTask();
    }

    public PlayerData getPlayerData(Player player) {
        PlayerData data = playerDataMap.computeIfAbsent(
                player.getUniqueId(),
                k -> PlayerData.load(plugin.getDataFolder(), player)
        );

        // ✅ Thêm dòng này
        data.autoBreakthrough();

        return data;
    }

    public void savePlayerData(Player player) {
        PlayerData data = playerDataMap.get(player.getUniqueId());
        if (data != null) {
            data.save(plugin.getDataFolder());
        }
    }

    public void removePlayerData(Player player) {
        savePlayerData(player);
        playerDataMap.remove(player.getUniqueId());
    }

    // Task tu luyện - mỗi 5 giây
    private void startTuLuyenTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    PlayerData data = getPlayerData(player);

                    if (data.isDangTuLuyen()) {
                        // Tính tu vi nhận được
                        long gain = 10 + (data.getCanhGioi().getLevel() * 5);
                        CanhGioi oldCanhGioi = data.getCanhGioi();
                        data.addTuVi(gain);
                        CanhGioi newCanhGioi = data.getCanhGioi();

                        // Check đột phá
                        if (newCanhGioi.getLevel() > oldCanhGioi.getLevel()) {
                            plugin.getServer().broadcastMessage("");
                            plugin.getServer().broadcastMessage("§6✨ ĐỘT PHÁ! ✨");
                            plugin.getServer().broadcastMessage("§e" + player.getName() + " §fđã đột phá đến");
                            plugin.getServer().broadcastMessage("§d" + newCanhGioi.getName() + "!");
                            plugin.getServer().broadcastMessage("");
                            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 0.5f, 1f);
                        }

                        // Hiệu ứng và thông báo
                        player.spawnParticle(Particle.END_ROD,
                                player.getLocation().add(0, 1, 0),
                                10, 0.5, 0.5, 0.5, 0.02);
                        // Sử dụng title thay cho action bar
                        String message = "§b⚡ Tu luyện... §e+" + gain + " tu vi §7| §d" +
                                data.getTuVi() + "/" + data.getCanhGioi().getNextExp();
                        player.sendTitle("", message, 0, 40, 10);
                    }
                }
            }
        }.runTaskTimer(plugin, 100L, 100L); // 5 giây
    }

    // Task hồi phục linh lực - mỗi 3 giây
    private void startLinhLucRegenTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    PlayerData data = getPlayerData(player);
                    double regenAmount = 5 + (data.getCanhGioi().getLevel() * 2);
                    data.regenLinhLuc(regenAmount);
                }
            }
        }.runTaskTimer(plugin, 60L, 60L); // 3 giây
    }

    // Task tự động lưu dữ liệu - mỗi 5 phút
    private void startAutoSaveTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    savePlayerData(player);
                }
            }
        }.runTaskTimer(plugin, 6000L, 6000L); // 5 phút (6000 ticks)
    }

    // Bắt đầu tu luyện
    public void startTuLuyen(Player player) {
        PlayerData data = getPlayerData(player);

        if (data.isDangTuLuyen()) {
            player.sendMessage("§c❌ Đang tu luyện rồi!");
            return;
        }

        // Spawn ghế tu luyện (Armor Stand)
        Location loc = player.getLocation();
        ArmorStand chair = loc.getWorld().spawn(loc.clone().subtract(0, 0.5, 0), ArmorStand.class);
        chair.setVisible(false);
        chair.setGravity(false);
        chair.setInvulnerable(true);
        chair.setSmall(true);
        chair.addPassenger(player);

        // Lưu vị trí và trạng thái
        tuLuyenLocations.put(player.getUniqueId(), loc);
        data.setDangTuLuyen(true);

        // Hiệu ứng
        player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
        player.sendMessage("§a✨ Bắt đầu tu luyện!");

        // Particle effect
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline() || !data.isDangTuLuyen()) {
                    this.cancel();
                    return;
                }

                Location particleLoc = player.getLocation().add(0, 0.5, 0);
                player.getWorld().spawnParticle(Particle.END_ROD, particleLoc, 10, 0.3, 0.3, 0.3, 0.02);
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    // Dừng tu luyện
    public void stopTuLuyen(Player player) {
        PlayerData data = getPlayerData(player);

        if (!data.isDangTuLuyen()) {
            player.sendMessage("§c❌ Có tu luyện đâu mà dừng!");
            return;
        }

        // Xóa ghế và đứng dậy
        if (player.getVehicle() != null && player.getVehicle() instanceof ArmorStand) {
            Entity vehicle = player.getVehicle();
            player.leaveVehicle();
            vehicle.remove();
        }

        // Reset trạng thái
        data.setDangTuLuyen(false);
        tuLuyenLocations.remove(player.getUniqueId());

        // Hiệu ứng
        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1f, 1f);
        player.sendMessage("§e🛑 Đã dừng tu luyện!");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        PlayerData data = getPlayerData(player);
        if (data.isDangTuLuyen()) {
            Location from = e.getFrom();
            Location to = e.getTo();
            if (to != null && (from.getX() != to.getX() || from.getZ() != to.getZ())) {
                stopTuLuyen(player);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        stopTuLuyen(e.getPlayer());
        savePlayerData(e.getPlayer());
        removePlayerData(e.getPlayer());
    }

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent e) {
        for (Entity entity : e.getChunk().getEntities()) {
            if (entity instanceof ArmorStand && ((ArmorStand) entity).isInvisible()) {
                entity.remove();
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (getPlayerData(player).isDangTuLuyen()) {
                stopTuLuyen(player);
            }
        }
    }
}