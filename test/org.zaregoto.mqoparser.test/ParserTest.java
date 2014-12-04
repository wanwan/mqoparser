package org.zaregoto.mqoparser.test;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.parser.MQOParser;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;
import org.zaregoto.mqoparser.util.LogUtil;

import java.io.FileNotFoundException;
import java.io.IOException;


public class ParserTest {

    public ParserTest() {
    }

    @Before
    public void doBefore() {
    }

    @After
    public void doAfter() {
    }

    @Test
    public void checkHeader() {

        String samplefile = "sample/primitive/cube.mqo";

        MQOParser parser = new MQOParser();
        MQOData data = null;
        try {
            parser.open(samplefile);

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

        if (null != data) {
            MQOHeader hdr = data.getHeader();
            if (null != hdr) {
                if (hdr.getFormat().equals("Text") && hdr.getVersion() == 1.0) {
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
            }
            else {
                Assert.assertTrue(false);
            }
        }
        else {
            Assert.assertTrue(false);
        }
    }
}