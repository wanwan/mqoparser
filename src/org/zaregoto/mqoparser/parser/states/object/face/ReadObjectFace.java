package org.zaregoto.mqoparser.parser.states.object.face;

import org.zaregoto.mqoparser.model.MQOObject;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;


public class ReadObjectFace extends State {

    MQOObject.MQOFace face;

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
        case CHUNK_FACE:
            MQOObject.MQOFace face = new MQOObject.MQOFace();
            sm.push(face);
            break;
        case PAREN_CHUNK_END:
            Object subchunk = sm.pop();
            if (subchunk instanceof MQOObject.MQOFace) {
            }
            break;
        }

        return true;
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {



        return true;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {
        return true;
    }
}
