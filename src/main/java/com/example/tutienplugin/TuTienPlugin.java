package com.example.tutienplugin;

import com.example.tutienplugin.commands.TuCommand;
import com.example.tutienplugin.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TuTienPlugin extends JavaPlugin implements CommandExecutor {

    private static TuTienPlugin instance;
    private CultivationManager cultivationManager;
    private ThienLoi thienLoi;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        cultivationManager = new CultivationManager(this);
        thienLoi = new ThienLoi(this);

        // Commands
        register("tutien", new TuCommand(this), true);
        register("tuluyen", new TuCommand(this), false);
        register("dung", new TuCommand(this), false);
        register("canhgioi", new TuCommand(this), false);
        register("tt", new TuCommand(this), false);

        register("addexp", this, false);
        register("dokiep", this, false);

        // Listener
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(cultivationManager, this);

        getLogger().info("§a✨ Tu Tiên Plugin đã bật (FINAL)!");
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            cultivationManager.savePlayerData(p);
        }
        getLogger().info("§c✖ Tu Tiên Plugin đã tắt!");
    }

    private void register(String name, Object executor, boolean tab) {
        PluginCommand cmd = getCommand(name);
        if (cmd == null) return;

        cmd.setExecutor((CommandExecutor) executor);
        if (tab && executor instanceof TabCompleter) {
            cmd.setTabCompleter((TabCompleter) executor);
        }
    }

    /* =====================================================
                        COMMANDS
       ===================================================== */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        /* ========== /addexp ========== */
        if (cmd.getName().equalsIgnoreCase("addexp")) {

            if (!sender.hasPermission("tutien.admin")) {
                sender.sendMessage("§cBạn không có quyền!");
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage("§c/addexp <player> <amount>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cPlayer offline!");
                return true;
            }

            long amount;
            try {
                amount = parseExp(args[1]);
            } catch (Exception e) {
                sender.sendMessage("§cSố không hợp lệ!");
                return true;
            }

            PlayerData data = cultivationManager.getPlayerData(target);
            CanhGioi oldRealm = data.getCanhGioi();

            data.addTuVi(amount);
            cultivationManager.savePlayerData(target);

            sender.sendMessage("§aĐã thêm §e" + formatExp(amount) + " §acho " + target.getName());
            target.sendMessage("§aTu vi hiện tại: §e" + formatExp(data.getTuVi()));

            if (data.getCanhGioi().getLevel() > oldRealm.getLevel()) {
                target.sendMessage("§6✨ Bạn đã đột phá lên §d" + data.getCanhGioi().getName());
            }

            return true;
        }

        /* ========== /dokiep ========== */
        if (cmd.getName().equalsIgnoreCase("dokiep")) {

            // Check phải là Player
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cChỉ player mới dùng được lệnh này!");
                return true;
            }
            Player player = (Player) sender;

            // Check vị trí Đăng Tiên Đài
            if (!isAtDangTienDai(player.getLocation())) {
                player.sendMessage("§cBạn phải ở Đăng Tiên Đài!");
                return true;
            }

            // Lấy data
            PlayerData data = cultivationManager.getPlayerData(player);
            CanhGioi current = data.getCanhGioi();
            CanhGioi next = current.getNext();

            // Đã max level
            if (next == null || next == current) {
                player.sendMessage("§6Bạn đã đạt cảnh giới tối cao!");
                return true;
            }

            // Chưa đủ tu vi
            if (data.getTuVi() < next.getRequiredExp()) {
                player.sendMessage("§cTu vi chưa đủ để đột phá!");
                player.sendMessage("§eCần: §b" + formatExp(next.getRequiredExp()) + " §e| Hiện có: §b" + formatExp(data.getTuVi()));
                return true;
            }

            // Không cần độ kiếp
            if (!thienLoi.needThienLoi(current, next)) {
                player.sendMessage("§aCảnh giới này tự đột phá, không cần độ kiếp!");
                data.autoBreakthrough();
                cultivationManager.savePlayerData(player);
                return true;
            }

            // Bắt đầu độ kiếp
            thienLoi.startDoKiep(player, current, next);
            return true;
        }

        return false;
    }

    /* =====================================================
                          UTIL
       ===================================================== */

    private boolean isAtDangTienDai(Location loc) {
        String world = getConfig().getString("dangtien.world");
        int x = getConfig().getInt("dangtien.x");
        int y = getConfig().getInt("dangtien.y");
        int z = getConfig().getInt("dangtien.z");
        int r = getConfig().getInt("dangtien.radius", 20);

        if (world == null || loc.getWorld() == null) return false;
        if (!loc.getWorld().getName().equals(world)) return false;

        Location center = new Location(loc.getWorld(), x, y, z);
        return loc.distance(center) <= r;
    }

    private long parseExp(String s) {
        s = s.toUpperCase();
        if (s.endsWith("K")) return Long.parseLong(s.replace("K", "")) * 1_000;
        if (s.endsWith("M")) return Long.parseLong(s.replace("M", "")) * 1_000_000;
        if (s.endsWith("B")) return Long.parseLong(s.replace("B", "")) * 1_000_000_000;
        return Long.parseLong(s);
    }

    private String formatExp(long e) {
        if (e >= 1_000_000_000) return String.format("%.1fB", e / 1e9);
        if (e >= 1_000_000) return String.format("%.1fM", e / 1e6);
        if (e >= 1_000) return String.format("%.1fK", e / 1e3);
        return String.valueOf(e);
    }

    public static TuTienPlugin getInstance() {
        return instance;
    }

    public CultivationManager getCultivationManager() {
        return cultivationManager;
    }

    public ThienLoi getThienLoi() {
        return thienLoi;
    }
}