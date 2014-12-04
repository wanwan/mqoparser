package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOHeader;
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

        boolean ret = false;

        switch (input) {
            case NOP:
                ret = true;
                break;
            case FLOAT:
                ret = true;
            default:
                break;
        }

        return ret;
    }

}
