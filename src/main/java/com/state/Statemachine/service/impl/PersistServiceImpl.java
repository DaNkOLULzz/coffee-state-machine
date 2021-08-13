package com.state.Statemachine.service.impl;

import com.state.Statemachine.enums.CoffeeMachineEvent;
import com.state.Statemachine.enums.CoffeeMachineState;
import com.state.Statemachine.service.PersistService;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import static com.state.Statemachine.enums.CoffeeMachineEvent.*;

@Service
public class PersistServiceImpl implements PersistService {

    private final StateMachinePersister<CoffeeMachineState, CoffeeMachineEvent, String> persister;

    private final StateMachineFactory<CoffeeMachineState, CoffeeMachineEvent> stateMachineFactory;

    public PersistServiceImpl(StateMachinePersister<CoffeeMachineState, CoffeeMachineEvent, String> persister,
                              StateMachineFactory<CoffeeMachineState, CoffeeMachineEvent> stateMachineFactory) {
        this.persister = persister;
        this.stateMachineFactory = stateMachineFactory;
    }


    @Override
    public boolean insertCoin(String userId) {
        final StateMachine<CoffeeMachineState, CoffeeMachineEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            persister.persist(stateMachine, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean chooseCoffee(String userId, String coffeeId) {
        final StateMachine<CoffeeMachineState, CoffeeMachineEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            persister.restore(stateMachine, userId);
            stateMachine.getExtendedState().getVariables().put("COFFEE_ID", coffeeId);
            stateMachine.sendEvent(CHOSE_COFFEE);
            persister.persist(stateMachine, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean boilWater(String userId) {
        final StateMachine<CoffeeMachineState, CoffeeMachineEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            persister.restore(stateMachine, userId);
            stateMachine.sendEvent(BOIL_WATER);
            persister.persist(stateMachine, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean prepareCoffee(String userId) {
        final StateMachine<CoffeeMachineState, CoffeeMachineEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            persister.restore(stateMachine, userId);
            stateMachine.sendEvent(PREPARE_COFFEE);
            persister.persist(stateMachine, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
