package org.zaregoto.mqoparser.parser.states.scene;

import org.zaregoto.mqoparser.model.MQOScene;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

public class ReadScenePich extends State {

    private MQOScene.Pich pich;

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        boolean ret = true;

        switch (input) {
            case ATTR_PICH:
                pich = new MQOScene.Pich();
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
                scene.setPich(pich);
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
                pich.setValue(Float.valueOf(input.getValue()));
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
