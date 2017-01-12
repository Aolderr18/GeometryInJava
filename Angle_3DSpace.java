import java.util.ArrayList;
import java.util.EmptyStackException;

import javax.swing.JOptionPane;

public final class Angle_3DSpace {
	private AccurateNumber XY_Plane_Angle;
	private AccurateNumber XZ_Plane_Angle;
	private AccurateNumber YZ_Plane_Angle;
	protected boolean XY_Plane_active;
	// If the XY unit circle is relevant
	protected boolean XZ_Plane_active;
	// If the XZ unit circle is relevant
	protected boolean YZ_Plane_active;
	// If the YZ unit circle is relevant

	@SuppressWarnings("unused")
	public Angle_3DSpace(AccurateNumber XY_Plane_Angle, AccurateNumber XZ_Plane_Angle, AccurateNumber YZ_Plane_Angle)
			throws InvalidDimensionUseException, Invalid3DAngleInputException {
		setXY_Plane_Angle(XY_Plane_Angle);
		setXZ_Plane_Angle(XZ_Plane_Angle);
		setYZ_Plane_Angle(YZ_Plane_Angle);
		AccurateNumber originalXY = null, originalXZ = null, originalYZ = null;
		byte dimensionPairs = 0;
		while (dimensionPairs++ <= 3)
			try {
				switch (dimensionPairs) {
				case 1:
					originalXY = new AccurateNumber(XY_Plane_Angle.getIntegerDigits(),
							XY_Plane_Angle.getDecimalDigits());
					break;
				case 2:
					originalXZ = new AccurateNumber(XZ_Plane_Angle.getIntegerDigits(),
							XZ_Plane_Angle.getDecimalDigits());
					break;
				case 3:
					originalYZ = new AccurateNumber(YZ_Plane_Angle.getIntegerDigits(),
							YZ_Plane_Angle.getDecimalDigits());
				}
			} catch (NullPointerException npeX) {

			}
		try {
			figureOutMissingAngles();
		} catch (NumberTooLargeException ntle) {

		}
		dimensionPairs = 0;
		while (dimensionPairs++ <= 3) /*
										 * If this particular object of this
										 * class contains all three parameters,
										 * in which any of them results in a
										 * contradiction in the laws of
										 * trigonometry, an exception should be
										 * thrown as this is an invalid object.
										 */
			try {
				switch (dimensionPairs) {
				case 1:
					String test0 = originalXY.toString();
					if (!(originalXY.isEqualTo(XY_Plane_Angle)))
						throw new Invalid3DAngleInputException(
								"The original XY input is not compatible with the XZ and YZ plane angles.");
					break;
				case 2:
					String test1 = originalXZ.toString();
					if (!(originalXZ.isEqualTo(XZ_Plane_Angle)))
						throw new Invalid3DAngleInputException(
								"The original XZ input is not compatible with the XY and YZ plane angles.");
					break;
				case 3:
					String test2 = originalYZ.toString();
					if (!(originalYZ.isEqualTo(YZ_Plane_Angle)))
						throw new Invalid3DAngleInputException(
								"The original YZ input is not compatible with the XY and XZ plane angles.");
				}
			} catch (NullPointerException npeO) {

			}
	}

	public Angle_3DSpace(Double XY_Plane_Angle, Double XZ_Plane_Angle, Double YZ_Plane_Angle)
			throws InvalidDimensionUseException, Invalid3DAngleInputException {
		try {
			if (XY_Plane_Angle < 0.0)
				setXY_Plane_Angle(new AccurateNegativeNumber(XY_Plane_Angle));
			else
				setXY_Plane_Angle(new AccurateNumber(XY_Plane_Angle));
		} catch (NullPointerException n0) {

		}
		try {
			if (XZ_Plane_Angle < 0.0)
				setXZ_Plane_Angle(new AccurateNegativeNumber(XZ_Plane_Angle));
			else
				setXZ_Plane_Angle(new AccurateNumber(XZ_Plane_Angle));
		} catch (NullPointerException n1) {

		}
		try {
			if (YZ_Plane_Angle < 0.0)
				setYZ_Plane_Angle(new AccurateNegativeNumber(YZ_Plane_Angle));
			else
				setYZ_Plane_Angle(new AccurateNumber(YZ_Plane_Angle));
		} catch (NullPointerException n2) {

		}
		try {
			figureOutMissingAngles();
		} catch (NumberTooLargeException ntle) {

		}
	}

	@SuppressWarnings("unused")
	public void rotate(String unitCircleName, AccurateNumber rotationMagnitude)
			throws NumberTooLargeException, InvalidDimensionUseException {
		if (unitCircleName == "xy") {
			try {
				String test0 = XY_Plane_Angle.toString();
			} catch (NullPointerException n0) {
				return;
			}
			XZ_Plane_Angle = null;
			YZ_Plane_Angle = null;
			setXY_Plane_Angle(((getXY_Plane_Angle().sum(rotationMagnitude)).modulus(new AccurateNumber("360", "0"))));
			figureOutMissingAngles();
		} else if (unitCircleName == "xz") {
			try {
				String test1 = XZ_Plane_Angle.toString();
			} catch (NullPointerException n1) {
				return;
			}
			XY_Plane_Angle = null;
			YZ_Plane_Angle = null;
			setXZ_Plane_Angle(((getXZ_Plane_Angle().sum(rotationMagnitude)).modulus(new AccurateNumber("360", "0"))));
			figureOutMissingAngles();
		} else if (unitCircleName == "yz") {
			try {
				String test2 = YZ_Plane_Angle.toString();
			} catch (NullPointerException n2) {
				return;
			}
			XY_Plane_Angle = null;
			XZ_Plane_Angle = null;
			setYZ_Plane_Angle(((getYZ_Plane_Angle().sum(rotationMagnitude)).modulus(new AccurateNumber("360", "0"))));
			figureOutMissingAngles();
		} else {
			JOptionPane.showMessageDialog(null, "Invalid input");
		}
	}

	@SuppressWarnings("unused")
	protected void figureOutMissingAngles() throws NumberTooLargeException, InvalidDimensionUseException {
		try {
			String test0 = XY_Plane_Angle.toString();
			XY_Plane_active = true;
		} catch (NullPointerException n0) {
			XY_Plane_active = false;
		}
		try {
			String test1 = XZ_Plane_Angle.toString();
			XZ_Plane_active = true;
		} catch (NullPointerException n1) {
			XZ_Plane_active = false;
		}
		try {
			String test2 = YZ_Plane_Angle.toString();
			YZ_Plane_active = true;
		} catch (NullPointerException n2) {
			YZ_Plane_active = false;
		}
		AccurateNumber X, Y, Z, hypothenuse;
		char quadrant;
		if (XY_Plane_active && XZ_Plane_active && YZ_Plane_active) {

		} else if (XY_Plane_active && XZ_Plane_active && !YZ_Plane_active) {
			Y = XY_Plane_Angle.sine();
			Z = XZ_Plane_Angle.sine();
			if (XY_Plane_Angle.isInInclusiveRange(new AccurateNumber(0.0), new AccurateNumber(180.0))) {
				if (XZ_Plane_Angle.isInInclusiveRange(new AccurateNumber(0.0), new AccurateNumber(180.0)))
					quadrant = 'f';
				else
					quadrant = '4';
			} else {
				if (XZ_Plane_Angle.isInInclusiveRange(new AccurateNumber(0.0), new AccurateNumber(180.0)))
					quadrant = 's';
				else
					quadrant = 't';
			}
			if (Y instanceof AccurateNegativeNumber)
				Y = Y.product(new AccurateNegativeNumber("1", "0"));
			if (Z instanceof AccurateNegativeNumber)
				Z = Z.product(new AccurateNegativeNumber("1", "0"));
			hypothenuse = Y.power(2).sum(Z.power(2));
			hypothenuse.round(-2);
			hypothenuse = hypothenuse.root(2);
			if ((Y.sum(Z).isEqualTo(new AccurateNumber("0", "0")))) {
				// The YZ unit circle would be unnecessary if this is the case.
			} else if ((XY_Plane_Angle.cosine().product(Y).product(Z)).isEqualTo(new AccurateNumber("0", "0"))) {
				/*
				 * This is the case when X = 0. Therefore, if the YZ unit circle
				 * is unknown, it should only be able to be assigned in one of
				 * four places.
				 */
				switch (quadrant) {
				case 'f':
					YZ_Plane_Angle = new AccurateNumber("45", "0");
					break;
				case 's':
					YZ_Plane_Angle = new AccurateNumber("135", "0");
					break;
				case 't':
					YZ_Plane_Angle = new AccurateNumber("225", "0");
					break;
				case '4':
					YZ_Plane_Angle = new AccurateNumber("315", "0");
				}
			} else {
				YZ_Plane_Angle = Z.quotient(hypothenuse).arcsine();
				switch (quadrant) {
				case 's':
					YZ_Plane_Angle = new AccurateNumber(180.0).difference(XZ_Plane_Angle);
					break;
				case 't':
					YZ_Plane_Angle = new AccurateNumber(180.0).sum(XZ_Plane_Angle);
					break;
				case '4':
					YZ_Plane_Angle = new AccurateNumber(360.0).difference(XZ_Plane_Angle);
				}
			}
		} else if (XY_Plane_active && !XZ_Plane_active && YZ_Plane_active) {
			X = XY_Plane_Angle.cosine();
			Z = YZ_Plane_Angle.sine();
			if (XY_Plane_Angle.isInExclusiveRange(new AccurateNumber(90.0), new AccurateNumber(270.0))) {
				if (YZ_Plane_Angle.isInInclusiveRange(new AccurateNumber(0.0), new AccurateNumber(180.0)))
					quadrant = 's';
				else
					quadrant = 't';
			} else {
				if (YZ_Plane_Angle.isInInclusiveRange(new AccurateNumber(0.0), new AccurateNumber(180.0)))
					quadrant = 'f';
				else
					quadrant = '4';
			}
			if (X instanceof AccurateNegativeNumber)
				X = X.product(new AccurateNegativeNumber("1", "0"));
			if (Z instanceof AccurateNegativeNumber)
				Z = Z.product(new AccurateNegativeNumber("1", "0"));
			hypothenuse = X.power(2).sum(Z.power(2));
			hypothenuse.round(-2);
			hypothenuse = hypothenuse.root(2);
			if ((X.sum(Z).isEqualTo(new AccurateNumber("0", "0")))) {
			} else if ((XY_Plane_Angle.sine().product(X).product(Z)).isEqualTo(new AccurateNumber("0", "0"))) {
				switch (quadrant) {
				case 'f':
					XZ_Plane_Angle = new AccurateNumber("45", "0");
					break;
				case 's':
					XZ_Plane_Angle = new AccurateNumber("135", "0");
					break;
				case 't':
					XZ_Plane_Angle = new AccurateNumber("225", "0");
					break;
				case '4':
					XZ_Plane_Angle = new AccurateNumber("315", "0");
				}
			} else {
				XZ_Plane_Angle = Z.quotient(hypothenuse).arcsine();
				switch (quadrant) {
				case 's':
					XZ_Plane_Angle = new AccurateNumber(180.0).difference(XZ_Plane_Angle);
					break;
				case 't':
					XZ_Plane_Angle = new AccurateNumber(180.0).sum(XZ_Plane_Angle);
					break;
				case '4':
					XZ_Plane_Angle = new AccurateNumber(360.0).difference(XZ_Plane_Angle);
				}
			}
		} else if (!XY_Plane_active && XZ_Plane_active && YZ_Plane_active) {
			X = XZ_Plane_Angle.cosine();
			Y = YZ_Plane_Angle.cosine();
			if (YZ_Plane_Angle.isInExclusiveRange(new AccurateNumber(90.0), new AccurateNumber(270.0))) {
				if (XZ_Plane_Angle.isInExclusiveRange(new AccurateNumber(90.0), new AccurateNumber(270.0)))
					quadrant = 't';
				else
					quadrant = '4';
			} else {
				if (XZ_Plane_Angle.isInExclusiveRange(new AccurateNumber(90.0), new AccurateNumber(270.0)))
					quadrant = 's';
				else
					quadrant = 'f';
			}
			if (X instanceof AccurateNegativeNumber)
				X = X.product(new AccurateNegativeNumber("1", "0"));
			if (Y instanceof AccurateNegativeNumber)
				Y = Y.product(new AccurateNegativeNumber("1", "0"));
			hypothenuse = X.power(2).sum(Y.power(2));
			hypothenuse.round(-2);
			hypothenuse = hypothenuse.root(2);
			if ((X.sum(Y).isEqualTo(new AccurateNumber("0", "0")))) {
			} else if ((XZ_Plane_Angle.sine().product(X).product(Y)).isEqualTo(new AccurateNumber("0", "0"))) {
				switch (quadrant) {
				case 'f':
					XY_Plane_Angle = new AccurateNumber("45", "0");
					break;
				case 's':
					XY_Plane_Angle = new AccurateNumber("135", "0");
					break;
				case 't':
					XY_Plane_Angle = new AccurateNumber("225", "0");
					break;
				case '4':
					XY_Plane_Angle = new AccurateNumber("315", "0");
				}
			} else {
				XY_Plane_Angle = Y.quotient(hypothenuse).arcsine();
				switch (quadrant) {
				case 's':
					XY_Plane_Angle = new AccurateNumber(180.0).difference(XY_Plane_Angle);
					break;
				case 't':
					XY_Plane_Angle = new AccurateNumber(180.0).sum(XY_Plane_Angle);
					break;
				case '4':
					XY_Plane_Angle = new AccurateNumber(360.0).difference(XY_Plane_Angle);
				}
			}
			/**
			 * The following conditionals are cases when only one unit circle
			 * angle has been defined upon creation of an object of this class.
			 */
		} else if (XY_Plane_active && !(XZ_Plane_active || YZ_Plane_active)) {
			switch (XY_Plane_Angle.toString()) {
			case "0":
				XZ_Plane_Angle = new AccurateNumber("0", "0");
				break;
			case "90":
				YZ_Plane_Angle = new AccurateNumber("0", "0");
				break;
			case "180":
				XZ_Plane_Angle = new AccurateNumber("180", "0");
				break;
			case "270":
				YZ_Plane_Angle = new AccurateNumber("180", "0");
				break;
			default:
				if (XY_Plane_Angle.isInExclusiveRange(new AccurateNumber(0.0), new AccurateNumber(180.0)))
					YZ_Plane_Angle = new AccurateNumber("0", "0");
				else
					YZ_Plane_Angle = new AccurateNumber("180", "0");
				if (XY_Plane_Angle.isInExclusiveRange(new AccurateNumber(90.0), new AccurateNumber(270.0)))
					XZ_Plane_Angle = new AccurateNumber("180", "0");
				else
					XZ_Plane_Angle = new AccurateNumber("0", "0");
			}
		} else if (XZ_Plane_active && !(XY_Plane_active || YZ_Plane_active)) {
			switch (XZ_Plane_Angle.toString()) {
			case "0":
				XY_Plane_Angle = new AccurateNumber("0", "0");
				break;
			case "90":
				YZ_Plane_Angle = new AccurateNumber("90", "0");
				break;
			case "180":
				XY_Plane_Angle = new AccurateNumber("180", "0");
				break;
			case "270":
				YZ_Plane_Angle = new AccurateNumber("270", "0");
				break;
			default:
				if (XZ_Plane_Angle.isInExclusiveRange(new AccurateNumber(90.0), new AccurateNumber(270.0)))
					XY_Plane_Angle = new AccurateNumber("180", "0");
				else
					XY_Plane_Angle = new AccurateNumber("0", "0");
				if (XZ_Plane_Angle.isInExclusiveRange(new AccurateNumber(0.0), new AccurateNumber(180.0)))
					YZ_Plane_Angle = new AccurateNumber("90", "0");
				else
					YZ_Plane_Angle = new AccurateNumber("270", "0");
			}
		} else if (YZ_Plane_active && !(XY_Plane_active || XZ_Plane_active)) {
			switch (YZ_Plane_Angle.toString()) {
			case "0":
				XY_Plane_Angle = new AccurateNumber("90", "0");
				break;
			case "90":
				XZ_Plane_Angle = new AccurateNumber("90", "0");
				break;
			case "180":
				XY_Plane_Angle = new AccurateNumber("270", "0");
				break;
			case "270":
				XZ_Plane_Angle = new AccurateNumber("270", "0");
				break;
			default:
				if (YZ_Plane_Angle.isInExclusiveRange(new AccurateNumber(0.0), new AccurateNumber(180.0)))
					XZ_Plane_Angle = new AccurateNumber("90", "0");
				else
					XZ_Plane_Angle = new AccurateNumber("270", "0");
				if (YZ_Plane_Angle.isInExclusiveRange(new AccurateNumber(90.0), new AccurateNumber(270.0)))
					XY_Plane_Angle = new AccurateNumber("270", "0");
				else
					XY_Plane_Angle = new AccurateNumber("90", "0");
			}
		} else
			throw new InvalidDimensionUseException("All unit circles are 'null'");
		try {
			String test0 = XY_Plane_Angle.toString();
			XY_Plane_active = true;
		} catch (NullPointerException n0) {
			XY_Plane_active = false;
		}
		try {
			String test1 = XZ_Plane_Angle.toString();
			XZ_Plane_active = true;
		} catch (NullPointerException n1) {
			XZ_Plane_active = false;
		}
		try {
			String test2 = YZ_Plane_Angle.toString();
			YZ_Plane_active = true;
		} catch (NullPointerException n2) {
			YZ_Plane_active = false;
		}
	}

	/*
	 * The following method returns a vector of three variables, namely x, y,
	 * and z, which name a coordinate as to where the angle points to.
	 */
	public AccurateNumber[] directionalPlaneVector() throws NumberTooLargeException, InvalidDimensionUseException {
		figureOutMissingAngles();
		try {
			AccurateNumber[] directionalPlaneVector = new AccurateNumber[3];
			directionalPlaneVector[0] = XY_Plane_Angle.cosine();
			directionalPlaneVector[1] = XY_Plane_Angle.sine();
			directionalPlaneVector[2] = XZ_Plane_Angle.sine();
			return directionalPlaneVector;
		} catch (NullPointerException npe) {
			AccurateNumber[] directionalPlaneVector = new AccurateNumber[3];
			if (XY_Plane_active) {
				directionalPlaneVector[0] = XY_Plane_Angle.cosine();
				directionalPlaneVector[1] = XY_Plane_Angle.sine();
				directionalPlaneVector[2] = new AccurateNumber("0", "0");
				return directionalPlaneVector;
			}
			/**
			 * The 'else' keyword is unnecessary in this situation since if any
			 * 'if' statement in this sequence evaluates to 'true', the method
			 * returns a value.
			 */
			if (XZ_Plane_active) {
				directionalPlaneVector[0] = XZ_Plane_Angle.cosine();
				directionalPlaneVector[1] = new AccurateNumber("0", "0");
				directionalPlaneVector[2] = XZ_Plane_Angle.sine();
				return directionalPlaneVector;
			}
			directionalPlaneVector[0] = new AccurateNumber("0", "0");
			directionalPlaneVector[1] = XY_Plane_Angle.cosine().product(new AccurateNegativeNumber("1", "0"));
			directionalPlaneVector[2] = XY_Plane_Angle.sine();
			return directionalPlaneVector;
		}
	}

	public AccurateNumber getXY_Plane_Angle() {
		AccurateNumber XY_Plane_Angle_duplicate = XY_Plane_Angle;
		return XY_Plane_Angle_duplicate;
	}

	public AccurateNumber getXZ_Plane_Angle() {
		AccurateNumber XZ_Plane_Angle_duplicate = XZ_Plane_Angle;
		return XZ_Plane_Angle_duplicate;
	}

	public AccurateNumber getYZ_Plane_Angle() {
		AccurateNumber YZ_Plane_Angle_duplicate = YZ_Plane_Angle;
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

	public void setXY_Plane_Angle(AccurateNumber XY_Plane_Angle) {
		this.XY_Plane_Angle = XY_Plane_Angle;
	}

	public void setXZ_Plane_Angle(AccurateNumber XZ_Plane_Angle) {
		this.XZ_Plane_Angle = XZ_Plane_Angle;
	}

	public void setYZ_Plane_Angle(AccurateNumber YZ_Plane_Angle) {
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
