package org.zaregoto.mqoparser.parser;

import org.zaregoto.mqoparser.util.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {

    enum LEXER_STS {
        CURRENT_NOT_DEFINED,
        CURRENT_SEARCH_ENTER_END,
        CURRENT_IN_WORD,;
    }

    BufferedReader br = null;

    public LexicalAnalyzer(InputStream is) {
        this.br = new BufferedReader(new InputStreamReader(is));
    }

    public LexicalElement next() throws IOException {

        LexicalElement next = null;
        int c;
        StringBuffer buf = null;
        LEXER_STS sts = LEXER_STS.CURRENT_NOT_DEFINED;

        determine_element:
        while (true) {

            br.mark(1);
            if (0 < (c = br.read())) {
                switch (sts) {
                    case CURRENT_NOT_DEFINED:
                        if (isSeparater(c)) {
                            /* go through */
                        }
                        else if (isChunkSeparaterIn(c)) {
                            buf = new StringBuffer();
                            buf.append(Character.toChars(c));
                            next = createMQOElement(buf);
                            break determine_element;
                        }
                        else if (isChunkSeparaterOut(c)) {
                            buf = new StringBuffer();
                            buf.append(Character.toChars(c));
                            next = createMQOElement(buf);
                            break determine_element;
                        }
                        else if (isIndexSeparaterIn(c)) {
                            buf = new StringBuffer();
                            buf.append(Character.toChars(c));
                            next = createMQOElement(buf);
                            break determine_element;
                        }
                        else if (isIndexSeparaterOut(c)) {
                            buf = new StringBuffer();
                            buf.append(Character.toChars(c));
                            next = createMQOElement(buf);
                            break determine_element;
                        }
                        else if (isParen(c)) {
                            buf = new StringBuffer();
                            buf.append(Character.toChars(c));
                            next = createMQOElement(buf);
                            break determine_element;
                        }
                        else if ('\r' == c) {
                            sts = LEXER_STS.CURRENT_SEARCH_ENTER_END;
                            buf = new StringBuffer();
                            buf.append(c);
                        }
                        else {
                            sts = LEXER_STS.CURRENT_IN_WORD;
                            buf = new StringBuffer();
                            buf.append(Character.toChars(c));
                        }
                        break;
                    case CURRENT_IN_WORD:
                        if (isWordChar(c)) {
                            buf.append(Character.toChars(c));
                        }
                        else {
                            br.reset();
                            break determine_element;
                        }
                        break;
                    case CURRENT_SEARCH_ENTER_END:
                        if ('\n' == c) {
                            buf.append(c);
                            break determine_element;
                        }
                        else {
                            LogUtil.d("lexical analyzer occur fatal error: may be enter code broken");
                        }
                        break;
                    default:
                        break;
                }
            } else {
                break;
            }
        }

        if (LEXER_STS.CURRENT_SEARCH_ENTER_END == sts) {
            next = LexicalElement.ENTER_CODE;
            next.setValue(buf.toString());
        }
        else if (LEXER_STS.CURRENT_IN_WORD == sts) {
            next = createMQOElement(buf);
        }

        return next;
    }


    private LexicalElement createMQOElement(StringBuffer buf) {

        String word = buf.toString().trim();
        LexicalElement element = null;

        search:
        for (LexicalElement e : LexicalElement.values()) {
            if (e.equals(word)) {
                element = e;
                break search;
            }
        }

        if (null == element) {
            String regex = "\"(.*)\"";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(word);
            if (m.find()) {
                element = LexicalElement.STRING;
                element.setValue(m.group(1));
            }
        }

        if (null == element) {
            String regex = "^[-]?[0-9]+$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(word);
            if (m.find()) {
                element = LexicalElement.INT;
                element.setValue(word);
            }
        }

        if (null == element) {
            String regex = "^[-]?[0-9]*.[0-9]*$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(word);
            if (m.find()) {
                element = LexicalElement.FLOAT;
                element.setValue(word);
            }
        }

        if (null == element) {
            element = LexicalElement.BYTE_ARRAY;
            element.setValue(word);
        }

        return element;
    }

    private boolean isWordChar(int c) {

        boolean ret = false;

        if (isSeparater(c) || isEnter(c) || isParen(c)) {
            ret = false;
        }
        else {
            ret = true;
        }
        return ret;
    }

    private boolean isSeparater(int c) {

        boolean ret = false;

        switch (c) {
            case ' ':
            case '\t':
                ret = true;
                break;
            default:
                break;
        }
        return ret;
    }

    private boolean isEnter(int c) {
        boolean ret = false;

        switch (c) {
            case '\n':
            case '\r':
                ret = true;
                break;
            default:
                break;
        }

        return ret;
    }

    private boolean isParen(int c) {
        boolean ret = false;

        switch (c) {
            case '(':
            case ')':
                ret = true;
                break;
            default:
                break;
        }

        return ret;
    }

    private boolean isChunkSeparaterIn(int c) {

        boolean ret = false;

        switch (c) {
            case '{':
                ret = true;
                break;
            default:
                break;
        }
        return ret;
    }

    private boolean isChunkSeparaterOut(int c) {

        boolean ret = false;

        switch (c) {
            case '}':
                ret = true;
                break;
            default:
                break;
        }
        return ret;
    }

    private boolean isIndexSeparaterIn(int c) {

        boolean ret = false;

        switch (c) {
            case '(':
                ret = true;
                break;
            default:
                break;
        }
        return ret;
    }

    private boolean isIndexSeparaterOut(int c) {

        boolean ret = false;

        switch (c) {
            case ')':
                ret = true;
                break;
            default:
                break;
        }
        return ret;
    }

}
