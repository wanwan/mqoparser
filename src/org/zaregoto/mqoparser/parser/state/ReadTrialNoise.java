package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadTrialNoise implements State {

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {
        return true;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {
        return true;
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

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
