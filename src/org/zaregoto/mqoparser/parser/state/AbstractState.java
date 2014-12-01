package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.parser.MQOElement;


public abstract class AbstractState implements State {

    private static State current;

    protected AbstractState() {
    }

    public abstract boolean receive(MQOElement e);
    public abstract void run();

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
            new TBL(ReadHeader.class,       MQOElement.HEADER_KEYWORD_DOCUMENT,         ReadHeader.class      ),
            new TBL(ReadHeader.class,       MQOElement.HEADER_FORMAT,                   ReadHeader.class      ),
            new TBL(ReadHeader.class,       MQOElement.HEADER_KEYWORD_VER,              Init.class      ),
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
            

            null
    };

    protected void transfer() {
    }
}
