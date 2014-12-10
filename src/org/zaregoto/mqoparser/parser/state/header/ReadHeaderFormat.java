package org.zaregoto.mqoparser.parser.state.header;

import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;
import org.zaregoto.mqoparser.parser.state.State;
import org.zaregoto.mqoparser.parser.state.StateMachine;

public class ReadHeaderFormat implements State {

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {

        MQOHeader hdr;
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
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {
        return true;
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) {

        boolean ret = false;

        switch (input) {
            case HEADER_FORMAT:
                ret = true;
                break;
        }
        return ret;
    }

}
