package com.example.tutienplugin;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;

public class ThienLoi {
    private final TuTienPlugin plugin;
    private final Map<UUID, Location> dangKiep = new HashMap<>();
    private final int PROTECTED_RADIUS = 20;

    public ThienLoi(TuTienPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Check xem có cần gọi Thiên Lôi khi đột phá không
     */
    public boolean needThienLoi(CanhGioi currentRealm, CanhGioi targetRealm) {
        String currentName = currentRealm.getName();
        String targetName = targetRealm.getName();

        // ✅ PHÀM NHÂN lên bất kỳ cấp nào → KHÔNG cần độ kiếp
        if (currentRealm == CanhGioi.PHAM_NHAN) {
            return false;
        }

        // ✅ LUYỆN KHÍ nội bộ → KHÔNG cần độ kiếp
        if (currentName.contains("Luyện Khí") && targetName.contains("Luyện Khí")) {
            return false;
        }

        // ✅ Đổi đại cảnh giới (Luyện Khí → Trúc Cơ, v.v.)
        String currentMajor = getMajorRealm(currentName);
        String targetMajor = getMajorRealm(targetName);

        if (!currentMajor.equals(targetMajor)) {
            return true;
        }

        // ✅ Lên trọng thiên mới (trong cùng đại cảnh)
        String currentTrong = getTrongThien(currentName);
        String targetTrong = getTrongThien(targetName);

        return !currentTrong.equals(targetTrong);
    }

    private String getMajorRealm(String name) {
        if (name.contains(" - ")) {
            return name.split(" - ")[0];
        }
        return name;
    }

    private String getTrongThien(String name) {
        if (name.contains("Nhất Trọng")) return "1";
        if (name.contains("Nhị Trọng")) return "2";
        if (name.contains("Tam Trọng")) return "3";
        if (name.contains("Tứ Trọng")) return "4";
        if (name.contains("Ngũ Trọng")) return "5";
        if (name.contains("Lục Trọng")) return "6";
        if (name.contains("Thất Trọng")) return "7";
        if (name.contains("Bát Trọng")) return "8";
        if (name.contains("Cửu Trọng")) return "9";
        return "0";
    }

    /**
     * Luôn thành công - chỉ fail nếu chết hoặc rời vùng
     */
    public boolean checkSuccess(Player player, int strikeCount) {
        return true;
    }

    public void startDoKiep(Player player, CanhGioi currentRealm, CanhGioi nextRealm) {
        if (dangKiep.containsKey(player.getUniqueId())) {
            player.sendMessage("§cBạn đang trong quá trình độ kiếp!");
            return;
        }

        PlayerData playerData = plugin.getCultivationManager().getPlayerData(player);
        if (playerData == null) {
            player.sendMessage("§cLỗi: Không tìm thấy dữ liệu tu luyện!");
            return;
        }

        Location dangTienDai = player.getLocation();

        // Thông báo bắt đầu (không có tỷ lệ)
        player.sendMessage("");
        player.sendMessage("§6§l✧ THÔNG TIN ĐỘ KIẾP ✧");
        player.sendMessage("§eTừ: §b" + currentRealm.getName());
        player.sendMessage("§eĐến: §a" + nextRealm.getName());
        player.sendMessage("§c⚡ 9 đạo Thiên Lôi sẽ giáng xuống, mỗi đạo gây §480% máu§c!");
        player.sendMessage("§e⚠ Không được rời khỏi vùng hoặc chết!");
        player.sendMessage("");

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§6§l✧ THIÊN KIẾP ✧");
        Bukkit.broadcastMessage("§eTu sĩ §f" + player.getName() + " §ebắt đầu §5Độ Kiếp§e!");
        Bukkit.broadcastMessage("§eTừ §b" + currentRealm.getName() + " §e→ §a" + nextRealm.getName());
        Bukkit.broadcastMessage("§c⚠ Khu vực bán kính " + PROTECTED_RADIUS + " blocks đã được bảo vệ!");
        Bukkit.broadcastMessage("");

        // Lưu vị trí độ kiếp
        dangKiep.put(player.getUniqueId(), dangTienDai);
        World world = dangTienDai.getWorld();

        // Hiệu ứng bắt đầu
        world.strikeLightningEffect(dangTienDai);
        world.playSound(dangTienDai, Sound.ENTITY_WITHER_SPAWN, 1.0f, 0.5f);
        createBarrier(dangTienDai);

        // Task đánh sét
        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                // Check đã thoát hoặc chết
                if (!dangKiep.containsKey(player.getUniqueId()) || player.isDead()) {
                    handleFailure(player, "§4Không chịu nổi Thiên Lôi!");
                    this.cancel();
                    return;
                }

                // Đã chịu đủ 9 đạo sét
                if (count >= 9) {
                    handleSuccess(player);
                    this.cancel();
                    return;
                }

                // Đánh sét
                strikeLightning(player, count + 1);
                count++;
            }
        }.runTaskTimer(plugin, 60L, 40L); // 60 tick delay đầu, 40 tick (2s) giữa mỗi đạo

        // Task check rời vùng
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!dangKiep.containsKey(player.getUniqueId())) {
                    this.cancel();
                    return;
                }

                Location playerLoc = player.getLocation();
                Location kiepLoc = dangKiep.get(player.getUniqueId());

                if (kiepLoc != null && playerLoc.distance(kiepLoc) > PROTECTED_RADIUS) {
                    strikeProtectedArea(player);
                    player.sendMessage("§c⚠ Không được rời khỏi vùng độ kiếp!");
                }
            }
        }.runTaskTimer(plugin, 0L, 10L);
    }

    private void strikeLightning(Player player, int strikeCount) {
        Location loc = player.getLocation();
        World world = loc.getWorld();

        // Hiệu ứng sét
        world.strikeLightningEffect(loc);
        world.spawnParticle(Particle.SOUL_FIRE_FLAME, loc, 50, 0.2, 0.2, 0.2, 0.05);
        world.spawnParticle(Particle.ELECTRIC_SPARK, loc, 100, 0.5, 0.5, 0.5, 0.1);
        world.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);

        // Gây sát thương 80% máu
        double damage = player.getMaxHealth() * 0.8;
        player.damage(damage);

        // Thông báo
        Bukkit.broadcastMessage("§c⚡ Thiên Lôi đạo thứ §4" + strikeCount + "§c/9 giáng xuống §f" + player.getName() + "§c!");

        // Hiệu ứng choáng nhẹ
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 15, 0));
    }

    private void createBarrier(Location center) {
        new BukkitRunnable() {
            double angle = 0;
            int ticks = 0;

            @Override
            public void run() {
                if (ticks > 100 || !dangKiep.containsValue(center)) {
                    this.cancel();
                    return;
                }

                for (double y = 0; y < 5; y += 0.5) {
                    double x = PROTECTED_RADIUS * Math.cos(angle);
                    double z = PROTECTED_RADIUS * Math.sin(angle);
                    Location loc = center.clone().add(x, y, z);
                    center.getWorld().spawnParticle(Particle.END_ROD, loc, 1, 0, 0, 0, 0);
                }

                angle += Math.PI / 16;
                if (angle > Math.PI * 2) angle = 0;
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 2L);
    }

    private void strikeProtectedArea(Player player) {
        Location loc = player.getLocation();
        World world = loc.getWorld();

        world.strikeLightningEffect(loc);
        world.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0.5f);

        // Phạt 80% máu
        double damage = player.getMaxHealth() * 0.8;
        player.damage(damage);
    }

    private void handleSuccess(Player player) {
        Location loc = dangKiep.get(player.getUniqueId());
        if (loc == null) return;

        World world = loc.getWorld();
        if (world == null) return;

        // ✅ CẬP NHẬT CẢNH GIỚI
        PlayerData data = plugin.getCultivationManager().getPlayerData(player);
        if (data != null) {
            CanhGioi current = data.getCanhGioi();
            CanhGioi next = current.getNext();

            // Nâng cấp
            data.breakthrough(next);

            // Tiếp tục auto nếu còn đủ tu vi
            data.autoBreakthrough();

            // Lưu
            data.save(plugin.getDataFolder());

            // Thông báo cảnh giới mới
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage("§6§l✨ ĐỘ KIẾP THÀNH CÔNG ✨");
            Bukkit.broadcastMessage("§eTu sĩ §f" + player.getName() + " §eđã vượt qua §59 đạo Thiên Lôi§e!");
            Bukkit.broadcastMessage("§a➜ Đột phá thành công lên: §b" + data.getCanhGioi().getName());
            Bukkit.broadcastMessage("");

            player.sendMessage("§a✨ Chúc mừng đạo hữu đột phá thành công!");
            player.sendMessage("§eCảnh giới hiện tại: §b" + data.getCanhGioi().getName());
        }

        // Hiệu ứng
        world.strikeLightningEffect(loc);
        world.spawnParticle(Particle.TOTEM, loc.clone().add(0, 1, 0), 100, 0.5, 1, 0.5, 0.5);
        world.playSound(loc, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
        player.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

        // Xóa trạng thái
        dangKiep.remove(player.getUniqueId());
    }

    private void handleFailure(Player player, String reason) {
        Location loc = dangKiep.get(player.getUniqueId());
        if (loc != null) {
            World world = loc.getWorld();
            if (world != null) {
                world.strikeLightning(loc);
                world.spawnParticle(Particle.EXPLOSION_LARGE, loc, 10, 0.5, 0.5, 0.5, 0.1);
                world.playSound(loc, Sound.ENTITY_WITHER_DEATH, 1.0f, 0.5f);
            }
        }

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§4§l❌ ĐỘ KIẾP THẤT BẠI ❌");
        Bukkit.broadcastMessage("§eTu sĩ §f" + player.getName() + " §eđã thất bại trong §5Độ Kiếp§e!");
        Bukkit.broadcastMessage("§c➜ " + reason);
        Bukkit.broadcastMessage("");

        player.sendMessage("§c❌ Độ kiếp thất bại! Hãy tu luyện thêm rồi thử lại.");

        dangKiep.remove(player.getUniqueId());
    }

    public boolean isDoingKiep(Player player) {
        return player != null && dangKiep.containsKey(player.getUniqueId());
    }

    public void cancelDoKiep(Player player) {
        if (isDoingKiep(player)) {
            handleFailure(player, "§4Độ Kiếp bị hủy bỏ!");
        }
    }

    // Method cho breakthrough thường (không cần độ kiếp)
    public void handleNormalBreakthrough(Player player, CanhGioi nextRealm) {
        PlayerData playerData = plugin.getCultivationManager().getPlayerData(player);
        if (playerData != null) {
            playerData.setCanhGioi(nextRealm);
            player.sendMessage("§a✨ Đột phá thành công lên " + nextRealm.getName() + "!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }
    }
}