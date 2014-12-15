package org.zaregoto.mqoparser.parser.states.object.face;

import org.zaregoto.mqoparser.model.MQOObject;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

import java.util.ArrayList;


public class ReadObjectFaceV extends State {

    private ArrayList<Integer> index;

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
            case ATTR_V:
                index = new ArrayList<Integer>();
                break;
        }

        return true;
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
            case PAREN_END:
                MQOObject.MQOFace face = (MQOObject.MQOFace) sm.pop();
                face.setV(index);
                sm.push(face);
                break;
        }

        return true;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
            case INT:
                index.add(Integer.parseInt(input.getValue()));
                break;
        }

        return true;
    }
}
