package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;


public interface State {

    public String getStateName();

    public boolean before();
    public boolean after();

    public boolean receive(StateMachine sm, MQOElement input);
}
