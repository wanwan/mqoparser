package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadHeader implements State {

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {
        return true;
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
            case HEADER_METASEQUOIA:
                hdr = new MQOHeader();
                sm.push(hdr);
                ret = true;
                break;
            case HEADER_KEYWORD_DOCUMENT:
                ret = true;
                break;
            case HEADER_FORMAT:
                hdr = (MQOHeader)sm.pop();
                hdr.setFormat(input.getValue());
                sm.push(hdr);
                ret = true;
                break;
            case HEADER_KEYWORD_VER:
                hdr = (MQOHeader)sm.pop();
                hdr.setVersion(new Float(input.getValue()));
                sm.push(hdr);
                ret = true;
                break;
            case BYTE_ARRAY:
                ret = true;
                break;
            default:
                break;
        }

        return ret;
    }

}
