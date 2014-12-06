package org.zaregoto.mqoparser.model;

/**
 * Created by IntelliJ IDEA.
 * User: waka
 * Date: 5/15/11
 * Time: 6:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class MQOScene {

//    Scene {
//        pos 0.0000 0.0000 1500.0000
//        lookat 0.0000 0.0000 0.0000
//        head -0.5236
//        pich 0.5236
//        ortho 0
//        zoom2 5.0000
//        amb 0.250 0.250 0.250
//    }

    private Pos pos = null;
    private LookAt lookat = null;
    private Head head = null;
    private Pitch pich = null;
    private Ortho ortho = null;
    private Zoom2 zoom2 = null;
    private Amb amb = null;

    public MQOScene() {
    }


    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public LookAt getLookat() {
        return lookat;
    }

    public void setLookat(LookAt lookat) {
        this.lookat = lookat;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Pitch getPich() {
        return pich;
    }

    public void setPich(Pitch pich) {
        this.pich = pich;
    }

    public Ortho getOrtho() {
        return ortho;
    }

    public void setOrtho(Ortho ortho) {
        this.ortho = ortho;
    }

    public Zoom2 getZoom2() {
        return zoom2;
    }

    public void setZoom2(Zoom2 zoom2) {
        this.zoom2 = zoom2;
    }

    public Amb getAmb() {
        return amb;
    }

    public void setAmb(Amb amb) {
        this.amb = amb;
    }

    class Pos {

        private float x;
        private float y;
        private float z;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getZ() {
            return z;
        }

        public void setZ(float z) {
            this.z = z;
        }
    }

    class LookAt {

        private float x;
        private float y;
        private float z;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getZ() {
            return z;
        }

        public void setZ(float z) {
            this.z = z;
        }
    }


    class Head {
        private float value;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }


    class Pitch {
        private float value;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

    class Ortho {
        private float value;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

    class Zoom2 {
        private float value;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }


    class Amb {

        private float x;
        private float y;
        private float z;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getZ() {
            return z;
        }

        public void setZ(float z) {
            this.z = z;
        }
    }

    class Unknown {
    }
}
