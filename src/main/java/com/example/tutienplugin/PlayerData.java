package com.example.tutienplugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private final String playerName;
    private long tuVi;           // Tu vi (exp)
    private double linhLuc;      // Linh lực (mana)
    private double maxLinhLuc;   // Max linh lực
    private CanhGioi canhGioi;   // Cảnh giới hiện tại
    private boolean dangTuLuyen; // Đang ngồi thiền?
    private long lastTuLuyen;    // Thời gian tu luyện gần nhất

    public PlayerData(Player player) {
        this.uuid = player.getUniqueId();
        this.playerName = player.getName();
        this.tuVi = 0;
        this.linhLuc = 100;
        this.maxLinhLuc = 100;
        this.canhGioi = CanhGioi.PHAM_NHAN;
        this.dangTuLuyen = false;
        this.lastTuLuyen = 0;
    }


    /**
     * Cập nhật cảnh giới của người chơi
     * @param canhGioi Cảnh giới mới
     */
    public void setCanhGioi(CanhGioi canhGioi) {
        if (canhGioi != null) {
            this.canhGioi = canhGioi;
            // Cập nhật max linh lực dựa trên cấp độ cảnh giới mới
            this.maxLinhLuc = 100 + (canhGioi.getLevel() * 50);
            // Đảm bảo linh lực hiện tại không vượt quá max mới
            this.linhLuc = Math.min(this.linhLuc, this.maxLinhLuc);
        }
    }

    // Thêm tu vi
    public void addTuVi(long amount) {
        this.tuVi += amount;
        autoBreakthrough();
    }

    public void autoBreakthrough() {
        while (true) {
            // ✅ Lấy cảnh giới TIẾP THEO (chỉ 1 cấp)
            CanhGioi nextRealm = this.canhGioi.getNext();

            // Đã max level
            if (nextRealm == this.canhGioi) return;

            // Chưa đủ tu vi để lên cấp tiếp theo
            if (this.tuVi < nextRealm.getRequiredExp()) return;

            // Cần độ kiếp → DỪNG
            if (TuTienPlugin.getInstance()
                    .getThienLoi()
                    .needThienLoi(this.canhGioi, nextRealm)) {
                return;
            }

            // ✅ Nâng lên 1 cấp
            breakthrough(nextRealm);

            // Vòng lặp tiếp tục kiểm tra cấp tiếp theo
        }
    }

    public boolean canBreakthrough() {
        CanhGioi target = CanhGioi.fromExp(this.tuVi);
        return target.getLevel() > this.canhGioi.getLevel();
    }

    public void breakthrough(CanhGioi target) {
        this.canhGioi = target;
        this.maxLinhLuc = 100 + target.getLevel() * 50;
        this.linhLuc = this.maxLinhLuc;
    }




    // Tiêu hao linh lực
    public boolean useLinhLuc(double amount) {
        if (this.linhLuc >= amount) {
            this.linhLuc -= amount;
            return true;
        }
        return false;
    }

    // Hồi phục linh lực
    public void regenLinhLuc(double amount) {
        this.linhLuc = Math.min(this.linhLuc + amount, this.maxLinhLuc);
    }

    // Getters & Setters
    public UUID getUuid() { return uuid; }
    public String getPlayerName() { return playerName; }
    public long getTuVi() { return tuVi; }
    public double getLinhLuc() { return linhLuc; }
    public double getMaxLinhLuc() { return maxLinhLuc; }
    public CanhGioi getCanhGioi() { return canhGioi; }
    public boolean isDangTuLuyen() { return dangTuLuyen; }
    public void setDangTuLuyen(boolean dangTuLuyen) { this.dangTuLuyen = dangTuLuyen; }
    public long getLastTuLuyen() { return lastTuLuyen; }
    public void setLastTuLuyen(long time) { this.lastTuLuyen = time; }

    // Save to file
    public void save(File dataFolder) {
        File playersFolder = new File(dataFolder, "players");
        if (!playersFolder.exists()) {
            playersFolder.mkdirs();
        }

        File file = new File(playersFolder, uuid.toString() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("name", playerName);
        config.set("tuvi", tuVi);
        config.set("linhluc", linhLuc);
        config.set("maxlinhluc", maxLinhLuc);
        config.set("canhgioi", canhGioi.name());

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load from file
    public static PlayerData load(File dataFolder, Player player) {
        File file = new File(dataFolder, "players/" + player.getUniqueId().toString() + ".yml");

        if (!file.exists()) {
            return new PlayerData(player);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        PlayerData data = new PlayerData(player);

        data.tuVi = config.getLong("tuvi", 0);
        data.linhLuc = config.getDouble("linhluc", 100);
        data.maxLinhLuc = config.getDouble("maxlinhluc", 100);

        try {
            String canhGioiStr = config.getString("canhgioi", "PHAM_NHAN");
            data.canhGioi = CanhGioi.valueOf(canhGioiStr);
        } catch (IllegalArgumentException e) {
            data.canhGioi = CanhGioi.fromExp(data.tuVi);
        }

        // ✅ FIX: Gọi autoBreakthrough() - nó đã kiểm tra needThienLoi() rồi
        data.autoBreakthrough();

        return data;
    }



    private boolean isMinorStageUpgrade(CanhGioi current, CanhGioi target) {
        // cùng đại cảnh (level) nhưng exp mốc cao hơn
        return current.getLevel() == target.getLevel()
                && target.getRequiredExp() > current.getRequiredExp();
    }

}