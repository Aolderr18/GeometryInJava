package Geometry;

/**
 * The object that this class creates the foundation
 *  for is a point in 3 dimensional space. 3 axes,
 *   namely x, y, and z are used with BigDecimal 
 *   representations.
 */
import java.math.BigDecimal;

public class Point3DSpace {
	private BigDecimal X_Coordinate;
	private BigDecimal Y_Coordinate;
	private BigDecimal Z_Coordinate;

	public Point3DSpace() {
		this(BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0));
	}

	public Point3DSpace(BigDecimal X_Coordinate, BigDecimal Y_Coordinate, BigDecimal Z_Coordinate) {
		setX_Coordinate(X_Coordinate);
		setY_Coordinate(Y_Coordinate);
		setZ_Coordinate(Z_Coordinate);
	}

	// Create mutators
	public void setX_Coordinate(BigDecimal X_Coordinate) {
		this.X_Coordinate = X_Coordinate;
	}

	public void setY_Coordinate(BigDecimal Y_Coordinate) {
		this.Y_Coordinate = Y_Coordinate;
	}

	public void setZ_Coordinate(BigDecimal Z_Coordinate) {
		this.Z_Coordinate = Z_Coordinate;
	}

	// Create accessors
	public BigDecimal getX_Coordinate() {
		return X_Coordinate;
	}

	public BigDecimal getY_Coordinate() {
		return Y_Coordinate;
	}

	public BigDecimal getZ_Coordinate() {
		return Z_Coordinate;
	}
}
