package org.zaregoto.mqoparser.parser;

import org.zaregoto.mqoparser.parser.exception.StateTransferException;


public abstract class State {

    public abstract String getStateName();

    public abstract boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException;
    public abstract boolean postTransfer(StateMachine sm, LexicalElement input) throws StateTransferException;
    public abstract boolean received(StateMachine sm, LexicalElement input) throws StateTransferException;

    @Override
    public int hashCode() {
        return getClass().getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        boolean ret = false;

        if (null != obj) {
            if (this.getClass() == obj.getClass()) {
                ret = true;
            }
        }
        return ret;
    }
}
