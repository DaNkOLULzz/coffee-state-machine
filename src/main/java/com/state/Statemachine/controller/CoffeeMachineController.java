package com.state.Statemachine.controller;

import com.state.Statemachine.service.PersistService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoffeeMachineController {

    private final PersistService persistService;

    public CoffeeMachineController(PersistService persistService) {
        this.persistService = persistService;
    }

    @PostMapping("/insert-coin")
    public boolean insertCoin(@RequestParam String userId) {
        return persistService.insertCoin(userId);
    }

    @PutMapping("/choose-coffee")
    public boolean chooseCoffee(@RequestParam String userId, @RequestParam String coffeeId) {
        return persistService.chooseCoffee(userId, coffeeId);
    }

    @PutMapping("/boil-water")
    public boolean boilWater(@RequestParam String userId) {
        return persistService.boilWater(userId);
    }

    @PutMapping("/prepare-coffee")
    public boolean prepareCoffee(@RequestParam String userId) {
        return persistService.prepareCoffee(userId);
    }
}
