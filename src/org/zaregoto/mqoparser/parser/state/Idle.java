package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;

public class Idle implements State {

    private final String stateName = "Init";

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean postTransfer(StateMachine sm, MQOElement input) {
        return true;
    }

    @Override
    public boolean preTransfer(StateMachine sm, MQOElement input) {

        boolean ret = true;

        switch (input) {
            case NOP:
            case FLOAT:
                break;
            default:
                break;
        }

        return ret;
    }

}