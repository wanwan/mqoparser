package org.zaregoto.mqoparser.parser.states.object.face;

import org.zaregoto.mqoparser.model.MQOData;
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
            break;
        case PAREN_CHUNK_END:

            System.out.println("***** FACE CHUNK_END 1 *****");

            break;
        case ENTER_CODE:
//            MQOObject.MQOFace face = (MQOObject.MQOFace) sm.pop();
//            MQOObject data = (MQOObject) sm.pop();
//            data.addFace(face);
//            sm.push(data);
//            face = null;
            break;
        }

        return true;
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
            case ATTR_V:
            case ATTR_M:
            case ATTR_UV:
            case ATTR_COL:
            case ATTR_CRS:
                if (null == face) {
                    face = new MQOObject.MQOFace();
                    sm.push(face);
                }
                break;
            case PAREN_CHUNK_END:

                System.out.println("***** FACE CHUNK_END 2 *****");

                break;
        }

        return true;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
            case ATTR_V:
            case ATTR_M:
            case ATTR_UV:
            case ATTR_COL:
            case ATTR_CRS:
//                if (null == face) {
//                    face = new MQOObject.MQOFace();
//                    sm.push(face);
//                }
                break;
            case ENTER_CODE:
                if (null != face) {
                    sm.pop(); // drop face
                    MQOObject data = (MQOObject) sm.pop();
                    data.addFace(face);
                    sm.push(data);
                    face = null;
                }
                break;
            case PAREN_CHUNK_END:

                System.out.println("***** FACE CHUNK_END 3 *****");

                break;
        }

        return true;
    }
}
