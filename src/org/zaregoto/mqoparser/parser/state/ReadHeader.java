package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;

public class ReadHeader extends AbstractState {
    @Override
    public boolean receive(MQOElement e) {
        return false;
    }

    @Override
    public void run() {

    }
}
