package org.zaregoto.mqoparser.parser;


import org.zaregoto.mqoparser.parser.exception.StateTransferException;
import org.zaregoto.mqoparser.parser.state.StateMachine;

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
            bis = new BufferedInputStream(new FileInputStream(mqofile));
            lexicalAnalyzer = new LexicalAnalyzer(bis);
        }
        else {
            throw new FileNotFoundException("filename is null");
        }

    }

    public void parse() throws IOException, StateTransferException {

        MQOElement e;

        stateMachine.init();

        while (null != (e = lexicalAnalyzer.next())) {
            e.dump();

            stateMachine.transfer(e);
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
