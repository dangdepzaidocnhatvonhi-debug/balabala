package com.example.tutienplugin.commands;

import com.example.tutienplugin.CanhGioi;
import com.example.tutienplugin.PlayerData;
import com.example.tutienplugin.TuTienPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class TuCommand implements CommandExecutor, TabCompleter {
    private final TuTienPlugin plugin;

    public TuCommand(TuTienPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cChỉ người chơi mới dùng được lệnh này!");
            return true;
        }

        Player player = (Player) sender;
        PlayerData data = plugin.getCultivationManager().getPlayerData(player);
        String cmd = command.getName().toLowerCase();

        switch (cmd) {
            case "tuluyen":
                plugin.getCultivationManager().startTuLuyen(player);
                return true;

            case "dung":
                plugin.getCultivationManager().stopTuLuyen(player);
                return true;

            case "canhgioi":
                showCanhGioiInfo(player, data);
                return true;

            case "tt":  // Thay "tuthongtin" thành "tt"
                showTuLuyenInfo(player, data);
                return true;

            default:
                return false;
        }

    }

    private void showTuLuyenInfo(Player player, PlayerData data) {
        CanhGioi current = data.getCanhGioi();
        long currentExp = data.getTuVi();
        long nextExp = current.getNextExp();
        boolean isMaxLevel = (nextExp < 0 || current == CanhGioi.DO_KIEP_1_SO);
        double progress = isMaxLevel ? 100.0 : (double) currentExp / nextExp * 100;
        if (progress > 100) progress = 100;

        String progressBar = createProgressBar(progress);

        player.sendMessage("");
        player.sendMessage("§6═══════ ✨ THÔNG TIN TU LUYỆN ✨ ═══════");
        player.sendMessage("");
        player.sendMessage("§e👤 Đạo hữu: §f" + player.getName());
        player.sendMessage("§d🏮 Cảnh giới: §f" + current.getName());
        player.sendMessage("§b⚡ Tu vi: §f" + formatNumber(currentExp) +
                (isMaxLevel ? " §a(MAX)" : " §7/ " + formatNumber(nextExp)));
        player.sendMessage("§a" + progressBar + " §7(" + String.format("%.1f", progress) + "%)");
        player.sendMessage("§9💫 Linh lực: §f" + (int)data.getLinhLuc() + "/" + (int)data.getMaxLinhLuc());
        player.sendMessage("");

        if (!isMaxLevel) {
            player.sendMessage("§7➜ Kế tiếp: §f" + current.getNext().getName());
            player.sendMessage("§7  Cần thêm: §e" + formatNumber(nextExp - currentExp) + " tu vi");
        }

        player.sendMessage("§6══════════════════════════════════");
    }

    private void showCanhGioiInfo(Player player, PlayerData data) {
        CanhGioi current = data.getCanhGioi();

        player.sendMessage("");
        player.sendMessage("§6═══════ DANH SÁCH CẢNH GIỚI ═══════");
        player.sendMessage("");

        for (CanhGioi cg : CanhGioi.values()) {
            String status;
            if (cg.getLevel() < current.getLevel()) {
                status = "§a✓ ";
            } else if (cg == current) {
                status = "§e➜ ";
            } else {
                status = "§7✗ ";
            }

            player.sendMessage(status + "§f" + cg.getName() +
                    " §7(" + formatNumber(cg.getRequiredExp()) + " tu vi)");
        }

        player.sendMessage("");
        player.sendMessage("§6══════════════════════════════");
    }

    private String createProgressBar(double percent) {
        int filled = (int) (percent / 5);
        int empty = 20 - filled;
        return "§a" + "▰".repeat(Math.max(0, filled)) +
                "§7" + "▱".repeat(Math.max(0, empty));
    }

    private String formatNumber(long number) {
        if (number >= 1000000) {
            return String.format("%.1fM", number / 1000000.0);
        } else if (number >= 1000) {
            return String.format("%.1fK", number / 1000.0);
        }
        return String.valueOf(number);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>(); // Không cần tab completion vì mỗi lệnh đứng riêng
    }
}