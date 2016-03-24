package Geometry;

public class Triangle {
	private Point3DSpace vertexA;
	private Point3DSpace vertexB;
	private Point3DSpace vertexC;

	public Triangle() {
		this(new Point3DSpace(), new Point3DSpace(), new Point3DSpace());
	}

	public Triangle(Point3DSpace vertexA, Point3DSpace vertexB, Point3DSpace vertexC) {
		setVertexA(vertexA);
		setVertexB(vertexB);
		setVertexC(vertexC);
	}

	LineSegment sideA() {
		return new LineSegment(vertexB, vertexC);
	}

	LineSegment sideB() {
		return new LineSegment(vertexA, vertexC);
	}

	LineSegment sideC() {
		return new LineSegment(vertexA, vertexB);
	}

	public void setVertexA(Point3DSpace vertexA) {
		this.vertexA = vertexA;
	}

	public void setVertexB(Point3DSpace vertexB) {
		this.vertexB = vertexB;
	}

	public void setVertexC(Point3DSpace vertexC) {
		this.vertexC = vertexC;
	}

	public Point3DSpace getVertexA() {
		return vertexA;
	}

	public Point3DSpace getVertexB() {
		return vertexB;
	}

	public Point3DSpace getVertexC() {
		return vertexC;
	}
}
