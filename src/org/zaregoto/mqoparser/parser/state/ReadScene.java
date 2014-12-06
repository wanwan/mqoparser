package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.model.MQOScene;
import org.zaregoto.mqoparser.parser.MQOElement;

public class ReadScene implements State {

    private final String stateName = "ReadScene";

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public boolean postTransfer(StateMachine sm, MQOElement input) {
        return true;
    }

    @Override
    public boolean preTransfer(StateMachine sm, MQOElement input) {

        MQOScene scene = null;
        boolean ret = true;

        switch (input) {
        case CHUNK_SCENE:
            scene = new MQOScene();
            sm.push(scene);
            ret = true;
            break;
        default:
            break;
        }

        return ret;
    }
}