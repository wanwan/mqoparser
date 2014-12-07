package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.model.MQOMaterial;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

import java.util.ArrayList;


public class ReadMaterialPerLine implements State {


    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {

        boolean ret = true;

        switch (input) {
            case CHUNK_END:
                // drop material arraylist from stack
                sm.pop();
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
            case CHUNK_MATERIAL:
                MQOData data = sm.getMqodata();
                ArrayList<MQOMaterial> materials = data.getMaterials();
                sm.push(materials);
                break;
            case CHUNK_BEGIN:
                break;
            case ENTER_CODE:
                break;
            case BYTE_ARRAY:
                break;
            case FLOAT:
                break;
            case INT:
                break;
            case CHUNK_END:
                break;
            default:
                break;
        }

        return ret;
    }
}
