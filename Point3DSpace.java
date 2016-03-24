package Geometry;

/**
 * The object that this class creates the foundation
 *  for is a point in 3 dimensional space.
 */

public class Point3DSpace {
	private double X_Coordinate;
	private double Y_Coordinate;
	private double Z_Coordinate;

	public Point3DSpace() {
		this(0.0, 0.0, 0.0);
	}

	public Point3DSpace(double X_Coordinate, double Y_Coordinate, double Z_Coordinate) {
		setX_Coordinate(X_Coordinate);
		setY_Coordinate(Y_Coordinate);
		setZ_Coordinate(Z_Coordinate);
	}

	// Create mutators
	public void setX_Coordinate(double X_Coordinate) {
		this.X_Coordinate = X_Coordinate;
	}

	public void setY_Coordinate(double Y_Coordinate) {
		this.Y_Coordinate = Y_Coordinate;
	}

	public void setZ_Coordinate(double Z_Coordinate) {
		this.Z_Coordinate = Z_Coordinate;
	}

	// Create accessors
	public double getX_Coordinate() {
		return X_Coordinate;
	}

	public double getY_Coordinate() {
		return Y_Coordinate;
	}

	public double getZ_Coordinate() {
		return Z_Coordinate;
	}
}
