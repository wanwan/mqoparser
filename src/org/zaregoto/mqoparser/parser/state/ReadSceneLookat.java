package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;


public class ReadSceneLookat implements State {
    @Override
    public String getStateName() {
        return null;
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {
        return true;
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {
        return true;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {
        return true;
    }
}
