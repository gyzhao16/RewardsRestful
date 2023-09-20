package com.example.rewardsrestful.controller;

import com.example.rewardsrestful.exception.CustomerNotFoundException;
import com.example.rewardsrestful.exception.MonthNotValidException;
import com.example.rewardsrestful.model.Customer;
import com.example.rewardsrestful.model.ResponseMessage;
import com.example.rewardsrestful.model.Reward;
import com.example.rewardsrestful.service.CustomerService;
import com.example.rewardsrestful.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.rewardsrestful.util.RewardUtil.*;

@RestController
@RequestMapping("/api")
public class RewardController {

    private RewardService rewardService;
    private CustomerService customerService;
    private static Logger logger = LoggerFactory.getLogger(RewardController.class);


    public RewardController(RewardService rewardService, CustomerService customerService) {
        this.rewardService = rewardService;
        this.customerService = customerService;
    }

    @GetMapping("/reward/{customerId}")
    public ResponseEntity<?> getTotalRewards(@PathVariable("customerId") Long customerId) {

        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }

        List<Reward> rewards = rewardService.getTotalRewards(customerId);
        long totalPoints = rewards.stream().mapToLong(Reward::getPoints).sum();
        return new ResponseEntity<>(new ResponseMessage("Total points = " + totalPoints, rewards), HttpStatus.OK);
    }

    @GetMapping("/reward/{customerId}/{month}")
    public ResponseEntity<?> getMonthlyReward(@PathVariable("customerId") Long customerId, @PathVariable("month") String month) {

        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }
        if (month.equals(July) || month.equals(August) || month.equals(September)) {
            Reward reward = rewardService.getMonthlyReward(customerId, month);
            return new ResponseEntity<>(reward, HttpStatus.OK);
        }
        throw new MonthNotValidException("Month not valid, should be July or August or September.");
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> exceptionHandlerCustomerNotFound(Exception ex) {

        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MonthNotValidException.class)
    public ResponseEntity<?> exceptionHandlerMonthNotValid(Exception ex) {

        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
