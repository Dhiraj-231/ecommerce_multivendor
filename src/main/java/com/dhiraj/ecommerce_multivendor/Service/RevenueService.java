package com.dhiraj.ecommerce_multivendor.Service;

import java.util.List;

import com.dhiraj.ecommerce_multivendor.dto.RevenueChart;

public interface RevenueService {
    List<RevenueChart> getDailyRevenueForChart(int days, Long sellerId);

    List<RevenueChart> getMonthlyRevenueForChart(int months, Long sellerId);

    List<RevenueChart> getYearlyRevenueForChart(int years, Long sellerId);

    List<RevenueChart> getHourlyRevenueForChart(Long sellerId);

    List<RevenueChart> getRevenueChartByType(String type, Long sellerId);
}
