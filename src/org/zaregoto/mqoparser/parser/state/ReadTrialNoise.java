package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.MQOElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadTrialNoise implements State {

    private final String stateName = "ReadTrialNoise";

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean postTransfer(StateMachine sm, MQOElement input) {
        return true;
    }

    @Override
    public boolean preTransfer(StateMachine sm, MQOElement input) throws StateTransferException {

        MQOHeader hdr = null;
        boolean ret = false;

        switch (input) {
            case CHUNK_TRIAL_NOISE:
                throw new StateTransferException("TrialNoise detect");
            default:
                break;
        }

        return ret;
    }

}
