package com.example.rewardsrestful.impl;

import com.example.rewardsrestful.dao.TransactionRepository;
import com.example.rewardsrestful.entity.TransactionEntity;
import com.example.rewardsrestful.model.Reward;
import com.example.rewardsrestful.model.Transaction;
import com.example.rewardsrestful.service.RewardService;
import com.example.rewardsrestful.util.EntityVoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.rewardsrestful.util.RewardUtil.*;

@Service
public class RewardServiceImpl implements RewardService {

    private TransactionRepository transactionRepository;
//    private static Logger logger = LoggerFactory.getLogger(RewardServiceImpl.class);
    public RewardServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Reward> getTotalRewards(Long customerId) {

        List<Reward> rewards = new ArrayList<>();
        rewards.add(getMonthlyReward(customerId, July));
        rewards.add(getMonthlyReward(customerId, August));
        rewards.add(getMonthlyReward(customerId, September));
        return rewards;
    }

    @Override
    public Reward getMonthlyReward(Long customerId, String month) {

        List<TransactionEntity> transactionEntities = null;
        switch (month) {
            case July ->
                    transactionEntities = transactionRepository.findTransactionEntitiesByCustomerIdAndTimeBetween(customerId, startOfJuly, endOfJuly);
            case August ->
                    transactionEntities = transactionRepository.findTransactionEntitiesByCustomerIdAndTimeBetween(customerId, startOfAugust, endOfAugust);
            case September ->
                    transactionEntities = transactionRepository.findTransactionEntitiesByCustomerIdAndTimeBetween(customerId, startOfSeptember, endOfSeptember);
        }

        return new Reward(customerId, calculateReward(transactionEntities), month);
    }

    private Long calculateReward(List<TransactionEntity> transactionEntities) {

        if (transactionEntities == null) {
            return 0L;
        }

        return (long)transactionEntities.stream().map(EntityVoConverter::convertTransactionEntityToVo).mapToDouble(Transaction::getAmount).map(
                num -> {
                    if (num > threshold100) {
                        return 2.0 * (num - threshold100) + 50.0;
                    } else if (num > threshold50) {
                        return num - threshold50;
                    } else {
                        return 0.0;
                    }
                }
        ).sum();
    }
}
