package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOBackImage;
import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOScene;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadBackImage implements State {

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {

        boolean ret = true;

        switch (input) {
        case CHUNK_END:
            //MQOBackImage backImage = (MQOBackImage) sm.pop();
            MQOData data = sm.getMqodata();
            //data.setBackImage(backImage);
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
        case CHUNK_BEGIN:
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
