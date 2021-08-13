package com.state.Statemachine.persistance;

import com.state.Statemachine.enums.CoffeeMachineEvent;
import com.state.Statemachine.enums.CoffeeMachineState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

public class CoffeeMachinePersister implements StateMachinePersist<CoffeeMachineState, CoffeeMachineEvent, String> {

    private final HashMap<String, StateMachineContext<CoffeeMachineState, CoffeeMachineEvent>> database = new HashMap<>();

    @Override
    public void write(StateMachineContext<CoffeeMachineState, CoffeeMachineEvent> stateMachineContext, String id) {
        database.put(id, stateMachineContext);
    }

    @Override
    public StateMachineContext<CoffeeMachineState, CoffeeMachineEvent> read(String id) {
        return database.get(id);
    }
}
