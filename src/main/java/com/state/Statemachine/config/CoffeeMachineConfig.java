package com.state.Statemachine.config;

import com.state.Statemachine.action.ChooseCoffeeAction;
import com.state.Statemachine.enums.CoffeeMachineEvent;
import com.state.Statemachine.enums.CoffeeMachineState;
import com.state.Statemachine.listener.CoffeeMachineListener;
import com.state.Statemachine.persistance.CoffeeMachinePersister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

import static com.state.Statemachine.enums.CoffeeMachineEvent.*;
import static com.state.Statemachine.enums.CoffeeMachineState.*;

@Configuration
@EnableStateMachineFactory
public class CoffeeMachineConfig extends EnumStateMachineConfigurerAdapter<CoffeeMachineState, CoffeeMachineEvent> {
    @Override
    public void configure(StateMachineStateConfigurer<CoffeeMachineState, CoffeeMachineEvent> states) throws Exception {
        states
                .withStates()
                .initial(COIN_INSERTED)
                .end(CoffeeMachineState.COFFEE_PREPARED)
                .states(EnumSet.allOf(CoffeeMachineState.class));
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<CoffeeMachineState, CoffeeMachineEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(Boolean.TRUE)
                .listener(new CoffeeMachineListener());
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<CoffeeMachineState, CoffeeMachineEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(COIN_INSERTED)
                .target(COFFEE_CHOSEN)
                .event(CHOSE_COFFEE)
                .action(coffeeChosenAction())

                .and()
                .withExternal()
                .source(COFFEE_CHOSEN)
                .target(WATER_BOILED)
                .event(BOIL_WATER)

                .and()
                .withExternal()
                .source(WATER_BOILED)
                .target(COFFEE_PREPARED)
                .event(PREPARE_COFFEE);
    }

    @Bean
    public StateMachinePersister<CoffeeMachineState, CoffeeMachineEvent, String> persister() {
        return new DefaultStateMachinePersister<>(new CoffeeMachinePersister());
    }

    @Bean
    public Action<CoffeeMachineState, CoffeeMachineEvent> coffeeChosenAction() {
        return new ChooseCoffeeAction();
    }
}
