package org.zaregoto.mqoparser.parser;

import org.zaregoto.mqoparser.util.LogUtil;

public enum LexicalElement {
    NOP(""),
    HEADER_METASEQUOIA("Metasequoia"),
    HEADER_KEYWORD_DOCUMENT("Document"),
    HEADER_FORMAT("Format"),
    HEADER_KEYWORD_VER("Ver"),
    CHUNK_TRIAL_NOISE("TrialNoise"),
    CHUNK_INCLUDE_XML("IncludeXml"),
    CHUNK_SCENE("Scene"),
    CHUNK_SCENE_POS("pos"),
    CHUNK_SCENE_LOOKAT("lookat"),
    CHUNK_SCENE_HEAD("head"),
    CHUNK_SCENE_PICH("pich"),
    CHUNK_SCENE_ORTHO("ortho"),
    CHUNK_SCENE_ZOOM2("zoom2"),
    CHUNK_SCENE_AMB("amb"),
    CHUNK_BACKIMAGE("BackImage"),
    CHUNK_MATERIAL("Material"),
    CHUNK_MATERIAL_SHADER("shader"),
    CHUNK_MATERIAL_VCOL("vcol"),
    CHUNK_MATERIAL_DBLS("dbls"),
    CHUNK_MATERIAL_COL("col"),
    CHUNK_MATERIAL_DIF("dif"),
    CHUNK_MATERIAL_AMB("amb"),
    CHUNK_MATERIAL_EMI("emi"),
    CHUNK_MATERIAL_SPC("spc"),
    CHUNK_MATERIAL_POWER("power"),
    CHUNK_MATERIAL_REFLECT("reflect"),
    CHUNK_MATERIAL_REFRECT("refrect"),
    CHUNK_MATERIAL_TEX("tex"),
    CHUNK_MATERIAL_APLANE("aplane"),
    CHUNK_MATERIAL_BUMP("bump"),
    CHUNK_MATERIAL_PROJ_TYPE("proj_type"),
    CHUNK_MATERIAL_PROJ_POS("proj_pos"),
    CHUNK_MATERIAL_PROJ_SCALE("proj_scale"),
    CHUNK_MATERIAL_PROJ_ANGLE("proj_angle"),
    CHUNK_OBJECT("Object"),
    CHUNK_OBJECT_UID("uid"),
    CHUNK_OBJECT_DEPTH("depth"),
    CHUNK_OBJECT_FOLDING("folding"),
    CHUNK_OBJECT_SCALE("scale"),
    CHUNK_OBJECT_ROTATION("rotation"),
    CHUNK_OBJECT_TRANSLATION("translation"),
    CHUNK_OBJECT_PATCH("patch"),
    CHUNK_OBJECT_PATCHTRI("patchtri"),
    CHUNK_OBJECT_SEGMENT("segment"),
    CHUNK_OBJECT_VISIBLE("visible"),
    CHUNK_OBJECT_LOCKING("locking"),
    CHUNK_OBJECT_SHADING("shading"),
    CHUNK_OBJECT_FACET("facet"),
    CHUNK_OBJECT_COLOR("color"),
    CHUNK_OBJECT_COLOR_TYPE("color_type"),
    CHUNK_OBJECT_MIRROR("mirror"),
    CHUNK_OBJECT_MIRROR_AXIS("mirror_axis"),
    CHUNK_OBJECT_MIRROR_DIS("mirror_dis"),
    CHUNK_OBJECT_LATHE("lathe"),
    CHUNK_OBJECT_LATHE_AXIS("lathe_axis"),
    CHUNK_OBJECT_LATHE_SEG("lathe_seg"),
    CHUNK_OBJECT_VERTEX("vertex"),
    CHUNK_OBJECT_VERTEX_ATTR("vertexattr"),
    CHUNK_OBJECT_VERTEX_ATTR_UID("uid"),
    CHUNK_OBJECT_VERTEX_ATTR_WEIT("weit"),
    CHUNK_OBJECT_VERTEX_ATTR_COLOR("color"),
    CHUNK_OBJECT_BVERTEX("BVertex"),
    CHUNK_OBJECT_BVERTEX_VECTOR("vector"),
    CHUNK_OBJECT_BVERTEX_WEIT("weit"),
    CHUNK_OBJECT_BVERTEX_COLOR("color"),
    CHUNK_OBJECT_FACE("face"),
    CHUNK_BLOB("BLOB"),
    CHUNK_BEGIN("{"),
    CHUNK_END("}"),
    PAREN_BEGIN("("),
    PAREN_END(")"),
    STRING(null),
    INT(null),
    FLOAT(null),
    BYTE_ARRAY(null),
    ENTER_CODE(null);

    String word;
    String value;

    LexicalElement(String word) {
        this.word = word;
    }

    public boolean equals(String _word) {
        boolean ret = false;

        if (null == this.word || null == _word) {
            ret = false;
        }
        else {
            if (0 == this.word.toUpperCase().compareTo(_word.trim().toUpperCase())) {
                ret = true;
            }
            else {
                ret = false;
            }
        }

        return ret;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void dump() {
        StringBuffer b = new StringBuffer();
        b.append(this.name());
        b.append(" word: " + word);
        b.append(" value: " + value);

        LogUtil.d(b.toString());
    }
}
