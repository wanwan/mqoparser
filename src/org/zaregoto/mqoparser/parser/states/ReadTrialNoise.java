package org.zaregoto.mqoparser.parser.states;

import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.exception.StateTransferException;

public class ReadTrialNoise extends State {

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
