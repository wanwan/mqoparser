package org.zaregoto.mqoparser.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: waka
 * Date: 5/15/11
 * Time: 6:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class MQOData {

    private MQOScene scene = null;
    private ArrayList<MQOMaterial> materials = null;
    private ArrayList<MQOObject> objects = null;

    private MQOHeader header = null;


    public MQOData() {

        header = null;

        scene = new MQOScene();
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

    public MQOScene getScene() {
        return scene;
    }

    public void setScene(MQOScene scene) {
        this.scene = scene;
    }

    public ArrayList<MQOMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<MQOMaterial> materials) {
        this.materials = materials;
    }

    public ArrayList<MQOObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<MQOObject> objects) {
        this.objects = objects;
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


    public String toString() {

        StringBuffer str = new StringBuffer();

        str.append(header.toString());
        str.append(scene.toString());

        Iterator<MQOMaterial> it = materials.iterator();
        MQOMaterial material;
        while (it.hasNext()) {
            material = it.next();
            str.append(material.toString());
        }

        Iterator<MQOObject> it2 = objects.iterator();
        MQOObject object;
        while (it.hasNext()) {
            object = it2.next();
            str.append(object.toString());
        }

        return str.toString();
    }

}
