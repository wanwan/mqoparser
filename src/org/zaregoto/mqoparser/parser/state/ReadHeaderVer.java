package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.MQOElement;

public class ReadHeaderVer implements State {

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
            case FLOAT:
                hdr = (MQOHeader)sm.pop();
                if (null != input && null != input.getValue()) {
                    hdr.setVersion(new Float(input.getValue()));
                }
                else {
                    hdr.setVersion(null);
                }
                sm.push(hdr);
                ret = true;
                break;
            default:
                break;
        }

        return ret;
    }

}
