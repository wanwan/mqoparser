package org.zaregoto.mqoparser.parser.states.scene;

import org.zaregoto.mqoparser.model.MQOScene;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.exception.StateTransferException;


public class ReadScenePos extends State {

    enum STATUS {
        READ_X,
        READ_Y,
        READ_Z
    }

    private STATUS current;
    private MQOScene.Pos pos;

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        boolean ret = true;

        switch (input) {
            case ATTR_POS:
                current = STATUS.READ_X;
                pos = new MQOScene.Pos();
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
                scene.setPos(pos);
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
                        pos.setX(Float.valueOf(input.getValue()));
                        current = STATUS.READ_Y;
                        break;
                    case READ_Y:
                        pos.setY(Float.valueOf(input.getValue()));
                        current = STATUS.READ_Z;
                        break;
                    case READ_Z:
                        pos.setZ(Float.valueOf(input.getValue()));
                        break;
                }
                break;
            case PAREN_CHUNK_END:
                // TODO: write chenkend op when scene read
                break;
            default:
                break;
        }

        return ret;
    }
}
