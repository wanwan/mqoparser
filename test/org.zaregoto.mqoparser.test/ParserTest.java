package org.zaregoto.mqoparser.test;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.zaregoto.mqoparser.model.MQOData;
import org.zaregoto.mqoparser.model.MQOHeader;
import org.zaregoto.mqoparser.model.MQOIncludeXml;
import org.zaregoto.mqoparser.model.MQOScene;
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
    public void checkAll() {

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
    public void checkHeader() {

        String samplefile = "sample/primitive/cube.mqo";
        MQOData data;

        try {
            data = readData(samplefile);
        } catch (StateTransferException e) {
            e.printStackTrace();
            //Assert.assertTrue(false);
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
            //e.printStackTrace();
            Assert.assertTrue(true);
            return;
        } catch (IOException e) {
            //e.printStackTrace();
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
            //Assert.assertTrue(false);
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


    @Test
    public void checkScene() {

        String samplefile = "sample/primitive/cube.mqo";
        MQOData data;

        try {
            data = readData(samplefile);
        } catch (StateTransferException e) {
            e.printStackTrace();
            //Assert.assertTrue(false);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
            return;
        }

        if (null != data) {
            MQOScene scene = data.getScene();
            if (null != scene) {
                MQOScene.Pos pos = scene.getPos();
                if (null != pos && 0.0f == pos.getX() && 0.0f == pos.getY() && 1500.0f == pos.getZ()) {
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
                pos = null;

                MQOScene.LookAt lookAt = scene.getLookat();
                if (null != lookAt && 0.0f == lookAt.getX() && 0.0f == lookAt.getY() && 0.0f == lookAt.getZ()) {
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
                lookAt = null;

                MQOScene.Head head = scene.getHead();
                if (null != head && -0.5236f == head.getValue()) {
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
                head = null;

                MQOScene.Pich pich = scene.getPich();
                if (null != pich && 0.5236f == pich.getValue()) {
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
                pich = null;

                MQOScene.Ortho ortho = scene.getOrtho();
                if (null != ortho && 0 == ortho.getValue()) {
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
                ortho = null;

                MQOScene.Zoom2 zoom2 = scene.getZoom2();
                if (null != zoom2 && 5.0f == zoom2.getValue()) {
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
                zoom2 = null;


                MQOScene.Amb amb = scene.getAmb();
                if (null != amb && 0.25f == amb.getX() && 0.25f == amb.getY() && 0.25f == amb.getZ()) {
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
                amb = null;

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