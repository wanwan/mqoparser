package org.zaregoto.mqoparser.model;


public class MQOScene {

    private Pos pos = null;
    private LookAt lookat = null;
    private Head head = null;
    private Pich pich = null;
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

    public Pich getPich() {
        return pich;
    }

    public void setPich(Pich pich) {
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

    public static class Pos {

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

    public static class LookAt {

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


    public static class Head {

        private float value;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }


    public static class Pich {

        private float value;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

    public static class Ortho {

        private float value;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

    public static class Zoom2 {

        private float value;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

    public static class Amb {

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
}
