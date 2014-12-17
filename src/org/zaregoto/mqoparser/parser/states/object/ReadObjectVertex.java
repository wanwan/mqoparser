package org.zaregoto.mqoparser.parser.states.object;

import org.zaregoto.mqoparser.model.MQOObject;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

import java.util.ArrayList;

import static org.zaregoto.mqoparser.model.MQOObject.*;
import static org.zaregoto.mqoparser.parser.LexicalElement.*;


public class ReadObjectVertex extends State {


    private ArrayList<Float> datas;

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
        case CHUNK_VERTEX:
            if (null == datas) {
                datas = new ArrayList<Float>();
            }
            break;
        }

        return true;
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
        case PAREN_CHUNK_END:
            MQOObject.MQOVertex vertex = new MQOObject.MQOVertex(datas);
            MQOObject object = (MQOObject) sm.pop();
            object.setVertex(vertex);
            sm.push(object);
            break;
        }

        return true;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
        case FLOAT:
            datas.add(Float.valueOf(input.getValue()));
            break;
        }

        return true;
    }
}
