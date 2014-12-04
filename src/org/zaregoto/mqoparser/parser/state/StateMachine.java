package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.parser.MQOElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;
import org.zaregoto.mqoparser.util.LogUtil;

import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;


public class StateMachine {

    private State current;
    private Stack<Object> stack;

    private MQOData mqodata;

    public StateMachine() {
        stack = new Stack<Object>();
        mqodata = new MQOData();
    }

    private class TBL {
        Class current;
        MQOElement input;
        Class next;

        TBL(Class current, MQOElement input, Class next) {
            this.current = current;
            this.input = input;
            this.next = next;
        }
    }


    TBL[] table = new TBL[]{
            new TBL(Init.class,       MQOElement.HEADER_METASEQUOIA,              ReadHeader.class),
            new TBL(Init.class,       MQOElement.HEADER_KEYWORD_DOCUMENT,         Init.class      ),
            new TBL(Init.class,       MQOElement.HEADER_FORMAT,                   Init.class      ),
            new TBL(Init.class,       MQOElement.HEADER_KEYWORD_VER,              Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_TRIAL_NOISE,               Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_INCLUDE_XML,               Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_SCENE,                     Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_BACKIMAGE,                 Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_MATERIAL,                  Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT,                    Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_VERTEX,             Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR,        Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_BVERTEX_VECTOR,     Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_BVERTEX_WEIT,       Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_BVERTEX_COLOR,      Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_OBJECT_FACE,               Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_BLOB,                      Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_BEGIN,                     Init.class      ),
            new TBL(Init.class,       MQOElement.CHUNK_END,                       Init.class      ),
            new TBL(Init.class,       MQOElement.INDEX_ATTR_BEGIN,                Init.class      ),
            new TBL(Init.class,       MQOElement.INDEX_ATTR_END,                  Init.class      ),
            new TBL(Init.class,       MQOElement.STRING,                          Init.class      ),
            new TBL(Init.class,       MQOElement.INT,                             Init.class      ),
            new TBL(Init.class,       MQOElement.FLOAT,                           Init.class      ),
            new TBL(Init.class,       MQOElement.BYTE_ARRAY,                      Init.class      ),

            new TBL(ReadHeader.class,       MQOElement.HEADER_METASEQUOIA,              ReadHeader.class),
            new TBL(ReadHeader.class,       MQOElement.HEADER_KEYWORD_DOCUMENT,         ReadHeader.class),
            new TBL(ReadHeader.class,       MQOElement.HEADER_FORMAT,                   ReadHeaderFormat.class),
            new TBL(ReadHeader.class,       MQOElement.HEADER_KEYWORD_VER,              ReadHeaderVer.class   ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_TRIAL_NOISE,               Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_INCLUDE_XML,               Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_SCENE,                     Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_BACKIMAGE,                 Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_MATERIAL,                  Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT,                    Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX,             Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR,        Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX_VECTOR,     Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX_WEIT,       Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX_COLOR,      Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_FACE,               Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_BLOB,                      Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_BEGIN,                     Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_END,                       Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.INDEX_ATTR_BEGIN,                Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.INDEX_ATTR_END,                  Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.STRING,                          Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.INT,                             Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.FLOAT,                           Init.class      ),
            new TBL(ReadHeader.class,       MQOElement.BYTE_ARRAY,                      Init.class      ),

            new TBL(ReadHeaderFormat.class, MQOElement.BYTE_ARRAY,                      ReadHeader.class),

            new TBL(ReadHeaderVer.class,    MQOElement.FLOAT,                           Init.class      ),

            null
    };

    public synchronized void push(Object obj) {
        stack.push(obj);
    }

    public synchronized Object pop() {
        return stack.pop();
    }


    public void init() throws StateTransferException {

        current = new Init();

        if (current.preTransfer(this, MQOElement.NOP)) {
            // TODO: do nothing?
        }
        else {
            throw new StateTransferException("init status before return false");
        }
    }


    public void transfer(final MQOElement input) throws StateTransferException {

        Class next;
        State nextState = null;

        for (TBL element: table) {
            if (null != element) {
                if (current.getClass().equals(element.current)
                        && input.ordinal() == element.input.ordinal()) {
                    next = element.next;

                    if (!current.getClass().equals(next)) {

                        try {
                            nextState = (State) next.newInstance();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        if (null != current && current.postTransfer(this, input)) {
                            if (null != nextState && nextState.preTransfer(this, input)) {
                                current = nextState;
                                LogUtil.d("StateMachine.transfer current: " + current.getStateName() + " input: " + input + " next: " + nextState.getStateName());
                                break;
                            } else {
                                throw new StateTransferException("state transfer failed, next status preTransfer() routine return error");
                            }
                        }
                        else {
                            throw new StateTransferException("state transfer failed, current status postTransfer() routine return error");
                        }
                    } else {
                        current.preTransfer(this, input);
                        break;
                    }
                }
            }
            else {
                throw new StateTransferException("cannot find transfer table element");
            }
        }
    }

    public MQOData getMqodata() {
        return mqodata;
    }

    public void setMqodata(MQOData mqodata) {
        this.mqodata = mqodata;
    }


}
