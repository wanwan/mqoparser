package org.zaregoto.mqoparser.parser;


import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.exception.LoadStateException;
import org.zaregoto.mqoparser.exception.StateTransferException;

import java.io.*;


public class MQOParser {

    private File mqofile = null;
    private BufferedInputStream bis = null;

    private LexicalAnalyzer lexicalAnalyzer = null;
    private StateMachine stateMachine = new StateMachine();


    public MQOParser() {
    }

    public void open(String filename) throws FileNotFoundException {

        if (null != filename) {
            mqofile = new File(filename);
            this.open(mqofile);
        }
    }


    public void open(File file) throws FileNotFoundException {

        if (null != file) {
            mqofile = file;
            if (mqofile.exists() && mqofile.isFile()) {
                bis = new BufferedInputStream(new FileInputStream(mqofile));
                lexicalAnalyzer = new LexicalAnalyzer(bis);
            }
            else {
                throw new FileNotFoundException("illeagal filename: " + file.getName());
            }
        }
        else {
            throw new FileNotFoundException("filename is null");
        }

    }


    public MQOData parse() throws IOException, StateTransferException, LoadStateException {

        LexicalElement e;
        MQOData data;

        stateMachine.initialize();

        while (null != (e = lexicalAnalyzer.next())) {
            e.dump();

            stateMachine.transfer(e);
        }
        data = stateMachine.getMqodata();

        return data;
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
