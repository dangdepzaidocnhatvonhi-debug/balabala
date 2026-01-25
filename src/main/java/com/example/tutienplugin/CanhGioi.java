// Canhgioi.java
package com.example.tutienplugin;

import org.bukkit.ChatColor;

public enum CanhGioi {
    // Phàm Nhân
    PHAM_NHAN("Phàm Nhân", 0, 0, 1_000),

    // Luyện Khí (9 tầng) - 1K -> 1M
    LUYEN_KHI_1_SO("Luyện Khí - Nhất Trọng Thiên Sơ Kỳ", 1, 1_000, 5_000),
    LUYEN_KHI_1_TRUNG("Luyện Khí - Nhất Trọng Thiên Trung Kỳ", 2, 5_000, 10_000),
    LUYEN_KHI_1_HAU("Luyện Khí - Nhất Trọng Thiên Hậu Kỳ", 3, 10_000, 20_000),

    LUYEN_KHI_2_SO("Luyện Khí - Nhị Trọng Thiên Sơ Kỳ", 4, 20_000, 40_000),
    LUYEN_KHI_2_TRUNG("Luyện Khí - Nhị Trọng Thiên Trung Kỳ", 5, 40_000, 60_000),
    LUYEN_KHI_2_HAU("Luyện Khí - Nhị Trọng Thiên Hậu Kỳ", 6, 60_000, 100_000),

    LUYEN_KHI_3_SO("Luyện Khí - Tam Trọng Thiên Sơ Kỳ", 7, 100_000, 150_000),
    LUYEN_KHI_3_TRUNG("Luyện Khí - Tam Trọng Thiên Trung Kỳ", 8, 150_000, 200_000),
    LUYEN_KHI_3_HAU("Luyện Khí - Tam Trọng Thiên Hậu Kỳ", 9, 200_000, 300_000),

    LUYEN_KHI_4_SO("Luyện Khí - Tứ Trọng Thiên Sơ Kỳ", 10, 300_000, 400_000),
    LUYEN_KHI_4_TRUNG("Luyện Khí - Tứ Trọng Thiên Trung Kỳ", 11, 400_000, 500_000),
    LUYEN_KHI_4_HAU("Luyện Khí - Tứ Trọng Thiên Hậu Kỳ", 12, 500_000, 600_000),

    LUYEN_KHI_5_SO("Luyện Khí - Ngũ Trọng Thiên Sơ Kỳ", 13, 600_000, 650_000),
    LUYEN_KHI_5_TRUNG("Luyện Khí - Ngũ Trọng Thiên Trung Kỳ", 14, 650_000, 700_000),
    LUYEN_KHI_5_HAU("Luyện Khí - Ngũ Trọng Thiên Hậu Kỳ", 15, 700_000, 750_000),

    LUYEN_KHI_6_SO("Luyện Khí - Lục Trọng Thiên Sơ Kỳ", 16, 750_000, 800_000),
    LUYEN_KHI_6_TRUNG("Luyện Khí - Lục Trọng Thiên Trung Kỳ", 17, 800_000, 850_000),
    LUYEN_KHI_6_HAU("Luyện Khí - Lục Trọng Thiên Hậu Kỳ", 18, 850_000, 900_000),

    LUYEN_KHI_7_SO("Luyện Khí - Thất Trọng Thiên Sơ Kỳ", 19, 900_000, 920_000),
    LUYEN_KHI_7_TRUNG("Luyện Khí - Thất Trọng Thiên Trung Kỳ", 20, 920_000, 940_000),
    LUYEN_KHI_7_HAU("Luyện Khí - Thất Trọng Thiên Hậu Kỳ", 21, 940_000, 960_000),

    LUYEN_KHI_8_SO("Luyện Khí - Bát Trọng Thiên Sơ Kỳ", 22, 960_000, 970_000),
    LUYEN_KHI_8_TRUNG("Luyện Khí - Bát Trọng Thiên Trung Kỳ", 23, 970_000, 980_000),
    LUYEN_KHI_8_HAU("Luyện Khí - Bát Trọng Thiên Hậu Kỳ", 24, 980_000, 990_000),

    LUYEN_KHI_9_SO("Luyện Khí - Cửu Trọng Thiên Sơ Kỳ", 25, 990_000, 993_000),
    LUYEN_KHI_9_TRUNG("Luyện Khí - Cửu Trọng Thiên Trung Kỳ", 26, 993_000, 996_000),
    LUYEN_KHI_9_HAU("Luyện Khí - Cửu Trọng Thiên Hậu Kỳ", 27, 996_000, 1_000_000),

    // Trúc Cơ (9 tầng) - 1M -> 10M
    TRUC_CO_1_SO("Trúc Cơ - Nhất Trọng Thiên Sơ Kỳ", 28, 1_000_000, 1_500_000),
    TRUC_CO_1_TRUNG("Trúc Cơ - Nhất Trọng Thiên Trung Kỳ", 29, 1_500_000, 2_000_000),
    TRUC_CO_1_HAU("Trúc Cơ - Nhất Trọng Thiên Hậu Kỳ", 30, 2_000_000, 2_500_000),

    TRUC_CO_2_SO("Trúc Cơ - Nhị Trọng Thiên Sơ Kỳ", 31, 2_500_000, 3_000_000),
    TRUC_CO_2_TRUNG("Trúc Cơ - Nhị Trọng Thiên Trung Kỳ", 32, 3_000_000, 3_500_000),
    TRUC_CO_2_HAU("Trúc Cơ - Nhị Trọng Thiên Hậu Kỳ", 33, 3_500_000, 4_000_000),

    TRUC_CO_3_SO("Trúc Cơ - Tam Trọng Thiên Sơ Kỳ", 34, 4_000_000, 4_500_000),
    TRUC_CO_3_TRUNG("Trúc Cơ - Tam Trọng Thiên Trung Kỳ", 35, 4_500_000, 5_000_000),
    TRUC_CO_3_HAU("Trúc Cơ - Tam Trọng Thiên Hậu Kỳ", 36, 5_000_000, 5_500_000),

    TRUC_CO_4_SO("Trúc Cơ - Tứ Trọng Thiên Sơ Kỳ", 37, 5_500_000, 6_000_000),
    TRUC_CO_4_TRUNG("Trúc Cơ - Tứ Trọng Thiên Trung Kỳ", 38, 6_000_000, 6_500_000),
    TRUC_CO_4_HAU("Trúc Cơ - Tứ Trọng Thiên Hậu Kỳ", 39, 6_500_000, 7_000_000),

    TRUC_CO_5_SO("Trúc Cơ - Ngũ Trọng Thiên Sơ Kỳ", 40, 7_000_000, 7_500_000),
    TRUC_CO_5_TRUNG("Trúc Cơ - Ngũ Trọng Thiên Trung Kỳ", 41, 7_500_000, 8_000_000),
    TRUC_CO_5_HAU("Trúc Cơ - Ngũ Trọng Thiên Hậu Kỳ", 42, 8_000_000, 8_500_000),

    TRUC_CO_6_SO("Trúc Cơ - Lục Trọng Thiên Sơ Kỳ", 43, 8_500_000, 8_800_000),
    TRUC_CO_6_TRUNG("Trúc Cơ - Lục Trọng Thiên Trung Kỳ", 44, 8_800_000, 9_000_000),
    TRUC_CO_6_HAU("Trúc Cơ - Lục Trọng Thiên Hậu Kỳ", 45, 9_000_000, 9_200_000),

    TRUC_CO_7_SO("Trúc Cơ - Thất Trọng Thiên Sơ Kỳ", 46, 9_200_000, 9_400_000),
    TRUC_CO_7_TRUNG("Trúc Cơ - Thất Trọng Thiên Trung Kỳ", 47, 9_400_000, 9_500_000),
    TRUC_CO_7_HAU("Trúc Cơ - Thất Trọng Thiên Hậu Kỳ", 48, 9_500_000, 9_600_000),

    TRUC_CO_8_SO("Trúc Cơ - Bát Trọng Thiên Sơ Kỳ", 49, 9_600_000, 9_700_000),
    TRUC_CO_8_TRUNG("Trúc Cơ - Bát Trọng Thiên Trung Kỳ", 50, 9_700_000, 9_800_000),
    TRUC_CO_8_HAU("Trúc Cơ - Bát Trọng Thiên Hậu Kỳ", 51, 9_800_000, 9_900_000),

    TRUC_CO_9_SO("Trúc Cơ - Cửu Trọng Thiên Sơ Kỳ", 52, 9_900_000, 9_930_000),
    TRUC_CO_9_TRUNG("Trúc Cơ - Cửu Trọng Thiên Trung Kỳ", 53, 9_930_000, 9_960_000),
    TRUC_CO_9_HAU("Trúc Cơ - Cửu Trọng Thiên Hậu Kỳ", 54, 9_960_000, 10_000_000),

    // Kim Đan (9 tầng) - 10M -> 100M
    KIM_DAN_1_SO("Kim Đan - Nhất Trọng Thiên Sơ Kỳ", 55, 10_000_000, 15_000_000),
    KIM_DAN_1_TRUNG("Kim Đan - Nhất Trọng Thiên Trung Kỳ", 56, 15_000_000, 20_000_000),
    KIM_DAN_1_HAU("Kim Đan - Nhất Trọng Thiên Hậu Kỳ", 57, 20_000_000, 25_000_000),

    KIM_DAN_2_SO("Kim Đan - Nhị Trọng Thiên Sơ Kỳ", 58, 25_000_000, 30_000_000),
    KIM_DAN_2_TRUNG("Kim Đan - Nhị Trọng Thiên Trung Kỳ", 59, 30_000_000, 35_000_000),
    KIM_DAN_2_HAU("Kim Đan - Nhị Trọng Thiên Hậu Kỳ", 60, 35_000_000, 40_000_000),

    KIM_DAN_3_SO("Kim Đan - Tam Trọng Thiên Sơ Kỳ", 61, 40_000_000, 45_000_000),
    KIM_DAN_3_TRUNG("Kim Đan - Tam Trọng Thiên Trung Kỳ", 62, 45_000_000, 50_000_000),
    KIM_DAN_3_HAU("Kim Đan - Tam Trọng Thiên Hậu Kỳ", 63, 50_000_000, 55_000_000),

    KIM_DAN_4_SO("Kim Đan - Tứ Trọng Thiên Sơ Kỳ", 64, 55_000_000, 60_000_000),
    KIM_DAN_4_TRUNG("Kim Đan - Tứ Trọng Thiên Trung Kỳ", 65, 60_000_000, 65_000_000),
    KIM_DAN_4_HAU("Kim Đan - Tứ Trọng Thiên Hậu Kỳ", 66, 65_000_000, 70_000_000),

    KIM_DAN_5_SO("Kim Đan - Ngũ Trọng Thiên Sơ Kỳ", 67, 70_000_000, 75_000_000),
    KIM_DAN_5_TRUNG("Kim Đan - Ngũ Trọng Thiên Trung Kỳ", 68, 75_000_000, 80_000_000),
    KIM_DAN_5_HAU("Kim Đan - Ngũ Trọng Thiên Hậu Kỳ", 69, 80_000_000, 85_000_000),

    KIM_DAN_6_SO("Kim Đan - Lục Trọng Thiên Sơ Kỳ", 70, 85_000_000, 87_000_000),
    KIM_DAN_6_TRUNG("Kim Đan - Lục Trọng Thiên Trung Kỳ", 71, 87_000_000, 89_000_000),
    KIM_DAN_6_HAU("Kim Đan - Lục Trọng Thiên Hậu Kỳ", 72, 89_000_000, 91_000_000),

    KIM_DAN_7_SO("Kim Đan - Thất Trọng Thiên Sơ Kỳ", 73, 91_000_000, 93_000_000),
    KIM_DAN_7_TRUNG("Kim Đan - Thất Trọng Thiên Trung Kỳ", 74, 93_000_000, 95_000_000),
    KIM_DAN_7_HAU("Kim Đan - Thất Trọng Thiên Hậu Kỳ", 75, 95_000_000, 96_000_000),

    KIM_DAN_8_SO("Kim Đan - Bát Trọng Thiên Sơ Kỳ", 76, 96_000_000, 97_000_000),
    KIM_DAN_8_TRUNG("Kim Đan - Bát Trọng Thiên Trung Kỳ", 77, 97_000_000, 98_000_000),
    KIM_DAN_8_HAU("Kim Đan - Bát Trọng Thiên Hậu Kỳ", 78, 98_000_000, 99_000_000),

    KIM_DAN_9_SO("Kim Đan - Cửu Trọng Thiên Sơ Kỳ", 79, 99_000_000, 99_300_000),
    KIM_DAN_9_TRUNG("Kim Đan - Cửu Trọng Thiên Trung Kỳ", 80, 99_300_000, 99_600_000),
    KIM_DAN_9_HAU("Kim Đan - Cửu Trọng Thiên Hậu Kỳ", 81, 99_600_000, 100_000_000),

    // Nguyên Anh (9 tầng) - 100M -> 1B
    NGUYEN_ANH_1_SO("Nguyên Anh - Nhất Trọng Thiên Sơ Kỳ", 82, 100_000_000, 150_000_000),
    NGUYEN_ANH_1_TRUNG("Nguyên Anh - Nhất Trọng Thiên Trung Kỳ", 83, 150_000_000, 200_000_000),
    NGUYEN_ANH_1_HAU("Nguyên Anh - Nhất Trọng Thiên Hậu Kỳ", 84, 200_000_000, 250_000_000),

    NGUYEN_ANH_2_SO("Nguyên Anh - Nhị Trọng Thiên Sơ Kỳ", 85, 250_000_000, 300_000_000),
    NGUYEN_ANH_2_TRUNG("Nguyên Anh - Nhị Trọng Thiên Trung Kỳ", 86, 300_000_000, 350_000_000),
    NGUYEN_ANH_2_HAU("Nguyên Anh - Nhị Trọng Thiên Hậu Kỳ", 87, 350_000_000, 400_000_000),

    NGUYEN_ANH_3_SO("Nguyên Anh - Tam Trọng Thiên Sơ Kỳ", 88, 400_000_000, 450_000_000),
    NGUYEN_ANH_3_TRUNG("Nguyên Anh - Tam Trọng Thiên Trung Kỳ", 89, 450_000_000, 500_000_000),
    NGUYEN_ANH_3_HAU("Nguyên Anh - Tam Trọng Thiên Hậu Kỳ", 90, 500_000_000, 550_000_000),

    NGUYEN_ANH_4_SO("Nguyên Anh - Tứ Trọng Thiên Sơ Kỳ", 91, 550_000_000, 600_000_000),
    NGUYEN_ANH_4_TRUNG("Nguyên Anh - Tứ Trọng Thiên Trung Kỳ", 92, 600_000_000, 650_000_000),
    NGUYEN_ANH_4_HAU("Nguyên Anh - Tứ Trọng Thiên Hậu Kỳ", 93, 650_000_000, 700_000_000),

    NGUYEN_ANH_5_SO("Nguyên Anh - Ngũ Trọng Thiên Sơ Kỳ", 94, 700_000_000, 750_000_000),
    NGUYEN_ANH_5_TRUNG("Nguyên Anh - Ngũ Trọng Thiên Trung Kỳ", 95, 750_000_000, 800_000_000),
    NGUYEN_ANH_5_HAU("Nguyên Anh - Ngũ Trọng Thiên Hậu Kỳ", 96, 800_000_000, 850_000_000),

    NGUYEN_ANH_6_SO("Nguyên Anh - Lục Trọng Thiên Sơ Kỳ", 97, 850_000_000, 880_000_000),
    NGUYEN_ANH_6_TRUNG("Nguyên Anh - Lục Trọng Thiên Trung Kỳ", 98, 880_000_000, 900_000_000),
    NGUYEN_ANH_6_HAU("Nguyên Anh - Lục Trọng Thiên Hậu Kỳ", 99, 900_000_000, 920_000_000),

    NGUYEN_ANH_7_SO("Nguyên Anh - Thất Trọng Thiên Sơ Kỳ", 100, 920_000_000, 940_000_000),
    NGUYEN_ANH_7_TRUNG("Nguyên Anh - Thất Trọng Thiên Trung Kỳ", 101, 940_000_000, 960_000_000),
    NGUYEN_ANH_7_HAU("Nguyên Anh - Thất Trọng Thiên Hậu Kỳ", 102, 960_000_000, 970_000_000),

    NGUYEN_ANH_8_SO("Nguyên Anh - Bát Trọng Thiên Sơ Kỳ", 103, 970_000_000, 980_000_000),
    NGUYEN_ANH_8_TRUNG("Nguyên Anh - Bát Trọng Thiên Trung Kỳ", 104, 980_000_000, 990_000_000),
    NGUYEN_ANH_8_HAU("Nguyên Anh - Bát Trọng Thiên Hậu Kỳ", 105, 990_000_000, 995_000_000),

    NGUYEN_ANH_9_SO("Nguyên Anh - Cửu Trọng Thiên Sơ Kỳ", 106, 995_000_000, 997_000_000),
    NGUYEN_ANH_9_TRUNG("Nguyên Anh - Cửu Trọng Thiên Trung Kỳ", 107, 997_000_000, 999_000_000),
    NGUYEN_ANH_9_HAU("Nguyên Anh - Cửu Trọng Thiên Hậu Kỳ", 108, 999_000_000, 1_000_000_000),

    // Hóa Thần (9 tầng) - 1B -> 10B
    HOA_THAN_1_SO("Hóa Thần - Nhất Trọng Thiên Sơ Kỳ", 109, 1_000_000_000, 1_500_000_000),
    HOA_THAN_1_TRUNG("Hóa Thần - Nhất Trọng Thiên Trung Kỳ", 110, 1_500_000_000, 2_000_000_000),
    HOA_THAN_1_HAU("Hóa Thần - Nhất Trọng Thiên Hậu Kỳ", 111, 2_000_000_000L, 2_500_000_000L),

    HOA_THAN_2_SO("Hóa Thần - Nhị Trọng Thiên Sơ Kỳ", 112, 2_500_000_000L, 3_000_000_000L),
    HOA_THAN_2_TRUNG("Hóa Thần - Nhị Trọng Thiên Trung Kỳ", 113, 3_000_000_000L, 3_500_000_000L),
    HOA_THAN_2_HAU("Hóa Thần - Nhị Trọng Thiên Hậu Kỳ", 114, 3_500_000_000L, 4_000_000_000L),

    HOA_THAN_3_SO("Hóa Thần - Tam Trọng Thiên Sơ Kỳ", 115, 4_000_000_000L, 4_500_000_000L),
    HOA_THAN_3_TRUNG("Hóa Thần - Tam Trọng Thiên Trung Kỳ", 116, 4_500_000_000L, 5_000_000_000L),
    HOA_THAN_3_HAU("Hóa Thần - Tam Trọng Thiên Hậu Kỳ", 117, 5_000_000_000L, 5_500_000_000L),

    HOA_THAN_4_SO("Hóa Thần - Tứ Trọng Thiên Sơ Kỳ", 118, 5_500_000_000L, 6_000_000_000L),
    HOA_THAN_4_TRUNG("Hóa Thần - Tứ Trọng Thiên Trung Kỳ", 119, 6_000_000_000L, 6_500_000_000L),
    HOA_THAN_4_HAU("Hóa Thần - Tứ Trọng Thiên Hậu Kỳ", 120, 6_500_000_000L, 7_000_000_000L),

    HOA_THAN_5_SO("Hóa Thần - Ngũ Trọng Thiên Sơ Kỳ", 121, 7_000_000_000L, 7_500_000_000L),
    HOA_THAN_5_TRUNG("Hóa Thần - Ngũ Trọng Thiên Trung Kỳ", 122, 7_500_000_000L, 8_000_000_000L),
    HOA_THAN_5_HAU("Hóa Thần - Ngũ Trọng Thiên Hậu Kỳ", 123, 8_000_000_000L, 8_500_000_000L),

    HOA_THAN_6_SO("Hóa Thần - Lục Trọng Thiên Sơ Kỳ", 124, 8_500_000_000L, 8_800_000_000L),
    HOA_THAN_6_TRUNG("Hóa Thần - Lục Trọng Thiên Trung Kỳ", 125, 8_800_000_000L, 9_000_000_000L),
    HOA_THAN_6_HAU("Hóa Thần - Lục Trọng Thiên Hậu Kỳ", 126, 9_000_000_000L, 9_200_000_000L),

    HOA_THAN_7_SO("Hóa Thần - Thất Trọng Thiên Sơ Kỳ", 127, 9_200_000_000L, 9_400_000_000L),
    HOA_THAN_7_TRUNG("Hóa Thần - Thất Trọng Thiên Trung Kỳ", 128, 9_400_000_000L, 9_600_000_000L),
    HOA_THAN_7_HAU("Hóa Thần - Thất Trọng Thiên Hậu Kỳ", 129, 9_600_000_000L, 9_700_000_000L),

    HOA_THAN_8_SO("Hóa Thần - Bát Trọng Thiên Sơ Kỳ", 130, 9_700_000_000L, 9_800_000_000L),
    HOA_THAN_8_TRUNG("Hóa Thần - Bát Trọng Thiên Trung Kỳ", 131, 9_800_000_000L, 9_900_000_000L),
    HOA_THAN_8_HAU("Hóa Thần - Bát Trọng Thiên Hậu Kỳ", 132, 9_900_000_000L, 9_950_000_000L),

    HOA_THAN_9_SO("Hóa Thần - Cửu Trọng Thiên Sơ Kỳ", 133, 9_950_000_000L, 9_970_000_000L),
    HOA_THAN_9_TRUNG("Hóa Thần - Cửu Trọng Thiên Trung Kỳ", 134, 9_970_000_000L, 9_990_000_000L),
    HOA_THAN_9_HAU("Hóa Thần - Cửu Trọng Thiên Hậu Kỳ", 135, 9_990_000_000L, 10_000_000_000L),

    // Hợp Thể (9 tầng) - 10B -> 100B
    HOP_THE_1_SO("Hợp Thể - Nhất Trọng Thiên Sơ Kỳ", 136, 10_000_000_000L, 15_000_000_000L),
    HOP_THE_1_TRUNG("Hợp Thể - Nhất Trọng Thiên Trung Kỳ", 137, 15_000_000_000L, 20_000_000_000L),
    HOP_THE_1_HAU("Hợp Thể - Nhất Trọng Thiên Hậu Kỳ", 138, 20_000_000_000L, 25_000_000_000L),

    HOP_THE_2_SO("Hợp Thể - Nhị Trọng Thiên Sơ Kỳ", 139, 25_000_000_000L, 30_000_000_000L),
    HOP_THE_2_TRUNG("Hợp Thể - Nhị Trọng Thiên Trung Kỳ", 140, 30_000_000_000L, 35_000_000_000L),
    HOP_THE_2_HAU("Hợp Thể - Nhị Trọng Thiên Hậu Kỳ", 141, 35_000_000_000L, 40_000_000_000L),

    HOP_THE_3_SO("Hợp Thể - Tam Trọng Thiên Sơ Kỳ", 142, 40_000_000_000L, 45_000_000_000L),
    HOP_THE_3_TRUNG("Hợp Thể - Tam Trọng Thiên Trung Kỳ", 143, 45_000_000_000L, 50_000_000_000L),
    HOP_THE_3_HAU("Hợp Thể - Tam Trọng Thiên Hậu Kỳ", 144, 50_000_000_000L, 55_000_000_000L),

    HOP_THE_4_SO("Hợp Thể - Tứ Trọng Thiên Sơ Kỳ", 145, 55_000_000_000L, 60_000_000_000L),
    HOP_THE_4_TRUNG("Hợp Thể - Tứ Trọng Thiên Trung Kỳ", 146, 60_000_000_000L, 65_000_000_000L),
    HOP_THE_4_HAU("Hợp Thể - Tứ Trọng Thiên Hậu Kỳ", 147, 65_000_000_000L, 70_000_000_000L),

    HOP_THE_5_SO("Hợp Thể - Ngũ Trọng Thiên Sơ Kỳ", 148, 70_000_000_000L, 75_000_000_000L),
    HOP_THE_5_TRUNG("Hợp Thể - Ngũ Trọng Thiên Trung Kỳ", 149, 75_000_000_000L, 80_000_000_000L),
    HOP_THE_5_HAU("Hợp Thể - Ngũ Trọng Thiên Hậu Kỳ", 150, 80_000_000_000L, 85_000_000_000L),

    HOP_THE_6_SO("Hợp Thể - Lục Trọng Thiên Sơ Kỳ", 151, 85_000_000_000L, 88_000_000_000L),
    HOP_THE_6_TRUNG("Hợp Thể - Lục Trọng Thiên Trung Kỳ", 152, 88_000_000_000L, 90_000_000_000L),
    HOP_THE_6_HAU("Hợp Thể - Lục Trọng Thiên Hậu Kỳ", 153, 90_000_000_000L, 92_000_000_000L),

    HOP_THE_7_SO("Hợp Thể - Thất Trọng Thiên Sơ Kỳ", 154, 92_000_000_000L, 94_000_000_000L),
    HOP_THE_7_TRUNG("Hợp Thể - Thất Trọng Thiên Trung Kỳ", 155, 94_000_000_000L, 96_000_000_000L),
    HOP_THE_7_HAU("Hợp Thể - Thất Trọng Thiên Hậu Kỳ", 156, 96_000_000_000L, 97_000_000_000L),

    HOP_THE_8_SO("Hợp Thể - Bát Trọng Thiên Sơ Kỳ", 157, 97_000_000_000L, 98_000_000_000L),
    HOP_THE_8_TRUNG("Hợp Thể - Bát Trọng Thiên Trung Kỳ", 158, 98_000_000_000L, 99_000_000_000L),
    HOP_THE_8_HAU("Hợp Thể - Bát Trọng Thiên Hậu Kỳ", 159, 99_000_000_000L, 99_500_000_000L),

    HOP_THE_9_SO("Hợp Thể - Cửu Trọng Thiên Sơ Kỳ", 160, 99_500_000_000L, 99_700_000_000L),
    HOP_THE_9_TRUNG("Hợp Thể - Cửu Trọng Thiên Trung Kỳ", 161, 99_700_000_000L, 99_900_000_000L),
    HOP_THE_9_HAU("Hợp Thể - Cửu Trọng Thiên Hậu Kỳ", 162, 99_900_000_000L, 100_000_000_000L),

    // Đại Thừa (9 tầng) - 100B -> 300B
    DAI_THUA_1_SO("Đại Thừa - Nhất Trọng Thiên Sơ Kỳ", 163, 100_000_000_000L, 120_000_000_000L),
    DAI_THUA_1_TRUNG("Đại Thừa - Nhất Trọng Thiên Trung Kỳ", 164, 120_000_000_000L, 140_000_000_000L),
    DAI_THUA_1_HAU("Đại Thừa - Nhất Trọng Thiên Hậu Kỳ", 165, 140_000_000_000L, 160_000_000_000L),

    DAI_THUA_2_SO("Đại Thừa - Nhị Trọng Thiên Sơ Kỳ", 166, 160_000_000_000L, 180_000_000_000L),
    DAI_THUA_2_TRUNG("Đại Thừa - Nhị Trọng Thiên Trung Kỳ", 167, 180_000_000_000L, 200_000_000_000L),
    DAI_THUA_2_HAU("Đại Thừa - Nhị Trọng Thiên Hậu Kỳ", 168, 200_000_000_000L, 220_000_000_000L),

    DAI_THUA_3_SO("Đại Thừa - Tam Trọng Thiên Sơ Kỳ", 169, 220_000_000_000L, 230_000_000_000L),
    DAI_THUA_3_TRUNG("Đại Thừa - Tam Trọng Thiên Trung Kỳ", 170, 230_000_000_000L, 240_000_000_000L),
    DAI_THUA_3_HAU("Đại Thừa - Tam Trọng Thiên Hậu Kỳ", 171, 240_000_000_000L, 250_000_000_000L),

    DAI_THUA_4_SO("Đại Thừa - Tứ Trọng Thiên Sơ Kỳ", 172, 250_000_000_000L, 255_000_000_000L),
    DAI_THUA_4_TRUNG("Đại Thừa - Tứ Trọng Thiên Trung Kỳ", 173, 255_000_000_000L, 260_000_000_000L),
    DAI_THUA_4_HAU("Đại Thừa - Tứ Trọng Thiên Hậu Kỳ", 174, 260_000_000_000L, 265_000_000_000L),

    DAI_THUA_5_SO("Đại Thừa - Ngũ Trọng Thiên Sơ Kỳ", 175, 265_000_000_000L, 270_000_000_000L),
    DAI_THUA_5_TRUNG("Đại Thừa - Ngũ Trọng Thiên Trung Kỳ", 176, 270_000_000_000L, 275_000_000_000L),
    DAI_THUA_5_HAU("Đại Thừa - Ngũ Trọng Thiên Hậu Kỳ", 177, 275_000_000_000L, 280_000_000_000L),

    DAI_THUA_6_SO("Đại Thừa - Lục Trọng Thiên Sơ Kỳ", 178, 280_000_000_000L, 283_000_000_000L),
    DAI_THUA_6_TRUNG("Đại Thừa - Lục Trọng Thiên Trung Kỳ", 179, 283_000_000_000L, 286_000_000_000L),
    DAI_THUA_6_HAU("Đại Thừa - Lục Trọng Thiên Hậu Kỳ", 180, 286_000_000_000L, 290_000_000_000L),

    DAI_THUA_7_SO("Đại Thừa - Thất Trọng Thiên Sơ Kỳ", 181, 290_000_000_000L, 292_000_000_000L),
    DAI_THUA_7_TRUNG("Đại Thừa - Thất Trọng Thiên Trung Kỳ", 182, 292_000_000_000L, 294_000_000_000L),
    DAI_THUA_7_HAU("Đại Thừa - Thất Trọng Thiên Hậu Kỳ", 183, 294_000_000_000L, 296_000_000_000L),

    DAI_THUA_8_SO("Đại Thừa - Bát Trọng Thiên Sơ Kỳ", 184, 296_000_000_000L, 297_000_000_000L),
    DAI_THUA_8_TRUNG("Đại Thừa - Bát Trọng Thiên Trung Kỳ", 185, 297_000_000_000L, 298_000_000_000L),
    DAI_THUA_8_HAU("Đại Thừa - Bát Trọng Thiên Hậu Kỳ", 186, 298_000_000_000L, 299_000_000_000L),

    DAI_THUA_9_SO("Đại Thừa - Cửu Trọng Thiên Sơ Kỳ", 187, 299_000_000_000L, 299_300_000_000L),
    DAI_THUA_9_TRUNG("Đại Thừa - Cửu Trọng Thiên Trung Kỳ", 188, 299_300_000_000L, 299_600_000_000L),
    DAI_THUA_9_HAU("Đại Thừa - Cửu Trọng Thiên Hậu Kỳ", 189, 299_600_000_000L, 300_000_000_000L),

    // Độ Kiếp (9 tầng) - 300B -> 500B (MAX)
    DO_KIEP_1_SO("Độ Kiếp - Nhất Trọng Thiên Sơ Kỳ", 190, 300_000_000_000L, 320_000_000_000L),
    DO_KIEP_1_TRUNG("Độ Kiếp - Nhất Trọng Thiên Trung Kỳ", 191, 320_000_000_000L, 340_000_000_000L),
    DO_KIEP_1_HAU("Độ Kiếp - Nhất Trọng Thiên Hậu Kỳ", 192, 340_000_000_000L, 360_000_000_000L),

    DO_KIEP_2_SO("Độ Kiếp - Nhị Trọng Thiên Sơ Kỳ", 193, 360_000_000_000L, 370_000_000_000L),
    DO_KIEP_2_TRUNG("Độ Kiếp - Nhị Trọng Thiên Trung Kỳ", 194, 370_000_000_000L, 380_000_000_000L),
    DO_KIEP_2_HAU("Độ Kiếp - Nhị Trọng Thiên Hậu Kỳ", 195, 380_000_000_000L, 390_000_000_000L),

    DO_KIEP_3_SO("Độ Kiếp - Tam Trọng Thiên Sơ Kỳ", 196, 390_000_000_000L, 400_000_000_000L),
    DO_KIEP_3_TRUNG("Độ Kiếp - Tam Trọng Thiên Trung Kỳ", 197, 400_000_000_000L, 410_000_000_000L),
    DO_KIEP_3_HAU("Độ Kiếp - Tam Trọng Thiên Hậu Kỳ", 198, 410_000_000_000L, 420_000_000_000L),

    DO_KIEP_4_SO("Độ Kiếp - Tứ Trọng Thiên Sơ Kỳ", 199, 420_000_000_000L, 430_000_000_000L),
    DO_KIEP_4_TRUNG("Độ Kiếp - Tứ Trọng Thiên Trung Kỳ", 200, 430_000_000_000L, 440_000_000_000L),
    DO_KIEP_4_HAU("Độ Kiếp - Tứ Trọng Thiên Hậu Kỳ", 201, 440_000_000_000L, 450_000_000_000L),

    DO_KIEP_5_SO("Độ Kiếp - Ngũ Trọng Thiên Sơ Kỳ", 202, 450_000_000_000L, 460_000_000_000L),
    DO_KIEP_5_TRUNG("Độ Kiếp - Ngũ Trọng Thiên Trung Kỳ", 203, 460_000_000_000L, 470_000_000_000L),
    DO_KIEP_5_HAU("Độ Kiếp - Ngũ Trọng Thiên Hậu Kỳ", 204, 470_000_000_000L, 480_000_000_000L),

    DO_KIEP_6_SO("Độ Kiếp - Lục Trọng Thiên Sơ Kỳ", 205, 480_000_000_000L, 485_000_000_000L),
    DO_KIEP_6_TRUNG("Độ Kiếp - Lục Trọng Thiên Trung Kỳ", 206, 485_000_000_000L, 490_000_000_000L),
    DO_KIEP_6_HAU("Độ Kiếp - Lục Trọng Thiên Hậu Kỳ", 207, 490_000_000_000L, 493_000_000_000L),

    DO_KIEP_7_SO("Độ Kiếp - Thất Trọng Thiên Sơ Kỳ", 208, 493_000_000_000L, 495_000_000_000L),
    DO_KIEP_7_TRUNG("Độ Kiếp - Thất Trọng Thiên Trung Kỳ", 209, 495_000_000_000L, 496_000_000_000L),
    DO_KIEP_7_HAU("Độ Kiếp - Thất Trọng Thiên Hậu Kỳ", 210, 496_000_000_000L, 497_000_000_000L),

    DO_KIEP_8_SO("Độ Kiếp - Bát Trọng Thiên Sơ Kỳ", 211, 497_000_000_000L, 498_000_000_000L),
    DO_KIEP_8_TRUNG("Độ Kiếp - Bát Trọng Thiên Trung Kỳ", 212, 498_000_000_000L, 498_500_000_000L),
    DO_KIEP_8_HAU("Độ Kiếp - Bát Trọng Thiên Hậu Kỳ", 213, 498_500_000_000L, 499_000_000_000L),

    DO_KIEP_9_SO("Độ Kiếp - Cửu Trọng Thiên Sơ Kỳ", 214, 499_000_000_000L, 499_300_000_000L),
    DO_KIEP_9_TRUNG("Độ Kiếp - Cửu Trọng Thiên Trung Kỳ", 215, 499_300_000_000L, 499_600_000_000L),
    DO_KIEP_9_HAU("Độ Kiếp - Cửu Trọng Thiên Hậu Kỳ", 216, 499_600_000_000L, 500_000_000_000L);

    private final String name;
    private final int level;
    private final long requiredExp; // Đổi tên từ minExp
    private final long nextExp;     // Đổi tên từ maxExp

    CanhGioi(String name, int level, long requiredExp, long nextExp) {
        this.name = name;
        this.level = level;
        this.requiredExp = requiredExp;
        this.nextExp = nextExp;
    }

    /**
     * Lấy tên đầy đủ của cảnh giới
     * @return Tên đầy đủ
     */
    public String getName() { return name; }

    /**
     * Lấy cấp độ của cảnh giới
     * @return Cấp độ (từ 0 trở lên)
     */
    public int getLevel() { return level; }

    /**
     * Lấy số điểm tu vi tối thiểu để đạt được cảnh giới này
     * @return Số điểm tu vi tối thiểu
     */
    public long getRequiredExp() { return requiredExp; }

    /**
     * Lấy số điểm tu vi cần để lên cấp tiếp theo
     * @return Số điểm tu vi cần thiết
     */
    public long getNextExp() { return nextExp; }

    /**
     * Kiểm tra xem có phải là cảnh giới cao nhất không
     * @return true nếu là cảnh giới cao nhất, ngược lại false
     */
    public boolean isMaxLevel() {
        return this == DO_KIEP_9_HAU;
    }

    /**
     * Lấy tỷ lệ tiến độ hiện tại (0.0 - 1.0)
     * @param currentExp Tu vi hiện tại của người chơi
     * @return Tỷ lệ tiến độ từ 0.0 đến 1.0
     */
    public double getProgress(long currentExp) {
        if (isMaxLevel()) return 1.0;
        if (currentExp <= requiredExp) return 0.0;
        if (currentExp >= nextExp) return 1.0;
        return (double)(currentExp - requiredExp) / (nextExp - requiredExp);
    }

    /**
     * Lấy cảnh giới tiếp theo
     * @return Cảnh giới tiếp theo, hoặc chính nó nếu đã là cấp cao nhất
     */
    public CanhGioi getNext() {
        if (isMaxLevel()) return this;
        int nextOrdinal = this.ordinal() + 1;
        if (nextOrdinal < values().length) {
            return values()[nextOrdinal];
        }
        return this;
    }

    /**
     * Tìm cảnh giới dựa trên số điểm tu vi
     * @param exp Số điểm tu vi của người chơi
     * @return Cảnh giới tương ứng với số điểm tu vi
     */
    public static CanhGioi fromExp(long exp) {
        CanhGioi result = PHAM_NHAN;
        for (CanhGioi cg : values()) {
            if (exp >= cg.requiredExp) {
                result = cg;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Lấy tất cả các cảnh giới chính (bỏ qua các giai đoạn Sơ/Trung/Hậu kỳ)
     * @return Mảng các cảnh giới chính
     */
    public static CanhGioi[] getMainRealms() {
        return new CanhGioi[]{
                PHAM_NHAN,
                LUYEN_KHI_1_SO,  // Đại diện cho Luyện Khí
                TRUC_CO_1_SO,    // Đại diện cho Trúc Cơ
                KIM_DAN_1_SO,    // Đại diện cho Kim Đan
                NGUYEN_ANH_1_SO, // Đại diện cho Nguyên Anh
                HOA_THAN_1_SO,   // Đại diện cho Hóa Thần
                HOP_THE_1_SO,    // Đại diện cho Hợp Thể
                DAI_THUA_1_SO,   // Đại diện cho Đại Thừa
                DO_KIEP_1_SO     // Đại diện cho Độ Kiếp
        };
    }

    /**
     * Kiểm tra xem đã đủ điều kiện lên cấp chưa
     * @param currentExp Tu vi hiện tại
     * @return true nếu đủ điều kiện lên cấp, false nếu chưa
     */
    public boolean canLevelUp(long currentExp) {
        return !isMaxLevel() && currentExp >= nextExp;
    }

    /**
     * Lấy tên rút gọn của cảnh giới (bỏ phần mô tả chi tiết)
     * @return Tên rút gọn
     */
    public String getShortName() {
        String[] parts = name.split(" - ");
        return parts[0];
    }

    /**
     * Lấy màu hiển thị cho cảnh giới
     * @return ChatColor tương ứng với cảnh giới
     */
    public ChatColor getColor() {
        if (level >= 190) return ChatColor.DARK_PURPLE; // Độ Kiếp
        if (level >= 163) return ChatColor.LIGHT_PURPLE; // Đại Thừa
        if (level >= 136) return ChatColor.RED; // Hợp Thể
        if (level >= 109) return ChatColor.GOLD; // Hóa Thần
        if (level >= 82) return ChatColor.YELLOW; // Nguyên Anh
        if (level >= 55) return ChatColor.AQUA; // Kim Đan
        if (level >= 28) return ChatColor.GREEN; // Trúc Cơ
        if (level >= 1) return ChatColor.WHITE; // Luyện Khí
        return ChatColor.GRAY; // Phàm Nhân
    }

    /**
     * Lấy prefix có màu cho cảnh giới
     * @return String prefix có màu
     */
    public String getPrefix() {
        return getColor() + "[" + name + "] ";
    }
}