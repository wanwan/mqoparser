package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;


public interface State {

    public String getStateName();

    // TODO: received を増設したので ReadHeaderXX で削除可能な物を削除し無駄な遷移を見直す
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException;
    public boolean postTransfer(StateMachine sm, LexicalElement input) throws StateTransferException;
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException;
}
