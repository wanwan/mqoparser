package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;


public interface State extends Runnable {

    public boolean receive(MQOElement e);
}
