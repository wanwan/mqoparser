package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.MQOElement;

public class ReadHeaderFormat implements State {

    private final String stateName = "ReadHeader";

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean before() {
        return true;
    }

    @Override
    public boolean after() {
        return true;
    }

    @Override
    public boolean receive(StateMachine sm, MQOElement input) {

        MQOHeader hdr = null;
        boolean ret = false;

        switch (input) {
            case BYTE_ARRAY:
                hdr = (MQOHeader)sm.pop();
                hdr.setFormat(input.getValue());
                sm.push(hdr);
                ret = true;
                break;
            default:
                break;
        }

        return ret;
    }

}
