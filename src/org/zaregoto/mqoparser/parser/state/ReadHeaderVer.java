package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadHeaderVer implements State {

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {

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
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {
        return false;
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) {

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
