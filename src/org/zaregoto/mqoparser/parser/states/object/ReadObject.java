package org.zaregoto.mqoparser.parser.states.object;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOObject;
import org.zaregoto.mqoparser.parser.LexicalElement;
import org.zaregoto.mqoparser.parser.State;
import org.zaregoto.mqoparser.parser.StateMachine;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;


public class ReadObject extends State {

    private MQOObject object;
    private String name = null;

    @Override
    public String getStateName() {
        return this.getClass().getName();
    }

    @Override
    public boolean preTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
            case CHUNK_OBJECT:
                object = new MQOObject();
                object.setName(name);
                sm.push(object);
                break;
            case ATTR_V:
            case ATTR_M:
            case ATTR_UV:
            case ATTR_COL:
            case ATTR_CRS:
                break;
            case ENTER_CODE:
                break;
            case PAREN_CHUNK_END:
                break;
        }

        return true;
    }

    @Override
    public boolean postTransfer(StateMachine sm, LexicalElement input) throws StateTransferException {
        switch (input) {
            case PAREN_CHUNK_END:
                sm.pop(); // drop MQOObject data
                MQOData data = sm.getMqodata();
                data.addObject(object);
                break;
        }

        return true;
    }

    @Override
    public boolean received(StateMachine sm, LexicalElement input) throws StateTransferException {

        switch (input) {
            case STRING:
                if (null == name) {
                    name = input.getValue();
                }
                break;
        }

        return true;
    }
}
