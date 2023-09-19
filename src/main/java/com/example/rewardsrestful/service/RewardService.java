package com.example.rewardsrestful.service;

import com.example.rewardsrestful.model.Reward;

import java.util.List;

public interface RewardService {

    List<Reward> getTotalRewards(Long customerId);
    Reward getMonthlyReward(Long customerId, String month);
}
