package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.parser.MQOElement;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;
import org.zaregoto.mqoparser.util.LogUtil;

import java.util.Stack;


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
            new TBL(Idle.class,       MQOElement.HEADER_METASEQUOIA,              ReadHeader.class          ),
            new TBL(Idle.class,       MQOElement.HEADER_KEYWORD_DOCUMENT,         Idle.class                ),
            new TBL(Idle.class,       MQOElement.HEADER_FORMAT,                   Idle.class                ),
            new TBL(Idle.class,       MQOElement.HEADER_KEYWORD_VER,              Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_TRIAL_NOISE,               ReadTrialNoise.class      ),
            new TBL(Idle.class,       MQOElement.CHUNK_INCLUDE_XML,               ReadIncludeXml.class      ),
            new TBL(Idle.class,       MQOElement.CHUNK_SCENE,                     Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_BACKIMAGE,                 Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_MATERIAL,                  Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT,                    Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_VERTEX,             Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR,        Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_BVERTEX_VECTOR,     Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_BVERTEX_WEIT,       Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_BVERTEX_COLOR,      Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_OBJECT_FACE,               Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_BLOB,                      Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_BEGIN,                     Idle.class                ),
            new TBL(Idle.class,       MQOElement.CHUNK_END,                       Idle.class                ),
            new TBL(Idle.class,       MQOElement.INDEX_ATTR_BEGIN,                Idle.class                ),
            new TBL(Idle.class,       MQOElement.INDEX_ATTR_END,                  Idle.class                ),
            new TBL(Idle.class,       MQOElement.STRING,                          Idle.class                ),
            new TBL(Idle.class,       MQOElement.INT,                             Idle.class                ),
            new TBL(Idle.class,       MQOElement.FLOAT,                           Idle.class                ),
            new TBL(Idle.class,       MQOElement.BYTE_ARRAY,                      Idle.class                ),

            new TBL(ReadHeader.class,       MQOElement.HEADER_METASEQUOIA,              ReadHeader.class      ),
            new TBL(ReadHeader.class,       MQOElement.HEADER_KEYWORD_DOCUMENT,         ReadHeader.class      ),
            new TBL(ReadHeader.class,       MQOElement.HEADER_FORMAT,                   ReadHeaderFormat.class),
            new TBL(ReadHeader.class,       MQOElement.HEADER_KEYWORD_VER,              ReadHeaderVer.class   ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_TRIAL_NOISE,               Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_INCLUDE_XML,               Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_SCENE,                     Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_BACKIMAGE,                 Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_MATERIAL,                  Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT,                    Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX,             Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR,        Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX_VECTOR,     Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX_WEIT,       Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_BVERTEX_COLOR,      Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_OBJECT_FACE,               Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_BLOB,                      Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_BEGIN,                     Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.CHUNK_END,                       Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.INDEX_ATTR_BEGIN,                Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.INDEX_ATTR_END,                  Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.STRING,                          Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.INT,                             Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.FLOAT,                           Idle.class            ),
            new TBL(ReadHeader.class,       MQOElement.BYTE_ARRAY,                      Idle.class            ),

            new TBL(ReadHeaderFormat.class,       MQOElement.HEADER_METASEQUOIA,              ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.HEADER_KEYWORD_DOCUMENT,         ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.HEADER_FORMAT,                   ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.HEADER_KEYWORD_VER,              ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_TRIAL_NOISE,               ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_INCLUDE_XML,               ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_SCENE,                     ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_BACKIMAGE,                 ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_MATERIAL,                  ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT,                    ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_VERTEX,             ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_OBJECT_FACE,               ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_BLOB,                      ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_BEGIN,                     ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.CHUNK_END,                       ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.INDEX_ATTR_BEGIN,                ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.INDEX_ATTR_END,                  ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.STRING,                          ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.INT,                             ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.FLOAT,                           ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       MQOElement.BYTE_ARRAY,                      ReadHeader.class      ),

            new TBL(ReadHeaderVer.class,       MQOElement.HEADER_METASEQUOIA,              ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.HEADER_KEYWORD_DOCUMENT,         ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.HEADER_FORMAT,                   ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.HEADER_KEYWORD_VER,              ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_TRIAL_NOISE,               ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_INCLUDE_XML,               ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_SCENE,                     ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_BACKIMAGE,                 ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_MATERIAL,                  ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT,                    ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_VERTEX,             ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_OBJECT_FACE,               ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_BLOB,                      ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_BEGIN,                     ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.CHUNK_END,                       ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.INDEX_ATTR_BEGIN,                ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.INDEX_ATTR_END,                  ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.STRING,                          ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.INT,                             ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       MQOElement.FLOAT,                           Idle.class         ),
            new TBL(ReadHeaderVer.class,       MQOElement.BYTE_ARRAY,                      ReadHeaderVer.class),

            new TBL(ReadIncludeXml.class,       MQOElement.HEADER_METASEQUOIA,              ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.HEADER_KEYWORD_DOCUMENT,         ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.HEADER_FORMAT,                   ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.HEADER_KEYWORD_VER,              ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_TRIAL_NOISE,               ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_INCLUDE_XML,               ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_SCENE,                     ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_BACKIMAGE,                 ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_MATERIAL,                  ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT,                    ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_VERTEX,             ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_BVERTEX,            ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_OBJECT_FACE,               ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_BLOB,                      ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_BEGIN,                     ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.CHUNK_END,                       ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.INDEX_ATTR_BEGIN,                ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.INDEX_ATTR_END,                  ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.STRING,                          Idle.class                          ),
            new TBL(ReadIncludeXml.class,       MQOElement.INT,                             ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.FLOAT,                           ReadIncludeXml.class                ),
            new TBL(ReadIncludeXml.class,       MQOElement.BYTE_ARRAY,                      ReadIncludeXml.class                ),
            

            null
    };

    public synchronized void push(Object obj) {
        stack.push(obj);
    }

    public synchronized Object pop() {
        return stack.pop();
    }


    public void init() throws StateTransferException {

        current = new Idle();

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
                        } else {
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
