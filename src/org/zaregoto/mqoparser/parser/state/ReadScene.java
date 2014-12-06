package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOScene;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadScene implements State {

    private final String stateName = "ReadScene";

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) {
        return true;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {
        return false;
    }

//    CHUNK_SCENE word: Scene value: null
//    CHUNK_BEGIN word: { value: null
//        BYTE_ARRAY word: null value: pos
//        FLOAT word: null value: 0.0000
//        FLOAT word: null value: 0.0000
//        FLOAT word: null value: 1500.0000
//        BYTE_ARRAY word: null value: lookat
//        FLOAT word: null value: 0.0000
//        FLOAT word: null value: 0.0000
//        FLOAT word: null value: 0.0000
//        BYTE_ARRAY word: null value: head
//        FLOAT word: null value: -0.5236
//        BYTE_ARRAY word: null value: pich
//        FLOAT word: null value: 0.5236
//        BYTE_ARRAY word: null value: ortho
//        INT word: null value: 0
//        BYTE_ARRAY word: null value: zoom2
//        FLOAT word: null value: 5.0000
//        BYTE_ARRAY word: null value: amb
//        FLOAT word: null value: 0.250
//        FLOAT word: null value: 0.250
//        FLOAT word: null value: 0.250
//        CHUNK_END word: } value: null

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) {

        MQOScene scene = null;
        boolean ret = true;

        switch (input) {
            case CHUNK_SCENE:
                scene = new MQOScene();
                sm.push(scene);
                ret = true;
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