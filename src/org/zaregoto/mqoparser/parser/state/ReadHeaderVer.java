package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.MQOElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadHeaderVer implements State {

    private final String stateName = "ReadHeaderVer";

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean postTransfer(StateMachine sm, MQOElement input) {

        MQOHeader hdr = null;
        boolean ret = true;

        switch (input) {
            case FLOAT:
                hdr = (MQOHeader)sm.pop();
                if (null != input && null != input.getValue()) {
                    hdr.setVersion(new Float(input.getValue()));
                }
                else {
                    hdr.setVersion(null);
                }
                //sm.push(hdr);
                MQOData mqoData = sm.getMqodata();
                mqoData.setHeader(hdr);
                ret = true;
                break;
            default:
                ret = false;
                break;
        }

        return ret;
    }

    @Override
    public boolean received(StateMachine sm, MQOElement input) throws StateTransferException {
        return false;
    }

    @Override
    public boolean preTransfer(StateMachine sm, MQOElement input) {

        MQOHeader hdr = null;
        boolean ret = true;

        switch (input) {
            case HEADER_KEYWORD_VER:
                ret = true;
                break;
            default:
                ret = false;
                break;
        }

        return ret;
    }

}
