package org.zaregoto.mqoparser.test;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.model.MQOIncludeXml;
import org.zaregoto.mqoparser.parser.MQOParser;
import org.zaregoto.mqoparser.parser.exception.StateTransferException;
import org.zaregoto.mqoparser.util.LogUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


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
        MQOData data;

        try {
            data = readData(samplefile);
        } catch (StateTransferException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
            return;
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


    @Test
    public void checkTrailNoise() {

        String samplefile = "sample/error/trialnoise.mqo";
        MQOData data;

        try {
            data = readData(samplefile);
        } catch (StateTransferException e) {
            e.printStackTrace();
            Assert.assertTrue(true);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
            return;
        }

        if (null != data) {
            Assert.assertTrue(false);
        }
    }


    @Test
    public void checkIncludeXml() {

        String samplefile = "sample/test/includexml.mqo";
        MQOData data;

        try {
            data = readData(samplefile);
        } catch (StateTransferException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
            return;
        }

        if (null != data) {
            ArrayList<MQOIncludeXml> xmls = data.getIncludeXmls();
            if (null != xmls && xmls.size() > 0) {
                Iterator<MQOIncludeXml> it = xmls.iterator();
                while (it.hasNext()) {
                    MQOIncludeXml e = it.next();
                    if (e.getFilename().equals("test.xml")) {
                        Assert.assertTrue(true);
                    }
                    else {
                        Assert.assertTrue(false);
                    }
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



    private MQOData readData(String filename) throws IOException, StateTransferException {

        MQOParser parser = new MQOParser();
        MQOData data = null;
        try {
            parser.open(filename);

            data = parser.parse();

            LogUtil.d(data.toString());

        } finally {
            try {
                parser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }
}