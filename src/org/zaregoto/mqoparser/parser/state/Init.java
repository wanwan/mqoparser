package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.MQOElement;

public class Init implements State {

    private final String stateName = "Init";

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean before() {
        return true;
    }

    @Override
    public boolean after() {
        return true;
    }

    @Override
    public boolean receive(StateMachine sm, MQOElement input) {

        boolean ret = false;

        switch (input) {
            default:
                break;
        }

        return ret;
    }

}
