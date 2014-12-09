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
    ATTR_POS("pos"),
    ATTR_LOOKAT("lookat"),
    ATTR_HEAD("head"),
    ATTR_PICH("pich"),
    ATTR_ORTHO("ortho"),
    ATTR_ZOOM2("zoom2"),
    ATTR_AMB("amb"),

    CHUNK_BACKIMAGE("BackImage"),

    CHUNK_MATERIAL("Material"),
    ATTR_SHADER("shader"),
    ATTR_VCOL("vcol"),
    ATTR_DBLS("dbls"),
    ATTR_COL("col"),
    ATTR_DIF("dif"),
    //ATTR_AMB,
    ATTR_EMI("emi"),
    ATTR_SPC("spc"),
    ATTR_POWER("power"),
    ATTR_REFLECT("reflect"),
    ATTR_REFRECT("refrect"),
    ATTR_TEX("tex"),
    ATTR_APLANE("aplane"),
    ATTR_BUMP("bump"),
    ATTR_PROJ_TYPE("proj_type"),
    ATTR_PROJ_POS("proj_pos"),
    ATTR_PROJ_SCALE("proj_scale"),
    ATTR_PROJ_ANGLE("proj_angle"),

    CHUNK_OBJECT("Object"),
    ATTR_UID("uid"),
    ATTR_DEPTH("depth"),
    ATTR_FOLDING("folding"),
    ATTR_SCALE("scale"),
    ATTR_ROTATION("rotation"),
    ATTR_TRANSLATION("translation"),
    ATTR_PATCH("patch"),
    ATTR_PATCHTRI("patchtri"),
    ATTR_SEGMENT("segment"),
    ATTR_VISIBLE("visible"),
    ATTR_LOCKING("locking"),
    ATTR_SHADING("shading"),
    ATTR_FACET("facet"),
    ATTR_COLOR("color"),
    ATTR_COLOR_TYPE("color_type"),
    ATTR_MIRROR("mirror"),
    ATTR_MIRROR_AXIS("mirror_axis"),
    ATTR_MIRROR_DIS("mirror_dis"),
    ATTR_LATHE("lathe"),
    ATTR_LATHE_AXIS("lathe_axis"),
    ATTR_LATHE_SEG("lathe_seg"),

    CHUNK_VERTEX("vertex"),

    CHUNK_VERTEX_ATTR("vertexattr"),
    //ATTR_UID("uid"),
    ATTR_WEIT("weit"),
    //ATTR_COLOR("color"),

    CHUNK_BVERTEX("BVertex"),
    ATTR_VECTOR("vector"),
    //ATTR_WEIT("weit"),
    //ATTR_COLOR("color"),

    CHUNK_FACE("face"),
    ATTR_V("V"),
    ATTR_M("M"),
    ATTR_UV("UV"),
    //ATTR_COL("COL"),
    ATTR_CRS("CRS"),

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
