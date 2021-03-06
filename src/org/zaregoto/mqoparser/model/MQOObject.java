package org.zaregoto.mqoparser.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.nio.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.zip.DataFormatException;


public class MQOObject {

    private static final int BYTES_PER_FLOAT = 4;
    private static final int BYTES_PER_SHORT = 2;

    enum SHADING {
        FLAT_SHADING,
        GLOW_SHADING,
    }

    enum COLOR_TYPE {
        USE_ENVIRONMENT,
        USE_OBJECT_SPECIFIED,
    }

    enum MIRROR_TYPE {
        MIRROR_TYPE_NONE,
            MIRROR_TYPE_SEPARATE_LEFT_RIGHT,
            MIRROR_TYPE_NOT_SEPARATE,
            MIRROR_TYPE_UNDEFINED,
    }

    enum MIRROR_AXIS {
        MIRROR_AXIS_X,
            MIRROR_AXIS_Y,
            MIRROR_AXIS_Z,
            MIRROR_AXIS_UNDEFINED,
    }

    enum LATHE_TYPE {
        LATHE_TYPE_NONE,
            LATHE_TYPE_BOTH_FACE,
            LATHE_TYPE_UNDEFINED,
    }

    enum LATHE_AXIS {
        LATHE_AXIS_X,
            LATHE_AXIS_Y,
            LATHE_AXIS_Z,
            LATHE_AXIS_UNDEFINED,
    }


    private String name = null;
    private Integer depth = null;
    private Integer foldeing = null;
    private Double[] scale = null;
    private Double[] rotation = null;
    private Double[] translation = null;
    private Double patch = null;
    private Double segment = null;
    private Boolean visible = null;
    private Boolean locking = null;
    private SHADING shading = null;
    private Integer facet = null;
    private Double[] color;
    private COLOR_TYPE colorType = null;
    private MIRROR_TYPE mirrorType = null;
    private MIRROR_AXIS mirrorAxis = null;
    private Double mirrorDis = null;
    private LATHE_TYPE latheType = null;
    private LATHE_AXIS latheAxis = null;
    private Integer latheSig = null;

    private MQOVertex vertex = null;
    private MQOBVertex bVertex = null;
    private MQOVertexAttr vertexAttr = null;
    private ArrayList<MQOFace> faces = null;

    //private MQOPolygon polygons = null;

    public MQOObject() {
    	//polygons = new MQOPolygon();
        return;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getFoldeing() {
        return foldeing;
    }

    public void setFoldeing(Integer foldeing) {
        this.foldeing = foldeing;
    }

    public Double[] getScale() {
        return scale;
    }

    public void setScale(Double[] scale) {
        this.scale = scale;
    }

    public Double[] getRotation() {
        return rotation;
    }

    public void setRotation(Double[] rotation) {
        this.rotation = rotation;
    }

    public Double[] getTranslation() {
        return translation;
    }

    public void setTranslation(Double[] translation) {
        this.translation = translation;
    }

    public Double getPatch() {
        return patch;
    }

    public void setPatch(Double patch) {
        this.patch = patch;
    }

    public Double getSegment() {
        return segment;
    }

    public void setSegment(Double segment) {
        this.segment = segment;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getLocking() {
        return locking;
    }

    public void setLocking(Boolean locking) {
        this.locking = locking;
    }

    public SHADING getShading() {
        return shading;
    }

    public void setShading(SHADING shading) {
        this.shading = shading;
    }

    public Integer getFacet() {
        return facet;
    }

    public void setFacet(Integer facet) {
        this.facet = facet;
    }

    public Double[] getColor() {
        return color;
    }

    public void setColor(Double[] color) {
        this.color = color;
    }

    public COLOR_TYPE getColorType() {
        return colorType;
    }

    public void setColorType(COLOR_TYPE colorType) {
        this.colorType = colorType;
    }

    public MIRROR_TYPE getMirrorType() {
        return mirrorType;
    }

    public void setMirrorType(MIRROR_TYPE mirrorType) {
        this.mirrorType = mirrorType;
    }

    public MIRROR_AXIS getMirrorAxis() {
        return mirrorAxis;
    }

    public void setMirrorAxis(MIRROR_AXIS mirrorAxis) {
        this.mirrorAxis = mirrorAxis;
    }

    public Double getMirrorDis() {
        return mirrorDis;
    }

    public void setMirrorDis(Double mirrorDis) {
        this.mirrorDis = mirrorDis;
    }

    public LATHE_TYPE getLatheType() {
        return latheType;
    }

    public void setLatheType(LATHE_TYPE latheType) {
        this.latheType = latheType;
    }

    public LATHE_AXIS getLatheAxis() {
        return latheAxis;
    }

    public void setLatheAxis(LATHE_AXIS latheAxis) {
        this.latheAxis = latheAxis;
    }

    public Integer getLatheSig() {
        return latheSig;
    }

    public void setLatheSig(Integer latheSig) {
        this.latheSig = latheSig;
    }

    public MQOVertex getVertex() {
        return vertex;
    }

    public void setVertex(MQOVertex vertex) {
        this.vertex = vertex;
    }

    public MQOBVertex getbVertex() {
        return bVertex;
    }

    public void setbVertex(MQOBVertex bVertex) {
        this.bVertex = bVertex;
    }

    public MQOVertexAttr getbVertexAttr() {
        return vertexAttr;
    }

    public void setbVertex(MQOVertexAttr vertexAttr) {
        this.vertexAttr = vertexAttr;
    }

    public ArrayList<MQOFace> getFace() {
        return faces;
    }

    public void addFace(MQOFace face) {
        if (null == faces) {
            faces = new ArrayList<MQOFace>();
        }
        faces.add(face);
    }


    public FloatBuffer generateVertexBuffer() {

        FloatBuffer fb = null;
        float[] array;

        if (null != vertex && null != vertex.getDatas()) {
            fb = ByteBuffer.allocateDirect(vertex.getDatas().size() * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
            array = vertex.getDatasPrimitive();
            if (null != array) {
                fb.put(array);
            }
            fb.position(0);
        }

        return fb;
    }


    public ShortBuffer generateIndexBuffer() throws DataFormatException {

        ShortBuffer sb = null;
        ArrayList<Integer> index = new ArrayList<Integer>();
        short[] array;

        for (MQOFace face : faces) {
            if (3 == face.getIndex().size()) {
                for (Integer i : face.getIndex())
                index.add(i.intValue());
            }
            else {
                throw new DataFormatException("except triangle polygon is not supported yet");
            }
        }

        if (index.size() > 0) {
            sb = ByteBuffer.allocateDirect(index.size() * BYTES_PER_SHORT).order(ByteOrder.nativeOrder()).asShortBuffer();
            array = convertIndexArrayListToPrimitiveList(index);
            if (null != array) {
                sb.put(array);
            }
            sb.position(0);
        }

        return sb;
    }

    private short[] convertIndexArrayListToPrimitiveList(ArrayList<Integer> index) {

        short[] ret = null;
        int i = 0;

        if (index.size() > 0) {
            ret = new short[index.size()];
        }

        for (Integer idx: index) {
            ret[i] = idx.shortValue();
            i++;
        }

        return ret;
    }


//	public void setPolygons(MQOPolygon polygons) {
//		this.polygons = polygons;
//	}
//
//
//
//    public MQOPolygon getPolygons() {
//		return polygons;
//	}
//
//
//    // TODO: これなんとかならんかな. format 的に vertex size { 形式なんで, polygon を Array で覚えるのやめるべきかも.
//    public FloatBuffer makeVertexFloatBuffer() {
//
//		int size = -1;
//		float[] expands = null;
//		ByteBuffer bb = null;
//		FloatBuffer fb = null;
//		MQOPolygon.Vertex vertex = null;
//		int i = 0;
//
//		if (null != polygons ) {
//			size = polygons.getVertexes().size() * 3;
//			expands = new float[size];
//
//			Iterator<MQOPolygon.Vertex> it = polygons.getVertexes().iterator();
//			while (it.hasNext()) {
//				vertex = it.next();
//				expands[i+0] = vertex.getX();
//				expands[i+1] = vertex.getY();
//				expands[i+2] = vertex.getZ();
//				i += 3;
//			}
//
//			bb = ByteBuffer.allocateDirect(4 * size); // sizeof(float) * num of points
//			bb.order(ByteOrder.nativeOrder());
//			fb = bb.asFloatBuffer();
//			fb.put(expands);
//			fb.position(0);
//		}
//
//		return fb;
//    }
//
//
//	// TODO: なんとかならんかな、これ。
//	public ShortBuffer makeTrianglePolygonShortBuffer() {
//
//		int size = -1;
//		ArrayList<Short> expands = null;
//		Short[] _expands = null;
//		short[] __expands = null;
//		ByteBuffer bb = null;
//		ShortBuffer sb = null;
//		MQOPolygon.Polygon polygon = null;
//
//		if (null != polygons ) {
//			expands = new ArrayList<Short>();
//
//			Iterator<MQOPolygon.TrianglePolygon> it = polygons.getTriangles().iterator();
//			while (it.hasNext()) {
//				polygon = it.next();
//				expands.addAll(Arrays.asList(polygon.getIndexes()));
//			}
//
//			_expands = expands.toArray(new Short[0]);
//			if (null != _expands) {
//				__expands = new short[_expands.length];
//				for (int i = 0; i < _expands.length; i++) {
//					__expands[i] = _expands[i];
//				}
//			}
//			size = expands.size();
//			bb = ByteBuffer.allocateDirect(2 * size); // sizeof(short) * num of points
//			bb.order(ByteOrder.nativeOrder());
//			sb = bb.asShortBuffer();
//			sb.put(__expands);
//			sb.position(0);
//		}
//
//		return sb;
//	}
//
//
//	public ShortBuffer makeQuadPolygonShortBuffer() {
//
//		int size = -1;
//		ArrayList<Short> expands = null;
//		Short[] _expands = null;
//		short[] __expands = null;
//		ByteBuffer bb = null;
//		ShortBuffer sb = null;
//		MQOPolygon.Polygon polygon = null;
//
//		if (null != polygons ) {
//			expands = new ArrayList<Short>();
//
//			Iterator<MQOPolygon.QuadPolygon> it = polygons.getQuads().iterator();
//			while (it.hasNext()) {
//				polygon = it.next();
//				expands.addAll(Arrays.asList(polygon.getIndexes()));
//			}
//
//			_expands = expands.toArray(new Short[0]);
//			if (null != _expands) {
//				__expands = new short[_expands.length];
//				for (int i = 0; i < _expands.length; i++) {
//					__expands[i] = _expands[i];
//				}
//			}
//			size = expands.size();
//			bb = ByteBuffer.allocateDirect(2 * size); // sizeof(short) * num of points
//			bb.order(ByteOrder.nativeOrder());
//			sb = bb.asShortBuffer();
//			sb.put(__expands);
//			sb.position(0);
//		}
//
//		return sb;
//
//	}


    public static class MQOVertex {
        private ArrayList<Float> datas;

        private MQOVertex() {
        }

        public MQOVertex(ArrayList<Float> datas) {
            this.datas = datas;
        }

        public ArrayList<Float> getDatas() {
            return datas;
        }

        public float[] getDatasPrimitive() {

            float[] ret = null;
            int i = 0;

            if (null != datas) {
                ret = new float[datas.size()];
                for (Float f : datas) {
                    ret[i] = (f != null) ? f.floatValue() : Float.NaN;
                    i++;
                }
            }

            return ret;
        }
    }

    public static class MQOBVertex {
        // TODO: not implement yet (MQOBVertex)
    }

    public static class MQOVertexAttr {
        // TODO: not implement yet (MQOVertexAttr)
    }

    public static class MQOFace {
        private ArrayList<Integer> index;
        private ArrayList<Integer> material;
        private ArrayList<Point2D.Float> uv;
        private ArrayList<Color> col;
        private ArrayList<Boolean> crs;
        private ArrayList<Integer> v;

        public MQOFace() {
        }

        public MQOFace(ArrayList<Integer> index, ArrayList<Integer> material, ArrayList<Point2D.Float> uv, ArrayList<Color> col, ArrayList<Boolean> crs) {
            this.index = index;
            this.material = material;
            this.uv = uv;
            this.col = col;
            this.crs = crs;
        }

        public ArrayList<Integer> getIndex() {
            return index;
        }

        public ArrayList<Integer> getMaterial() {
            return material;
        }

        public ArrayList<Point2D.Float> getUv() {
            return uv;
        }

        public ArrayList<Color> getCol() {
            return col;
        }

        public ArrayList<Boolean> getCrs() {
            return crs;
        }

        public void setV(ArrayList<Integer> v) {
            this.v = v;
        }
    }

}
