package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.MQOElement;

public class ReadHeaderFormat implements State {

    private final String stateName = "ReadHeaderFormat";

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean postTransfer(StateMachine sm, MQOElement input) {

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

    @Override
    public boolean preTransfer(StateMachine sm, MQOElement input) {

        boolean ret = false;

        switch (input) {
            case HEADER_FORMAT:
                ret = true;
                break;
        }
        return ret;
    }

}
