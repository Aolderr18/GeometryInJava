package Geometry;
/**
* This class is used for creating instances of line segments in 3 dimensional space. 
*/
public class LineSegment {
	Point3DSpace startPoint;
	Point3DSpace endPoint;

	public LineSegment() {
		this(new Point3DSpace(), new Point3DSpace());
	}

	public LineSegment(Point3DSpace startPoint, Point3DSpace endPoint) {
		setStartPoint(startPoint);
		setEndPoint(endPoint);
	}

	double segmentLength() {/* This is the distance between the startPoint and the endPoint. The
	* Pythagorean theorem is used.
	*/
		return Math.sqrt(Math.pow(endPoint.getX_Coordinate() - startPoint.getX_Coordinate(), 2.0)
				+ Math.pow(endPoint.getY_Coordinate() - startPoint.getY_Coordinate(), 2.0)
				+ Math.pow(endPoint.getZ_Coordinate() - startPoint.getZ_Coordinate(), 2.0));
	}
	
	// Create mutators.
	
	public void setStartPoint(Point3DSpace startPoint) {
		this.startPoint = startPoint;
	}

	public void setEndPoint(Point3DSpace endPoint) {
		this.endPoint = endPoint;
	}
	
	// Create accessors.
	
	public Point3DSpace getStartPoint() {
		return startPoint;
	}

	public Point3DSpace getEndPoint() {
		return endPoint;
	}
}
