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
	
	double dX() { // This is how much the x coordinate changes from the start point to the end point.
		return endPoint.getX_Coordinate() - startPoint.getX_Coordinate();
	}
	
	double dY() { // This is how much the y coordinate changes from the start point to the end point.
		return endPoint.getY_Coordinate() - startPoint.getY_Coordinate();	
	}
	
	double dZ() { // This is how much the z coordinate changes from the start point to the end point.
		return endPoint.getZ_Coordinate() - startPoint.getZ_Coordinate();
	}
	
	double xyPlaneInclinationAngle() {
		return (dX() - dY() + Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0))) / 2.0
				* Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0) + dX());
	}
	
	double xyPlaneInclinationAngle() {
		if (dX() > 0 && dY() > 0)
			return 45.0 * Math.acos((dX() - dY() + Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0) + dX()));
		else if (dX() < 0 && dY() > 0)
			return 45.0 * Math.acos((dX() - dY() + Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0) + dX())) + 90.0;
		else if (dX() < 0 && dY() < 0)
			return 45.0 * Math.acos((dX() - dY() + Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0) + dX())) + 180.0;
		else
			return 45.0 * Math.acos((dX() - dY() + Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dY, 2.0) + dX())) + 270.0;
	}
	
	double xzPlaneInclinationAngle() {
		if (dX() > 0 && dZ() > 0)
			return 45.0 * Math.acos((dX() - dZ() + Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0) + dX()));
		else if (dX() < 0 && dZ() > 0)
			return 45.0 * Math.acos((dX() - dZ() + Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0) + dX())) + 90.0;
		else if (dX() < 0 && dZ() < 0)
			return 45.0 * Math.acos((dX() - dZ() + Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dZ, 2.0) + Math.pow(dZ, 2.0) + dX())) + 180.0;
		else
			return 45.0 * Math.acos((dX() - dZ() + Math.sqrt(Math.pow(dZ, 2.0) + Math.pow(dZ, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0) + dX())) + 270.0;
	}
	
	double yzPlaneInclinationAngle() {
		if (dY() > 0 && dZ() > 0)
			return 45.0 * Math.acos((dY() - dZ() + Math.sqrt(Math.pow(dY, 2.0) + Math.pow(dZ, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dY, 2.0) + Math.pow(dZ, 2.0) + dY()));
		else if (dY() < 0 && dZ() > 0)
			return 4.0 * Math.acos((dY() - dZ() + Math.sqrt(Math.pow(dY, 2.0) + Math.pow(dZ, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dY, 2.0) + Math.pow(dZ, 2.0) + dY()));
		else if (dY() < 0 && dZ() < 0)
			return 225.0 * Math.acos((dY() - dZ() + Math.sqrt(Math.pow(dY, 2.0) + Math.pow(dZ, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dZ, 2.0) + Math.pow(dZ, 2.0) + dY()));
		else
			return 315.0 * Math.acos((dY() - dZ() + Math.sqrt(Math.pow(dZ, 2.0) + Math.pow(dZ, 2.0))) / 2.0
					* Math.sqrt(Math.pow(dY, 2.0) + Math.pow(dZ, 2.0) + dY()));
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
