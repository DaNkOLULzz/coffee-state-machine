package com.state.Statemachine.listener;

import com.state.Statemachine.enums.CoffeeMachineEvent;
import com.state.Statemachine.enums.CoffeeMachineState;
import lombok.extern.java.Log;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Objects;

@Log
public class CoffeeMachineListener implements StateMachineListener<CoffeeMachineState, CoffeeMachineEvent> {
    @Override
    public void stateChanged(State<CoffeeMachineState, CoffeeMachineEvent> from, State<CoffeeMachineState, CoffeeMachineEvent> to) {
        if (Objects.nonNull(from)) {
            log.info(String.format("%s was changed to %s", from.getId(), to.getId()));
        }
    }

    @Override
    public void stateEntered(State<CoffeeMachineState, CoffeeMachineEvent> state) {
    }

    @Override
    public void stateExited(State<CoffeeMachineState, CoffeeMachineEvent> state) {

    }

    @Override
    public void eventNotAccepted(Message<CoffeeMachineEvent> message) {

    }

    @Override
    public void transition(Transition<CoffeeMachineState, CoffeeMachineEvent> transition) {

    }

    @Override
    public void transitionStarted(Transition<CoffeeMachineState, CoffeeMachineEvent> transition) {

    }

    @Override
    public void transitionEnded(Transition<CoffeeMachineState, CoffeeMachineEvent> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<CoffeeMachineState, CoffeeMachineEvent> stateMachine) {
        log.info(String.format("State machine started with status %s", stateMachine.getState().getId()));
    }

    @Override
    public void stateMachineStopped(StateMachine<CoffeeMachineState, CoffeeMachineEvent> stateMachine) {
        log.info(String.format("State machine stopped with status %s", stateMachine.getState().getId()));
    }

    @Override
    public void stateMachineError(StateMachine<CoffeeMachineState, CoffeeMachineEvent> stateMachine, Exception e) {

    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<CoffeeMachineState, CoffeeMachineEvent> stateContext) {

    }
}
