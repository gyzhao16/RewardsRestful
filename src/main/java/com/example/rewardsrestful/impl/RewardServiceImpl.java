package com.example.rewardsrestful.impl;

import com.example.rewardsrestful.model.Reward;
import com.example.rewardsrestful.service.RewardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    @Override
    public List<Reward> getTotalRewards(Long customerId) {
        return null;
    }

    @Override
    public Reward getMonthlyReward(Long customerId, String month) {
        return null;
    }
}
