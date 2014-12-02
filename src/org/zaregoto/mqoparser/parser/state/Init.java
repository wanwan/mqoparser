package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;

public class Init implements State {

    @Override
    public boolean before() {
        return true;
    }

    @Override
    public boolean after() {
        return true;
    }

    @Override
    public boolean receive(MQOElement e) {

        boolean ret = false;

        switch (e) {
            case HEADER_METASEQUOIA:
                break;
            default:
                break;
        }

        return ret;
    }

    @Override
    public void run() {

    }
}
