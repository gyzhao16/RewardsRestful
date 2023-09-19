package com.example.rewardsrestful.model;

public class Reward {

    private Long customerId;
    private Long points;
    private String month;

    public Reward(Long customerId, Long points, String month) {
        this.customerId = customerId;
        this.points = points;
        this.month = month;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
