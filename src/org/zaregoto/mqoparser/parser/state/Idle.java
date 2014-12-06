package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

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
    public boolean received(StateMachine sm, MQOElement input) throws StateTransferException {
        return false;
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
