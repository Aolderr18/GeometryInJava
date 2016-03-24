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
	
	double perimeter() { // This is the sum of the sides.
		return sideA().segmentLength() + sideB().segmentLength() + sideC().segmentLength();
	}
	
	double area() {
		return new (sideB() + Math.acos(LineSegment(vertexB, new Point3DSpace(vertexA + sideB().dX() / 2.0,
				vertexA + sideB().dY() / 2.0, vertexA + sideB().dZ() / 2.0)).segmentLength())) / 2.0;
	}
	
	double angleA() { // This is the measure of angleA.
		return 180.0 * ((Math.acos((sideB().segmentLength() + sideC().segmentLength() - sideA().segmentLength())
				/ (2.0 * sideB().segmentLength() * sideC().segmentLength()))) / Math.PI);
	}
	
	double angleB() { // This is the measure of angleB.
		return 180.0 * ((Math.acos((sideA().segmentLength() + sideC().segmentLength() - sideB().segmentLength())
				/ (2.0 * sideA().segmentLength() * sideC().segmentLength()))) / Math.PI);
	}
	
	double angleC() { // This is the measure of angleC.
		return 180.0 * ((Math.acos((sideA().segmentLength() + sideB().segmentLength() - sideC().segmentLength())
				/ (2.0 * sideA().segmentLength() * sideB().segmentLength()))) / Math.PI);
	}
	
	// Create mutators.
	
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
	
	// Create accessors.
	
	public Point3DSpace getVertexB() {
		return vertexB;
	}

	public Point3DSpace getVertexC() {
		return vertexC;
	}
}
