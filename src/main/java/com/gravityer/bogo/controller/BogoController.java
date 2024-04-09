package com.gravityer.bogo.controller;

import com.gravityer.bogo.model.CalculationResult;
import com.gravityer.bogo.service.BogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bogo")
public class BogoController {

    @Autowired
    private BogoService bogoService;

    @GetMapping("/calculate")
    public CalculationResult calculateDiscounts(@RequestParam int[] prices, @RequestParam int rule) {
        return bogoService.calculateBogo(prices, rule);
    }
}