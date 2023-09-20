package com.example.rewardsrestful.util;

import java.time.LocalDate;

public class RewardUtil {

    public static final String July = "July";
    public static final String August = "August";
    public static final String September = "September";

    public static LocalDate startOfJuly = LocalDate.of(2023, 7, 1);
    public static LocalDate endOfJuly = LocalDate.of(2023, 7, 31);
    public static LocalDate startOfAugust = LocalDate.of(2023, 8, 1);
    public static LocalDate endOfAugust = LocalDate.of(2023, 8, 31);
    public static LocalDate startOfSeptember = LocalDate.of(2023, 9, 1);
    public static LocalDate endOfSeptember = LocalDate.of(2023, 9, 30);

    public static final Double threshold50 = Double.valueOf(50);
    public static final Double threshold100 = Double.valueOf(100);
}
