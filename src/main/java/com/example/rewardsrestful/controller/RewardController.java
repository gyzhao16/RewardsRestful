package com.example.rewardsrestful.controller;

import com.example.rewardsrestful.service.RewardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RewardController {

    private RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/rewards/{id}")
    public ResponseEntity<?> getTotalRewards(@PathVariable("id") Long customerId) {

        return new ResponseEntity<>(rewardService.getTotalRewards(customerId), HttpStatus.OK);
    }

    @GetMapping("/reward/{id}/{month}")
    public ResponseEntity<?> getMonthlyReward(@PathVariable("id") Long customerId, @PathVariable("month") String month) {

        return new ResponseEntity<>(rewardService.getMonthlyReward(customerId, month), HttpStatus.OK);
    }


}
