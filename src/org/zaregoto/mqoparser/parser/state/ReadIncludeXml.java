package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.model.MQOIncludeXml;
import org.zaregoto.mqoparser.parser.MQOElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadIncludeXml implements State {

    private final String stateName = "ReadIncludeXml";

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean postTransfer(StateMachine sm, MQOElement input) {

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
    public boolean received(StateMachine sm, MQOElement input) throws StateTransferException {
        return false;
    }

    @Override
    public boolean preTransfer(StateMachine sm, MQOElement input) {

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
