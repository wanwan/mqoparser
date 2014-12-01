package org.zaregoto.mqoparser.parser;


import java.io.*;


public class MQOParser {

    private File mqofile = null;
    private BufferedInputStream bis = null;

    private LexicalAnalyzer lexicalAnalyzer = null;

    public MQOParser() {
    }

    public void open(String filename) throws FileNotFoundException {

        if (null != filename) {
            mqofile = new File(filename);
            bis = new BufferedInputStream(new FileInputStream(mqofile));
            lexicalAnalyzer = new LexicalAnalyzer(bis);
        }
        else {
            throw new FileNotFoundException("filename is null");
        }
    }

    public void parse() throws IOException {

        MQOElement e;

        while (null != (e = lexicalAnalyzer.next())) {
            e.dump();
        }
    }


    public void close() throws IOException {

        if (null != bis) {
            bis.close();
            bis = null;
        }
        lexicalAnalyzer = null;
        mqofile = null;
    }

}
