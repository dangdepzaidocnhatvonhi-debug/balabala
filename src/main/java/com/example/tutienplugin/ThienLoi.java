package com.example.tutienplugin;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;
import com.example.tutienplugin.PlayerData;
import org.bukkit.Material;
import org.bukkit.Particle;
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
        // Case 1: Đột phá đại cảnh giới
        if (isDifferentMajorRealm(currentRealm, targetRealm)) {
            return true;
        }

        // Case 2: Lên trọng thiên mới
        String currentName = currentRealm.getName();
        String targetName = targetRealm.getName();
        
        // Lấy số trọng thiên
        String currentTrong = getTrongThien(currentName);
        String targetTrong = getTrongThien(targetName);
        
        return !currentTrong.equals(targetTrong);
    }

    public void handleNormalBreakthrough(Player player, CanhGioi nextRealm) {
        PlayerData playerData = plugin.getCultivationManager().getPlayerData(player);
        if (playerData != null) {
            // Update player's realm
            playerData.setCanhGioi(nextRealm);

            // Notify player
            player.sendMessage("§a✨ Đột phá thành công lên " + nextRealm.getName() + "!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

            // Add any other effects or logic for normal breakthrough
        }
    }
    /**
     * Kiểm tra xem có phải là 2 đại cảnh giới khác nhau không
     */
    private boolean isDifferentMajorRealm(CanhGioi r1, CanhGioi r2) {
        // Lấy tên cảnh giới (bỏ phần trọng thiên)
        String name1 = r1.getName().split(" ")[0];
        String name2 = r2.getName().split(" ")[0];
        return !name1.equals(name2);
    }

    /**
     * Lấy số trọng thiên từ tên
     * Ví dụ: "Nhất Trọng Thiên" -> "1"
     */
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
     * Lấy tỷ lệ thành công
     */
    public double getSuccessRate(CanhGioi currentRealm, CanhGioi targetRealm) {
        // Nếu là đột phá đại cảnh giới
        if (isDifferentMajorRealm(currentRealm, targetRealm)) {
            int level = targetRealm.getLevel();
            if (level >= 189) return 20.0;  // Lên Độ Kiếp
            if (level >= 162) return 30.0;  // Lên Đại Thừa
            if (level >= 135) return 40.0;  // Lên Hợp Thể
            if (level >= 108) return 50.0;  // Lên Hóa Thần
            if (level >= 81) return 60.0;   // Lên Nguyên Anh
            if (level >= 54) return 70.0;   // Lên Kim Đan
            if (level >= 27) return 80.0;   // Lên Trúc Cơ
        } 
        // Nếu là lên trọng thiên mới
        else {
            String targetTrong = getTrongThien(targetRealm.getName());
            int trong = Integer.parseInt(targetTrong);
            return Math.max(30.0, 90.0 - (trong * 5)); // 85% -> 45%
        }

        return 100.0; // Trong cùng một trọng thiên
    }

    public boolean checkSuccess(Player player, int strikeCount) {
        PlayerData playerData = plugin.getCultivationManager().getPlayerData(player);
        if (playerData == null) return false;

        CanhGioi currentRealm = playerData.getCanhGioi();
        CanhGioi nextRealm = currentRealm.getNext();
        
        if (nextRealm == null) {
            return true; // Đã đạt cảnh giới tối đa
        }
        
        if (!needThienLoi(currentRealm, nextRealm)) {
            return true; // Không cần độ kiếp
        }
        
        double baseRate = getSuccessRate(currentRealm, nextRealm);
        double currentRate = baseRate - (strikeCount * 5);

        double tuViProgress = currentRealm.getProgress(playerData.getTuVi());
        currentRate += tuViProgress * 10;

        double healthPercent = player.getHealth() / player.getMaxHealth();
        currentRate += healthPercent * 5;

        if (player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
            currentRate += 5;
        }

        return Math.random() * 100 < currentRate;
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

        double baseRate = getSuccessRate(currentRealm, nextRealm);
        Location dangTienDai = player.getLocation();

        // Thông báo tỷ lệ thành công
        player.sendMessage("");
        player.sendMessage("§6§l✧ THÔNG TIN ĐỘ KIẾP ✧");
        player.sendMessage("§eTừ: §b" + currentRealm.getName() + " §e→ §a" + nextRealm.getName());
        player.sendMessage("§eTỷ lệ thành công cơ bản: §b" + String.format("%.1f", getSuccessRate(currentRealm, nextRealm)) + "%");
        player.sendMessage("§e➜ Giảm 5% mỗi đạo sét");
        player.sendMessage("§e➜ +10% theo tiến độ tu vi");
        player.sendMessage("§e➜ +5% theo máu còn lại");
        player.sendMessage("§e➜ +5% nếu có buff kháng damage");
        player.sendMessage("");

        // Thông báo bắt đầu
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§6§l✧ THIÊN KIẾP ✧");
        Bukkit.broadcastMessage("§eTu sĩ §f" + player.getName() + " §ebắt đầu §5Độ Kiếp§e tại Đăng Tiên Đài!");
        
        // Lưu vị trí độ kiếp
        dangKiep.put(player.getUniqueId(), dangTienDai);
        World world = dangTienDai.getWorld();
        
        // Thông báo
        Bukkit.broadcastMessage("§c⚠ Khu vực bán kính 20 blocks đã được thiết lập!");
        Bukkit.broadcastMessage("§c⚡ 9 đạo Thiên Lôi sẽ giáng xuống, mỗi đạo gây §480% máu§c!");
        Bukkit.broadcastMessage("");
        
        // Hiệu ứng bắt đầu
        world.strikeLightningEffect(dangTienDai);
        world.playSound(dangTienDai, Sound.ENTITY_WITHER_SPAWN, 1.0f, 0.5f);
        createBarrier(dangTienDai);

        final int[] delay = {60};
        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (!dangKiep.containsKey(player.getUniqueId()) || player.isDead()) {
                    handleFailure(player, "§4Độ Kiếp thất bại!");
                    this.cancel();
                    return;
                }

                if (count >= 9) {
                    handleSuccess(player);
                    this.cancel();
                    return;
                }

                // Đánh sét và check thành công
                strikeLightning(player, count + 1);

                if (!checkSuccess(player, count + 1)) {
                    handleFailure(player, "§4Không đủ cơ duyên, độ kiếp thất bại!");
                    this.cancel();
                    return;
                }

                // Giảm delay mỗi đợt
                delay[0] = Math.max(20, delay[0] - 5);
                count++;
            }
        }.runTaskTimer(plugin, 60L, delay[0]);

        // Check vùng cấm
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!dangKiep.containsKey(player.getUniqueId())) {
                    this.cancel();
                    return;
                }

                Location playerLoc = player.getLocation();
                Location kiepLoc = dangKiep.get(player.getUniqueId());

                if (playerLoc.distance(kiepLoc) > PROTECTED_RADIUS) {
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

        // Thông báo đạo sét
        Bukkit.broadcastMessage("§c⚡ Thiên Lôi đạo thứ " + strikeCount + " giáng xuống!");

        // Hiệu ứng choáng
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
    }

    private void createBarrier(Location center) {
        new BukkitRunnable() {
            double angle = 0;

            @Override
            public void run() {
                if (angle > Math.PI * 2) {
                    this.cancel();
                    return;
                }

                for (double y = 0; y < 5; y += 0.5) {
                    double x = PROTECTED_RADIUS * Math.cos(angle);
                    double z = PROTECTED_RADIUS * Math.sin(angle);
                    Location loc = center.clone().add(x, y, z);

                    // Try different particle types with fallback
                    try {
                        // Try BARRIER first (1.9+)
                        center.getWorld().spawnParticle(Particle.valueOf("BARRIER"), loc, 1, 0, 0, 0, 0);
                    } catch (Exception e1) {
                        try {
                            // Try BLOCK_CRACK with BARRIER material (1.8+)
                            center.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 1, 0, 0, 0, 0,
                                    Material.BARRIER.createBlockData());
                        } catch (Exception e2) {
                            // Fallback to ENDER signal (works in most versions)
                            center.getWorld().spawnParticle(Particle.END_ROD, loc, 2, 0.1, 0.1, 0.1, 0.01);
                        }
                    }
                }

                angle += Math.PI / 16;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private void strikeProtectedArea(Player player) {
        Location loc = player.getLocation();
        World world = loc.getWorld();

        // Hiệu ứng sét trừng phạt
        world.strikeLightningEffect(loc);
        world.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 0.5f);

        // Gây sát thương 80% máu
        double damage = player.getMaxHealth() * 0.8;
        player.damage(damage);
    }

    private void handleSuccess(Player player) {
        Location loc = dangKiep.get(player.getUniqueId());
        if (loc == null) return;

        World world = loc.getWorld();
        if (world == null) return;

        // Hiệu ứng thành công
        world.strikeLightningEffect(loc);
        world.spawnParticle(Particle.EXPLOSION_HUGE, loc, 1);
        world.playSound(loc, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);

        // Thông báo
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§6§l✨ ĐỘ KIẾP THÀNH CÔNG ✨");
        Bukkit.broadcastMessage("§eTu sĩ §f" + player.getName() + " §eđã vượt qua §59 đạo Thiên Lôi§e!");
        Bukkit.broadcastMessage("§a➜ Cảnh giới được nâng lên!");
        Bukkit.broadcastMessage("");

        // Xóa khỏi danh sách đang độ kiếp
        dangKiep.remove(player.getUniqueId());

        // Hiệu ứng cho người chơi
        player.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }

    private void handleFailure(Player player, String reason) {
        Location loc = dangKiep.get(player.getUniqueId());
        if (loc != null) {
            World world = loc.getWorld();
            if (world != null) {
                // Hiệu ứng thất bại
                world.strikeLightning(loc);
                world.spawnParticle(Particle.EXPLOSION_LARGE, loc, 10, 0.5, 0.5, 0.5, 0.1);
                world.playSound(loc, Sound.ENTITY_WITHER_DEATH, 1.0f, 0.5f);
            }
        }

        // Thông báo
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§4§l❌ ĐỘ KIẾP THẤT BẠI ❌");
        Bukkit.broadcastMessage("§eTu sĩ §f" + player.getName() + " §eđã thất bại trong §5Độ Kiếp§e!");
        Bukkit.broadcastMessage("§c➜ " + reason);
        Bukkit.broadcastMessage("");

        // Xóa khỏi danh sách đang độ kiếp
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
}