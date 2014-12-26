package org.zaregoto.mqoparser.parser.states.material;


import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOMaterial;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.exception.StateTransferException;

import java.util.ArrayList;

public class ReadMaterials extends State {


    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {

        boolean ret = true;

        switch (input) {
            case PAREN_CHUNK_END:
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
            case PAREN_CHUNK_BEGIN:
                break;
            case ENTER_CODE:
                break;
            case BYTE_ARRAY:
                break;
            case FLOAT:
                break;
            case INT:
                break;
            case PAREN_CHUNK_END:
                break;
            default:
                break;
        }

        return ret;
    }
}
