package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;


public interface State {

    public String getStateName();


    public boolean preTransfer(StateMachine sm, MQOElement input);
    public boolean postTransfer(StateMachine sm, MQOElement input);
}
