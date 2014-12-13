package org.zaregoto.mqoparser.parser.states;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOIncludeXml;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadIncludeXml extends State {

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
        return true;
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) {

        boolean ret = true;

        switch (input) {
            case CHUNK_INCLUDE_XML:
                break;
            default:
                break;
        }

        return ret;
    }

}
