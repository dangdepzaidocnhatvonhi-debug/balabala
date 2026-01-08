package com.example.tutienplugin.listeners;


import com.example.tutienplugin.CanhGioi;
import com.example.tutienplugin.PlayerData;
import com.example.tutienplugin.TuTienPlugin;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final TuTienPlugin plugin;

    public PlayerListener(TuTienPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData data = plugin.getCultivationManager().getPlayerData(player);

        player.sendMessage("");
        player.sendMessage("§6✨ Chào mừng §f" + player.getName() + " §6quay lại!");
        player.sendMessage("§d🏮 Cảnh giới: §f" + data.getCanhGioi().getName());
        player.sendMessage("§b⚡ Tu vi: §f" + data.getTuVi());
        player.sendMessage("§7Dùng §e/tutien §7để xem thông tin tu luyện!");
        player.sendMessage("");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.getCultivationManager().removePlayerData(event.getPlayer());
    }

        // Đã xóa cơ chế nhận tu vi khi giết mob

    // Bonus damage dựa trên cảnh giới
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            PlayerData data = plugin.getCultivationManager().getPlayerData(player);

            // Tăng 5% damage mỗi cảnh giới
            double bonusDamage = data.getCanhGioi().getLevel() * 0.05;
            event.setDamage(event.getDamage() * (1 + bonusDamage));
        }
    }

    private void notifyBreakthrough(Player player, CanhGioi newCanhGioi) {
        // Thông báo server
        plugin.getServer().broadcastMessage("");
        plugin.getServer().broadcastMessage("§6✨ ĐỘT PHÁ! ✨");
        plugin.getServer().broadcastMessage("§e" + player.getName() + " §fđã đột phá đến");
        plugin.getServer().broadcastMessage("§d" + newCanhGioi.getName() + "!");
        plugin.getServer().broadcastMessage("");

        // Hiệu ứng
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 0.5f, 1f);
    }
}