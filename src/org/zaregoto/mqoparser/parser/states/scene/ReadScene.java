package org.zaregoto.mqoparser.parser.states.scene;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOScene;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.exception.StateTransferException;

public class ReadScene extends State {

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {

        boolean ret = true;

        switch (input) {
            case PAREN_CHUNK_END:
                MQOScene scene = (MQOScene) sm.pop();
                MQOData data = sm.getMqodata();
                data.setScene(scene);
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

        MQOScene scene = null;
        boolean ret = true;

        switch (input) {
            case CHUNK_SCENE:
                scene = new MQOScene();
                sm.push(scene);
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