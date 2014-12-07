package org.zaregoto.mqoparser.parser.state;

import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.parser.LexicalElement;
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
        LexicalElement input;
        Class next;

        TBL(Class current, LexicalElement input, Class next) {
            this.current = current;
            this.input = input;
            this.next = next;
        }
    }


    TBL[] table = new TBL[]{
            new TBL(Idle.class,       LexicalElement.HEADER_METASEQUOIA,              ReadHeader.class          ),
            new TBL(Idle.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         Idle.class                ),
            new TBL(Idle.class,       LexicalElement.HEADER_FORMAT,                   Idle.class                ),
            new TBL(Idle.class,       LexicalElement.HEADER_KEYWORD_VER,              Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadTrialNoise.class      ),
            new TBL(Idle.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadIncludeXml.class      ),
            new TBL(Idle.class,       LexicalElement.CHUNK_SCENE,                     ReadScene.class           ),
            new TBL(Idle.class,       LexicalElement.CHUNK_SCENE_POS,                 Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_SCENE_HEAD,                Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_SCENE_PICH,                Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_SCENE_ORTHO,               Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_SCENE_AMB,                 Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_BACKIMAGE,                 Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_MATERIAL,                  Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT,                    Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_OBJECT_FACE,               Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_BLOB,                      Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_BEGIN,                     Idle.class                ),
            new TBL(Idle.class,       LexicalElement.CHUNK_END,                       Idle.class                ),
            new TBL(Idle.class,       LexicalElement.INDEX_ATTR_BEGIN,                Idle.class                ),
            new TBL(Idle.class,       LexicalElement.INDEX_ATTR_END,                  Idle.class                ),
            new TBL(Idle.class,       LexicalElement.STRING,                          Idle.class                ),
            new TBL(Idle.class,       LexicalElement.INT,                             Idle.class                ),
            new TBL(Idle.class,       LexicalElement.FLOAT,                           Idle.class                ),
            new TBL(Idle.class,       LexicalElement.BYTE_ARRAY,                      Idle.class                ),
            new TBL(Idle.class,       LexicalElement.ENTER_CODE,                      Idle.class                ),

            new TBL(ReadHeader.class,       LexicalElement.HEADER_METASEQUOIA,              ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.HEADER_FORMAT,                   ReadHeaderFormat.class),
            new TBL(ReadHeader.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadHeaderVer.class   ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_SCENE,                     ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadHeader.class      ),            
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_MATERIAL,                  ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT,                    ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_BLOB,                      ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_BEGIN,                     ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.CHUNK_END,                       ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.INDEX_ATTR_END,                  ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.STRING,                          ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.INT,                             ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.FLOAT,                           ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.BYTE_ARRAY,                      ReadHeader.class      ),
            new TBL(ReadHeader.class,       LexicalElement.ENTER_CODE,                      ReadHeader.class      ),

            new TBL(ReadHeaderFormat.class,       LexicalElement.HEADER_METASEQUOIA,              ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.HEADER_FORMAT,                   ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_SCENE,                     ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_MATERIAL,                  ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT,                    ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_BLOB,                      ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_BEGIN,                     ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.CHUNK_END,                       ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.INDEX_ATTR_END,                  ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.STRING,                          ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.INT,                             ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.FLOAT,                           ReadHeaderFormat.class),
            new TBL(ReadHeaderFormat.class,       LexicalElement.BYTE_ARRAY,                      ReadHeader.class      ),
            new TBL(ReadHeaderFormat.class,       LexicalElement.ENTER_CODE,                      ReadHeader.class      ),

            new TBL(ReadHeaderVer.class,       LexicalElement.HEADER_METASEQUOIA,              ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.HEADER_FORMAT,                   ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_SCENE,                     ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadHeaderVer.class),            
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_MATERIAL,                  ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT,                    ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_BLOB,                      ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_BEGIN,                     ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.CHUNK_END,                       ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.INDEX_ATTR_END,                  ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.STRING,                          ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.INT,                             ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.FLOAT,                           Idle.class         ),
            new TBL(ReadHeaderVer.class,       LexicalElement.BYTE_ARRAY,                      ReadHeaderVer.class),
            new TBL(ReadHeaderVer.class,       LexicalElement.ENTER_CODE,                      ReadHeaderVer.class),

            new TBL(ReadIncludeXml.class,       LexicalElement.HEADER_METASEQUOIA,              ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.HEADER_FORMAT,                   ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_SCENE,                     ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_MATERIAL,                  ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT,                    ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_BLOB,                      ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_BEGIN,                     ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.CHUNK_END,                       ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.INDEX_ATTR_END,                  ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.STRING,                          Idle.class          ),
            new TBL(ReadIncludeXml.class,       LexicalElement.INT,                             ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.FLOAT,                           ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.BYTE_ARRAY,                      ReadIncludeXml.class),
            new TBL(ReadIncludeXml.class,       LexicalElement.ENTER_CODE,                      ReadIncludeXml.class),

            new TBL(ReadScene.class,       LexicalElement.HEADER_METASEQUOIA,              ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.HEADER_FORMAT,                   ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_SCENE,                     ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadScenePos.class             ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadSceneLookat.class          ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadSceneHead.class            ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadScenePich.class            ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadSceneOrtho.class           ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadSceneZoom2.class           ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadSceneAmb.class             ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_MATERIAL,                  ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT,                    ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_BLOB,                      ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_BEGIN,                     ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.CHUNK_END,                       Idle.class                     ),
            new TBL(ReadScene.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.INDEX_ATTR_END,                  ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.STRING,                          ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.INT,                             ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.FLOAT,                           ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.BYTE_ARRAY,                      ReadScene.class                ),
            new TBL(ReadScene.class,       LexicalElement.ENTER_CODE,                      ReadScene.class                ),
            
            new TBL(ReadScenePos.class,       LexicalElement.HEADER_METASEQUOIA,              ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.HEADER_FORMAT,                   ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_SCENE,                     ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_MATERIAL,                  ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT,                    ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_BLOB,                      ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_BEGIN,                     ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.CHUNK_END,                       Idle.class        ),
            new TBL(ReadScenePos.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.INDEX_ATTR_END,                  ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.STRING,                          ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.INT,                             ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.FLOAT,                           ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.BYTE_ARRAY,                      ReadScenePos.class),
            new TBL(ReadScenePos.class,       LexicalElement.ENTER_CODE,                      ReadScene.class   ),

            new TBL(ReadSceneLookat.class,       LexicalElement.HEADER_METASEQUOIA,              ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.HEADER_FORMAT,                   ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_SCENE,                     ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_MATERIAL,                  ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT,                    ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_BLOB,                      ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_BEGIN,                     ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.CHUNK_END,                       Idle.class        ),
            new TBL(ReadSceneLookat.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.INDEX_ATTR_END,                  ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.STRING,                          ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.INT,                             ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.FLOAT,                           ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.BYTE_ARRAY,                      ReadSceneLookat.class),
            new TBL(ReadSceneLookat.class,       LexicalElement.ENTER_CODE,                      ReadScene.class      ),

            new TBL(ReadSceneHead.class,       LexicalElement.HEADER_METASEQUOIA,              ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.HEADER_FORMAT,                   ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_SCENE,                     ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_MATERIAL,                  ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT,                    ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_BLOB,                      ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_BEGIN,                     ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.CHUNK_END,                       Idle.class        ),
            new TBL(ReadSceneHead.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.INDEX_ATTR_END,                  ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.STRING,                          ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.INT,                             ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.FLOAT,                           ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.BYTE_ARRAY,                      ReadSceneHead.class),
            new TBL(ReadSceneHead.class,       LexicalElement.ENTER_CODE,                      ReadScene.class    ),

            new TBL(ReadScenePich.class,       LexicalElement.HEADER_METASEQUOIA,              ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.HEADER_FORMAT,                   ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_SCENE,                     ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_MATERIAL,                  ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT,                    ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_BLOB,                      ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_BEGIN,                     ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.CHUNK_END,                       Idle.class        ),
            new TBL(ReadScenePich.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.INDEX_ATTR_END,                  ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.STRING,                          ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.INT,                             ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.FLOAT,                           ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.BYTE_ARRAY,                      ReadScenePich.class),
            new TBL(ReadScenePich.class,       LexicalElement.ENTER_CODE,                      ReadScene.class    ),

            new TBL(ReadSceneOrtho.class,       LexicalElement.HEADER_METASEQUOIA,              ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.HEADER_FORMAT,                   ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_SCENE,                     ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_MATERIAL,                  ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT,                    ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_BLOB,                      ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_BEGIN,                     ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.CHUNK_END,                       Idle.class        ),
            new TBL(ReadSceneOrtho.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.INDEX_ATTR_END,                  ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.STRING,                          ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.INT,                             ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.FLOAT,                           ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.BYTE_ARRAY,                      ReadSceneOrtho.class),
            new TBL(ReadSceneOrtho.class,       LexicalElement.ENTER_CODE,                      ReadScene.class     ),

            new TBL(ReadSceneZoom2.class,       LexicalElement.HEADER_METASEQUOIA,              ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.HEADER_FORMAT,                   ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_SCENE,                     ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_MATERIAL,                  ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT,                    ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_BLOB,                      ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_BEGIN,                     ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.CHUNK_END,                       Idle.class        ),
            new TBL(ReadSceneZoom2.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.INDEX_ATTR_END,                  ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.STRING,                          ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.INT,                             ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.FLOAT,                           ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.BYTE_ARRAY,                      ReadSceneZoom2.class),
            new TBL(ReadSceneZoom2.class,       LexicalElement.ENTER_CODE,                      ReadScene.class     ),

            new TBL(ReadSceneAmb.class,       LexicalElement.HEADER_METASEQUOIA,              ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.HEADER_KEYWORD_DOCUMENT,         ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.HEADER_FORMAT,                   ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.HEADER_KEYWORD_VER,              ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_TRIAL_NOISE,               ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_INCLUDE_XML,               ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_SCENE,                     ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_SCENE_POS,                 ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_SCENE_LOOKAT,              ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_SCENE_HEAD,                ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_SCENE_PICH,                ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_SCENE_ORTHO,               ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_SCENE_ZOOM2,               ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_SCENE_AMB,                 ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_BACKIMAGE,                 ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_MATERIAL,                  ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT,                    ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_VERTEX,             ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR,        ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_UID,    ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_WEIT,   ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_VERTEX_ATTR_COLOR,  ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_BVERTEX,            ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_VECTOR,     ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_WEIT,       ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_BVERTEX_COLOR,      ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_OBJECT_FACE,               ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_BLOB,                      ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_BEGIN,                     ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.CHUNK_END,                       Idle.class        ),
            new TBL(ReadSceneAmb.class,       LexicalElement.INDEX_ATTR_BEGIN,                ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.INDEX_ATTR_END,                  ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.STRING,                          ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.INT,                             ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.FLOAT,                           ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.BYTE_ARRAY,                      ReadSceneAmb.class),
            new TBL(ReadSceneAmb.class,       LexicalElement.ENTER_CODE,                      ReadScene.class   ),

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

        if (current.preTransfer(this, LexicalElement.NOP)) {
            // TODO: do nothing?
        }
        else {
            throw new StateTransferException("init status before return false");
        }
    }


    public void transfer(final LexicalElement input) throws StateTransferException {

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
                        current.received(this, input);
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
