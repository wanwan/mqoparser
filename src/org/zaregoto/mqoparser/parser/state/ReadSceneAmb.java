package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOScene;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadSceneAmb implements State {

    enum STATUS {
        READ_X,
        READ_Y,
        READ_Z;
    };

    private STATUS current;
    private MQOScene.Amb amb;

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        boolean ret = true;

        switch (input) {
            case CHUNK_SCENE_AMB:
                current = STATUS.READ_X;
                amb = new MQOScene.Amb();
                break;
            default:
                break;
        }

        return ret;
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        boolean ret = true;

        switch (input) {
            case ENTER_CODE:
                MQOScene scene = (MQOScene) sm.pop();
                scene.setAmb(amb);
                sm.push(scene);
                break;
            default:
                break;
        }

        return ret;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {

        boolean ret = true;

        switch (input) {
            case FLOAT:
                switch (current) {
                    case READ_X:
                        amb.setX(Float.valueOf(input.getValue()));
                        current = STATUS.READ_Y;
                        break;
                    case READ_Y:
                        amb.setY(Float.valueOf(input.getValue()));
                        current = STATUS.READ_Z;
                        break;
                    case READ_Z:
                        amb.setZ(Float.valueOf(input.getValue()));
                        break;
                }
                break;
            case CHUNK_END:
                // TODO: write chenkend op when scene read
                break;
            default:
                break;
        }

        return ret;
    }

}
