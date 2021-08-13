package com.state.Statemachine.action;

import com.state.Statemachine.enums.CoffeeMachineEvent;
import com.state.Statemachine.enums.CoffeeMachineState;
import lombok.extern.java.Log;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

@Log
public class ChooseCoffeeAction implements Action<CoffeeMachineState, CoffeeMachineEvent> {
    @Override
    public void execute(final StateContext<CoffeeMachineState, CoffeeMachineEvent> context) {
        final String coffeeId = context.getExtendedState().get("COFFEE_ID", String.class);
        log.info(String.format("The coffee with id %s was chosen.", coffeeId));
    }
}