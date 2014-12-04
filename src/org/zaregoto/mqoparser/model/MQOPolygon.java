package org.zaregoto.mqoparser.model;

import java.util.ArrayList;

public class MQOPolygon {

	private ArrayList<Vertex> vertexes = new ArrayList<Vertex>();
	private ArrayList<TrianglePolygon> triangles = new ArrayList<TrianglePolygon>();
	private ArrayList<QuadPolygon> quads = new ArrayList<QuadPolygon>();

	
	public MQOPolygon() {
	}
	
	public ArrayList<Vertex> getVertexes() {
		return vertexes;
	}

	public void setVertexes(ArrayList<Vertex> vertexes) {
		this.vertexes = vertexes;
	}
	
	public void addVertex(Float x, Float y, Float z) {
		vertexes.add(new Vertex(x, y, z));
	}
	
	public void addPolygon(Short[] indexes) {
		if (indexes.length == 3) {
			triangles.add(new TrianglePolygon(indexes));
		}
		else if (indexes.length == 4) {
			quads.add(new QuadPolygon(indexes));
		}
	}

	public void setTriangles(ArrayList<TrianglePolygon> triangles) {
		this.triangles = triangles;
	}	

	public ArrayList<TrianglePolygon> getTriangles() {
		return triangles;
	}
	
	public void setQuads(ArrayList<QuadPolygon> quads) {
		this.quads = quads;
	}


	public ArrayList<QuadPolygon> getQuads() {
		return quads;
	}

	public class Vertex {
		private Float x, y, z;
		public Vertex(Float _x, Float _y, Float _z) {
			x = _x;
			y = _y;
			z = _z;
			return;
		}
		public Float getX() {
			return x;
		}
		public void setX(Float x) {
			this.x = x;
		}
		public Float getY() {
			return y;
		}
		public void setY(Float y) {
			this.y = y;
		}
		public Float getZ() {
			return z;
		}
		public void setZ(Float z) {
			this.z = z;
		}		
	}

	public class Polygon {
		private Short[] indexes;
		public Polygon(Short[] _indexes) {
			indexes = _indexes;
		}
		public Short[] getIndexes() {
			return indexes;
		}
		public void setIndexes(Short[] indexes) {
			this.indexes = indexes;
		}		
	}

	
	public class TrianglePolygon extends Polygon {

		public TrianglePolygon(Short[] indexes) {
			super(indexes);
		}
		
	}
	
	public class QuadPolygon extends Polygon {

		public QuadPolygon(Short[] indexes) {
			super(indexes);
		}		
	}
	
}
