package org.zaregoto.mqoparser.parser;


import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.parser.exception.LoadStateException;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;

import java.io.*;


public class MQOParser {

    private File mqofile = null;
    private BufferedInputStream bis = null;

    private LexicalAnalyzer lexicalAnalyzer = null;
    private StateMachine stateMachine = new StateMachine();


    public MQOParser() {

        try {
            stateMachine.initialize();
        } catch (LoadStateException e) {
            e.printStackTrace();
        }
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

    public MQOData parse() throws IOException, StateTransferException {

        LexicalElement e;
        MQOData data;

        stateMachine.init();

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
