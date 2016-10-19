import java.util.ArrayList;
import java.util.EmptyStackException;

public final class Angle_3DSpace {
	private Double XY_Plane_Angle;
	private Double XZ_Plane_Angle;
	private Double YZ_Plane_Angle;
	protected boolean XY_Plane_active;
	// If the XY unit circle is relevant
	protected boolean XZ_Plane_active;
	// If the xz unit circle is relevant
	protected boolean YZ_Plane_active;
	// If the yz unit circle is relevant

	public Angle_3DSpace(Double XY_Plane_Angle, Double XZ_Plane_Angle, Double YZ_Plane_Angle)
			throws InvalidDimensionUseException, Invalid3DAngleInputException {
		XY_Plane_active = false;
		XZ_Plane_active = false;
		YZ_Plane_active = false;
		float originalXY_value = 0, originalXZ_value = 0, originalYZ_value = 0;
		try {
			XY_Plane_Angle %= 360;
			XY_Plane_active = true;
			originalXY_value = (float) XY_Plane_Angle.doubleValue();
			setXY_Plane_Angle(XY_Plane_Angle);
		} catch (NullPointerException npe) {

		}
		try {
			XZ_Plane_Angle %= 360;
			XZ_Plane_active = true;
			originalXZ_value = (float) XZ_Plane_Angle.doubleValue();
			setXZ_Plane_Angle(XZ_Plane_Angle);
		} catch (NullPointerException npe) {

		}
		try {
			YZ_Plane_Angle %= 360;
			YZ_Plane_active = true;
			originalYZ_value = (float) YZ_Plane_Angle.doubleValue();
			setYZ_Plane_Angle(YZ_Plane_Angle);
		} catch (NullPointerException npe) {

		}
		if (!(XY_Plane_active || XZ_Plane_active || YZ_Plane_active))
			throw new InvalidDimensionUseException("");
		figureOutMissingAngles();
		if (XY_Plane_active && (float) XY_Plane_Angle.doubleValue() != originalXY_value)
			throw new Invalid3DAngleInputException("The value you input for the xy unit circle : " + originalXY_value
					+ " is not equal to what it has been truly evaluated to : " + XY_Plane_Angle);
		if (XZ_Plane_active && (float) XZ_Plane_Angle.doubleValue() != originalXZ_value)
			throw new Invalid3DAngleInputException("The value you input for the xz unit circle : " + originalXZ_value
					+ " is not equal to what it has been truly evaluated to : " + XZ_Plane_Angle);
		if (YZ_Plane_active && (float) YZ_Plane_Angle.doubleValue() != originalYZ_value)
			throw new Invalid3DAngleInputException("The value you input for the yz unit circle : " + originalYZ_value
					+ " is not equal to what it has been truly evaluated to : " + YZ_Plane_Angle);
	}

	@SuppressWarnings("unused")
	protected void figureOutMissingAngles() {
		PairStack<Double, String> allAngles = new PairStack<Double, String>();
		allAngles.push(new ObjectPair<Double, String>(XY_Plane_Angle, "xy"));
		allAngles.push(new ObjectPair<Double, String>(XZ_Plane_Angle, "xz"));
		allAngles.push(new ObjectPair<Double, String>(YZ_Plane_Angle, "yz"));
		while (!allAngles.isEmpty()) {
			ObjectPair<Double, String> nextPair = allAngles.pop();
			if (nextPair.getRight() == "xy") {
				try {
					double test0 = XY_Plane_Angle;
				} catch (NullPointerException npe0) {
					/*
					 * If the XY_Plane_Angle is declared as obsolete, there
					 * should be no reason to execute any statements beyond this
					 * point in the loop.
					 */
					continue;
				}
				try {
					double test = XZ_Plane_Angle;
					/*
					 * If the XZ_Plane_Angle is null, a null exception will be
					 * thrown upon setting a test double variable to it.
					 */
				} catch (NullPointerException npe) {
					// The XZ plane angle must be set.
					try {
						double test1 = YZ_Plane_Angle;
						double x = Math.cos(Math.toRadians(XY_Plane_Angle));
						double z = Math.sin(Math.toRadians(YZ_Plane_Angle));
						double hypothenuse = Math.sqrt(Math.pow(x, 2.0) + Math.pow(z, 2.0));
						if (x * z == 0.0)
							throw new NullPointerException();
						XZ_Plane_Angle = Math.toDegrees(Math.asin(z / hypothenuse));
					} catch (NullPointerException npe1) {
						if (nextPair.getLeft() > 90.0 && nextPair.getLeft() < 270.0)
							XZ_Plane_Angle = new Double(180.0);
						else
							XZ_Plane_Angle = new Double(0.0);
					}
				}
				try {
					double test = YZ_Plane_Angle;
				} catch (NullPointerException npe2) {
					// The YZ plane angle must be set.
					try {
						double test1 = XZ_Plane_Angle;
						double y = 1.0 - Math.sin(Math.toRadians(XY_Plane_Angle));
						double z = Math.sin(Math.toRadians(XZ_Plane_Angle));
						double hypothenuse = Math.sqrt(Math.pow(y, 2.0) + Math.pow(z, 2.0));
						if (y * z == 0.0)
							throw new NullPointerException();
						YZ_Plane_Angle = Math.toDegrees(Math.asin(y / hypothenuse));
					} catch (NullPointerException npe1) {
						if (nextPair.getLeft() > 0.0 && nextPair.getLeft() < 180.0)
							YZ_Plane_Angle = new Double(0.0);
						else
							YZ_Plane_Angle = new Double(180.0);
					}
				}
			} else if (nextPair.getRight() == "xz") {
				try {
					double test0 = XZ_Plane_Angle;
				} catch (NullPointerException npe0) {
					continue;
				}
				try {
					double test = YZ_Plane_Angle;
				} catch (NullPointerException npe) {
					// The YZ plane angle must be set.
					try {
						double test1 = XY_Plane_Angle;
						double y = 1.0 - Math.sin(Math.toRadians(XY_Plane_Angle));
						double z = Math.sin(Math.toRadians(XZ_Plane_Angle));
						double hypothenuse = Math.sqrt(Math.pow(y, 2.0) + Math.pow(z, 2.0));
						if (y * z == 0.0)
							throw new NullPointerException();
						YZ_Plane_Angle = Math.toDegrees(Math.asin(y / hypothenuse));
					} catch (NullPointerException npe1) {
						if (nextPair.getLeft() > 0.0 && nextPair.getLeft() < 180.0)
							YZ_Plane_Angle = new Double(90.0);
						else
							YZ_Plane_Angle = new Double(270.0);
					}
				}
				try {
					double test = XY_Plane_Angle;
				} catch (NullPointerException npe) {
					// The XY plane angle must be set.
					try {
						double test1 = YZ_Plane_Angle;
						double x = Math.cos(Math.toRadians(XZ_Plane_Angle));
						double y = 1.0 - Math.cos(Math.toRadians(YZ_Plane_Angle));
						double hypothenuse = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
						if (x * y == 0.0)
							throw new NullPointerException();
						XY_Plane_Angle = Math.toDegrees(Math.asin(y / hypothenuse));
					} catch (NullPointerException npe1) {
						if (nextPair.getLeft() > 90.0 && nextPair.getLeft() < 270.0)
							XY_Plane_Angle = new Double(180.0);
						else
							XY_Plane_Angle = new Double(0.0);
					}
				}
			} else {/*
					 * This is the case where is the next pair is the YZ unit
					 * circle.
					 */
				try {
					double test0 = YZ_Plane_Angle;
				} catch (NullPointerException npe0) {
					continue;
				}
				try {
					double test = XY_Plane_Angle;
				} catch (NullPointerException npe) {
					// The XY plane angle must be set.
					try {
						double test1 = XZ_Plane_Angle;
						double y = 1.0 - Math.cos(Math.toRadians(YZ_Plane_Angle));
						double x = Math.cos(Math.toRadians(XZ_Plane_Angle));
						double hypothenuse = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
						if (x * y == 0.0)
							throw new NullPointerException();
						XY_Plane_Angle = Math.toDegrees(Math.asin(y / hypothenuse));
					} catch (NullPointerException npe1) {
						if (nextPair.getLeft() > 90.0 && nextPair.getLeft() < 270.0)
							XY_Plane_Angle = new Double(270.0);
						else
							XY_Plane_Angle = new Double(90.0);
					}
				}
				try {
					double test = XZ_Plane_Angle;
				} catch (NullPointerException npe) {
					// The XZ plane angle must be set.
					try {
						double test1 = XY_Plane_Angle;
						double x = Math.cos(Math.toRadians(XY_Plane_Angle));
						double z = Math.sin(Math.toRadians(YZ_Plane_Angle));
						double hypothenuse = Math.sqrt(Math.pow(x, 2.0) + Math.pow(z, 2.0));
						if (x * z == 0.0)
							throw new NullPointerException();
						XZ_Plane_Angle = Math.toDegrees(Math.asin(z / hypothenuse));
					} catch (NullPointerException npe1) {
						if (nextPair.getLeft() > 0 && nextPair.getLeft() < 180.0)
							XZ_Plane_Angle = new Double(90.0);
						else
							XZ_Plane_Angle = new Double(270.0);
					}
				}
			}
		}
	}

	/*
	 * The following method returns a vector of three variables, namely x, y,
	 * and z, which name a coordinate as to where the angle points to.
	 */
	public double[] directionalPlaneVector() {
		try {
			double[] directionalPlaneVector = new double[3];
			directionalPlaneVector[0] = Math.cos(Math.toRadians(XY_Plane_Angle));
			directionalPlaneVector[0] = Math.sin(Math.toRadians(XY_Plane_Angle));
			directionalPlaneVector[0] = Math.sin(Math.toRadians(XZ_Plane_Angle));
			return directionalPlaneVector;
		} catch (NullPointerException npe) {
			double[] directionalPlaneVector = new double[3];
			if (XY_Plane_active) {
				directionalPlaneVector[0] = Math.cos(Math.toRadians(XY_Plane_Angle));
				directionalPlaneVector[1] = Math.sin(Math.toRadians(XY_Plane_Angle));
				directionalPlaneVector[2] = 0.0;
				return directionalPlaneVector;
			}
			/**
			 * The 'else' keyword is unnecessary in this situation since if any
			 * 'if' statement in this sequence evaluates to 'true', the method
			 * returns a value.
			 */
			if (XZ_Plane_active) {
				directionalPlaneVector[0] = Math.cos(Math.toRadians(XZ_Plane_Angle));
				directionalPlaneVector[1] = 0.0;
				directionalPlaneVector[2] = Math.sin(Math.toRadians(XZ_Plane_Angle));
				return directionalPlaneVector;
			}
			directionalPlaneVector[0] = 0.0;
			directionalPlaneVector[1] = -1.0 * Math.cos(Math.toRadians(XY_Plane_Angle));
			directionalPlaneVector[2] = Math.sin(Math.toRadians(XY_Plane_Angle));
			return directionalPlaneVector;
		}
	}

	public double getXY_Plane_Angle() {
		double XY_Plane_Angle_duplicate = XY_Plane_Angle;
		return XY_Plane_Angle_duplicate;
	}

	public double getXZ_Plane_Angle() {
		double XZ_Plane_Angle_duplicate = XZ_Plane_Angle;
		return XZ_Plane_Angle_duplicate;
	}

	public double getYZ_Plane_Angle() {
		double YZ_Plane_Angle_duplicate = YZ_Plane_Angle;
		return YZ_Plane_Angle_duplicate;
	}

	public boolean XY_Plane_isActive() {
		if (XY_Plane_active)
			return true;
		else
			return false;
	}

	public boolean XZ_Plane_isActive() {
		if (XZ_Plane_active)
			return true;
		else
			return false;
	}

	public boolean YZ_Plane_isActive() {
		if (YZ_Plane_active)
			return true;
		else
			return false;
	}

	public void setXY_Plane_Angle(double XY_Plane_Angle) {
		this.XY_Plane_Angle = XY_Plane_Angle;
	}

	public void setXZ_Plane_Angle(double XZ_Plane_Angle) {
		this.XZ_Plane_Angle = XZ_Plane_Angle;
	}

	public void setYZ_Plane_Angle(double YZ_Plane_Angle) {
		this.YZ_Plane_Angle = YZ_Plane_Angle;
	}
}

class InvalidDimensionUseException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidDimensionUseException(String reason) {
		super(reason);
	}
}

class Invalid3DAngleInputException extends Exception {
	private static final long serialVersionUID = 1L;

	public Invalid3DAngleInputException(String reason) {
		super(reason);
	}
}

// The following class simply matches up one object with another.

class ObjectPair<V, S> {
	private V v;
	private S s;

	public ObjectPair(V v, S s) {
		this.v = v;
		this.s = s;
	}

	public V getLeft() {
		return v;
	}

	public S getRight() {
		return s;
	}
}

/*
 * The following class is very similar to a stack except that it stores object
 * pairs.
 */
class PairStack<V, S> {
	protected ArrayList<ObjectPair<V, S>> allPairs;

	public PairStack() {
		allPairs = new ArrayList<ObjectPair<V, S>>();
	}

	public void push(ObjectPair<V, S> pair) {
		allPairs.add(pair);
	}

	public ObjectPair<V, S> peak() throws EmptyStackException {
		if (allPairs.size() == 0)
			throw new EmptyStackException();
		return allPairs.get(allPairs.size() - 1);
	}

	public ObjectPair<V, S> pop() throws EmptyStackException {
		if (allPairs.size() == 0)
			throw new EmptyStackException();
		ObjectPair<V, S> pop = allPairs.get(allPairs.size() - 1);
		allPairs.remove(allPairs.size() - 1);
		return pop;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return allPairs.size();
	}
}
