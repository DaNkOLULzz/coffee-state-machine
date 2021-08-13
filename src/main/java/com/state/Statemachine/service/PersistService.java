package com.state.Statemachine.service;

public interface PersistService {
    boolean insertCoin(String userId);

    boolean chooseCoffee(String userId, String coffeeId);

    boolean boilWater(String userId);

    boolean prepareCoffee(String userId);
}
