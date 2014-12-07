package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.model.MQOIncludeXml;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadIncludeXml implements State {

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {

        boolean ret = true;

        switch (input) {
            case STRING:
                MQOIncludeXml xml = new MQOIncludeXml();
                xml.setFilename(input.getValue());
                MQOData data = sm.getMqodata();
                data.addIncludeXml(xml);
                break;
            default:
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
            case CHUNK_INCLUDE_XML:
                ret = true;
                break;
            default:
                break;
        }

        return ret;
    }

}
