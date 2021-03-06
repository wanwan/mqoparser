package org.zaregoto.mqoparser.parser.states;

import org.zaregoto.mqoparser.model.MQOBackImage;
import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.exception.StateTransferException;

public class ReadBackImage extends State {

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {

        boolean ret = true;

        switch (input) {
        case PAREN_CHUNK_END:
            MQOBackImage backImage = (MQOBackImage) sm.pop();
            MQOData data = sm.getMqodata();
            data.setBackImage(backImage);
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

        MQOBackImage backimage = null;
        boolean ret = true;

        switch (input) {
        case CHUNK_BACKIMAGE:
            backimage = new MQOBackImage();
            sm.push(backimage);
            break;
        case PAREN_CHUNK_BEGIN:
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
