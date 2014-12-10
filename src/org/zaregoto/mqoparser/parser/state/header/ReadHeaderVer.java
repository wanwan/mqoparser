package org.zaregoto.mqoparser.parser.state.header;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;
import org.zaregoto.mqoparser.parser.state.State;
import org.zaregoto.mqoparser.parser.state.StateMachine;

public class ReadHeaderVer implements State {

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {

        MQOHeader hdr;
        boolean ret = true;

        switch (input) {
            case FLOAT:
                hdr = (MQOHeader)sm.pop();
                if (null != input.getValue()) {
                    hdr.setVersion(Float.valueOf(input.getValue()));
                }
                else {
                    hdr.setVersion(null);
                }
                MQOData mqoData = sm.getMqodata();
                mqoData.setHeader(hdr);
                break;
            default:
                break;
        }

        return ret;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {
        return true;
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) {

        boolean ret = true;

        switch (input) {
            case HEADER_KEYWORD_VER:
                break;
            default:
                break;
        }

        return ret;
    }

}
