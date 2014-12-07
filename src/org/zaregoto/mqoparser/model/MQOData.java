package org.zaregoto.mqoparser.model;

import java.util.ArrayList;
import java.util.Iterator;


public class MQOData {

    private MQOHeader header = null;

    private ArrayList<MQOIncludeXml> includeXmls;

    private MQOScene scene = null;
    private ArrayList<MQOMaterial> materials = null;
    private ArrayList<MQOObject> objects = null;




    public MQOData() {

        header = null;

        includeXmls = new ArrayList<MQOIncludeXml>();

        materials = new ArrayList<MQOMaterial>();
        objects = new ArrayList<MQOObject>();

        return;
    }


    public MQOHeader getHeader() {
        return header;
    }

    public void setHeader(MQOHeader header) {
        this.header = header;
    }

    public ArrayList<MQOIncludeXml> getIncludeXmls() {
        return includeXmls;
    }


    public MQOScene getScene() {
        return scene;
    }

    public void setScene(MQOScene scene) {
        this.scene = scene;
    }

    public ArrayList<MQOMaterial> getMaterials() {
        return materials;
    }

    public ArrayList<MQOObject> getObjects() {
        return objects;
    }

	public void addMaterials(ArrayList<MQOMaterial> materialDatas) {
		if (null != materials && null != materialDatas) {
			materials.addAll(materialDatas);
		}
		return;
	}


	public void addObject(MQOObject objectData) {
		if (null != objects && null != objectData) {
			objects.add(objectData);
		}
	}

    public void addIncludeXml(MQOIncludeXml includeXml) {
        if (null != includeXmls && null != includeXml) {
            includeXmls.add(includeXml);
        }
    }


    public String toString() {

        StringBuffer str = new StringBuffer();

        str.append(header.toString());

        Iterator<MQOIncludeXml> it1 = includeXmls.iterator();
        MQOIncludeXml xml;
        while (it1.hasNext()) {
            xml = it1.next();
            str.append(xml.toString());
        }

        str.append(scene.toString());

        Iterator<MQOMaterial> it2 = materials.iterator();
        MQOMaterial material;
        while (it2.hasNext()) {
            material = it2.next();
            str.append(material.toString());
        }

        Iterator<MQOObject> it3 = objects.iterator();
        MQOObject object;
        while (it3.hasNext()) {
            object = it3.next();
            str.append(object.toString());
        }

        return str.toString();
    }

}
