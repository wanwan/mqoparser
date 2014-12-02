package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;


public interface State extends Runnable {

    public boolean before();
    public boolean after();

    public boolean receive(MQOElement e);
}
