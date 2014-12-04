package org.zaregoto.mqoparser;


import org.apache.commons.cli.*;
import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.parser.MQOParser;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;
import org.zaregoto.mqoparser.util.LogUtil;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // create Options object
        Options options = new Options();
        // add option
        Option file = OptionBuilder
                .hasArg(true)
                .withArgName("mqofile")
                .isRequired(true)
                .withDescription("-f mqofile")
                .withLongOpt("file")
                .create("f");
        options.addOption(file);

        CommandLineParser cliparser = new PosixParser();
        CommandLine cmd = null;
        try {
            cmd = cliparser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (null != cmd && cmd.hasOption("f")) {
            //String value = cmd.getOptionValue("f");
            String value = cmd.getOptionValue('f');
            System.out.println("filename: " + value);

            MQOParser parser = new MQOParser();
            MQOData data;
            try {
                parser.open(value);

                data = parser.parse();

                LogUtil.d(data.toString());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (StateTransferException e) {
                e.printStackTrace();
            } finally {
                try {
                    parser.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
