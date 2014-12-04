package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;


public interface State {

    public String getStateName();

    public boolean preTransfer(StateMachine sm, MQOElement input) throws StateTransferException;
    public boolean postTransfer(StateMachine sm, MQOElement input) throws StateTransferException;
}
