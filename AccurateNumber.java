import java.util.ArrayList;

public class AccurateNumber {
	protected String integerDigits;
	protected String decimalDigits;

	public AccurateNumber() {
		this(new String(), new String());
	}

	public AccurateNumber(String integerDigits, String decimalDigits) {
		setIntegerDigits(integerDigits);
		setDecimalDigits(decimalDigits);
	}

	public AccurateNumber(double value) {
		if (value == 0.0) {
			setIntegerDigits("0");
			setDecimalDigits("0");
			return;
		}
		short digitsBeforeDecimal = 0;
		while (Math.pow(10.0, digitsBeforeDecimal++) <= value)
			;
		--digitsBeforeDecimal;
		long decimalReference = (int) value;
		int iteration = 0;
		while (iteration++ < 6) {
			decimalReference *= 10;
			value *= 10;
			decimalReference = (int) value;
		}
		String allDigits = "" + decimalReference;
		StringBuilder integerDigs = new StringBuilder();
		StringBuilder decimalDigs = new StringBuilder();
		iteration = 0;
		while (iteration < 16) {
			if (iteration < digitsBeforeDecimal) {
				try {
					integerDigs.append(allDigits.charAt(iteration++));
				} catch (StringIndexOutOfBoundsException sioobe) {
					break;
				}
				continue;
			}
			try {
				decimalDigs.append(allDigits.charAt(iteration++));
			} catch (StringIndexOutOfBoundsException sioobe) {
				decimalDigs.append("0");
			}
		}
		setIntegerDigits(integerDigs.toString());
		setDecimalDigits(decimalDigs.toString());
	}

	@Override
	public String toString() {
		if (isEqualTo(new AccurateNumber(0.0)))
			return "0";
		trimZeros();
		if (decimalDigits.length() > 0)
			return integerDigits + "." + decimalDigits;
		else
			return integerDigits;
	}

	AccurateNumber sum(AccurateNumber other) {
		if (other instanceof AccurateNegativeNumber)
			return other.sum(this);
		StringBuilder operand1, operand2;
		operand1 = new StringBuilder();
		operand2 = new StringBuilder();
		int differenceInLength;
		differenceInLength = Math.abs(integerDigits.length() - other.getIntegerDigits().length());
		if (integerDigits.length() > other.getIntegerDigits().length())
			while (differenceInLength > 0) {
				operand2.append("0");
				--differenceInLength;
			}
		else
			while (differenceInLength > 0) {
				operand1.append("0");
				--differenceInLength;
			}
		operand1.append(integerDigits);
		operand1.append(decimalDigits);
		operand2.append(other.getIntegerDigits());
		operand2.append(other.getDecimalDigits());
		differenceInLength = Math.abs(decimalDigits.length() - other.getDecimalDigits().length());
		int digitsAfterDecimal;
		if (decimalDigits.length() > other.getDecimalDigits().length()) {
			digitsAfterDecimal = decimalDigits.length();
			while (differenceInLength > 0) {
				operand2.append("0");
				--differenceInLength;
			}
		} else {
			digitsAfterDecimal = other.getDecimalDigits().length();
			while (differenceInLength > 0) {
				operand1.append("0");
				--differenceInLength;
			}
		}
		int index;
		int carry = 0;
		StringBuilder sumDigits;
		sumDigits = new StringBuilder();
		for (index = operand1.length() - 1; index >= 0; --index) {
			int placeSum = carry + charToInt(operand1.charAt(index)) + charToInt(operand2.charAt(index));
			carry = (placeSum - placeSum % 10) / 10;
			placeSum %= 10;
			sumDigits.append(placeSum);
		}
		sumDigits.append(carry);
		sumDigits.reverse();
		StringBuilder sumIntegerDigits, sumDecimalDigits;
		sumIntegerDigits = new StringBuilder();
		sumDecimalDigits = new StringBuilder();
		for (index = 0; index < sumDigits.length(); ++index) {
			if (index > sumDigits.length() - digitsAfterDecimal - 1) {
				sumDecimalDigits.append(sumDigits.charAt(index));
				continue;
			}
			sumIntegerDigits.append(sumDigits.charAt(index));
		}
		AccurateNumber sum;
		sum = new AccurateNumber(new String(sumIntegerDigits), new String(sumDecimalDigits));
		sum.trimZeros();
		return sum;
	}

	AccurateNumber difference(AccurateNumber other) {
		if (other instanceof AccurateNegativeNumber) {
			AccurateNumber positive = new AccurateNumber(other.getIntegerDigits(), other.getDecimalDigits());
			return sum(positive);
		}
		StringBuilder operand1, operand2;
		operand1 = new StringBuilder();
		operand2 = new StringBuilder();
		int differenceInLength;
		differenceInLength = Math.abs(integerDigits.length() - other.getIntegerDigits().length());
		if (integerDigits.length() > other.getIntegerDigits().length())
			while (differenceInLength > 0) {
				operand2.append("0");
				--differenceInLength;
			}
		else
			while (differenceInLength > 0) {
				operand1.append("0");
				--differenceInLength;
			}
		operand1.append(integerDigits);
		operand1.append(decimalDigits);
		operand2.append(other.getIntegerDigits());
		operand2.append(other.getDecimalDigits());
		differenceInLength = Math.abs(decimalDigits.length() - other.getDecimalDigits().length());
		int digitsAfterDecimal;
		if (decimalDigits.length() > other.getDecimalDigits().length()) {
			digitsAfterDecimal = decimalDigits.length();
			while (differenceInLength > 0) {
				operand2.append("0");
				--differenceInLength;
			}
		} else {
			digitsAfterDecimal = other.getDecimalDigits().length();
			while (differenceInLength > 0) {
				operand1.append("0");
				--differenceInLength;
			}
		}
		int[] operand1Digits, operand2Digits;
		operand1Digits = new int[operand1.length()];
		operand2Digits = new int[operand2.length()];
		int index;
		for (index = 0; index < operand1Digits.length; ++index) {
			operand1Digits[index] = charToInt(operand1.charAt(index));
			operand2Digits[index] = charToInt(operand2.charAt(index));
		}
		StringBuilder differenceDigits;
		differenceDigits = new StringBuilder();
		for (index = operand1Digits.length - 1; index >= 0; --index) {
			int placeDifference;
			int borrowIndex;
			borrowIndex = index;
			if (operand1Digits[index] >= operand2Digits[index]) {
				placeDifference = operand1Digits[index] - operand2Digits[index];
			} else {
				placeDifference = operand1Digits[index] - operand2Digits[index] + 10;
				try {
					do {
						operand1Digits[--borrowIndex] += 10;
						operand1Digits[borrowIndex] %= 11;
					} while (operand1Digits[borrowIndex] == 0);
				} catch (ArrayIndexOutOfBoundsException outOfBounds) {
				}
			}
			differenceDigits.append(placeDifference);
		}
		differenceDigits.reverse();
		StringBuilder differenceIntegerDigits, differenceDecimalDigits;
		differenceIntegerDigits = new StringBuilder();
		differenceDecimalDigits = new StringBuilder();
		for (index = 0; index < differenceDigits.length(); ++index) {
			if (index > differenceDigits.length() - digitsAfterDecimal - 1) {
				differenceDecimalDigits.append(differenceDigits.charAt(index));
				continue;
			}
			differenceIntegerDigits.append(differenceDigits.charAt(index));
		}
		AccurateNumber difference;
		difference = new AccurateNumber(new String(differenceIntegerDigits), new String(differenceDecimalDigits));
		difference.trimZeros();
		return difference;
	}

	AccurateNumber product(AccurateNumber other) {
		if (other.isEqualTo(new AccurateNumber("0", "0")))
			return other;
		if (isEqualTo(new AccurateNumber("0", "0")))
			return this;
		StringBuilder operand1, operand2;
		operand1 = new StringBuilder();
		operand2 = new StringBuilder();
		int differenceInLength;
		differenceInLength = Math.abs(integerDigits.length() - other.getIntegerDigits().length());
		if (integerDigits.length() > other.getIntegerDigits().length())
			while (differenceInLength > 0) {
				operand2.append("0");
				--differenceInLength;
			}
		else
			while (differenceInLength > 0) {
				operand1.append("0");
				--differenceInLength;
			}
		operand1.append(integerDigits);
		operand1.append(decimalDigits);
		operand2.append(other.getIntegerDigits());
		operand2.append(other.getDecimalDigits());
		differenceInLength = Math.abs(decimalDigits.length() - other.getDecimalDigits().length());
		int digitsAfterDecimal;
		if (decimalDigits.length() > other.getDecimalDigits().length()) {
			digitsAfterDecimal = decimalDigits.length() * 2;
			while (differenceInLength > 0) {
				operand2.append("0");
				--differenceInLength;
			}
		} else {
			digitsAfterDecimal = other.getDecimalDigits().length() * 2;
			while (differenceInLength > 0) {
				operand1.append("0");
				--differenceInLength;
			}
		}
		ArrayList<StringBuilder> productRows;
		productRows = new ArrayList<StringBuilder>();
		int indexA, indexB;
		int carry = 0;
		for (indexB = operand2.length() - 1; indexB >= 0; --indexB) {
			StringBuilder productRow;
			productRow = new StringBuilder();
			int rowShift;
			rowShift = operand2.length() - 1 - indexB;
			while (rowShift > 0) {
				productRow.append("0");
				--rowShift;
			}
			for (indexA = operand1.length() - 1; indexA >= 0; --indexA) {
				int placeProduct;
				placeProduct = carry + charToInt(operand1.charAt(indexA)) * charToInt(operand2.charAt(indexB));
				carry = (placeProduct - placeProduct % 10) / 10;
				placeProduct %= 10;
				productRow.append(placeProduct);
			}
			productRow.append(carry);
			carry = 0;
			productRow.reverse();
			productRows.add(productRow);
		}
		AccurateNumber product;
		product = new AccurateNumber("0", "0");
		int index;
		for (index = 0; index < productRows.size(); ++index)
			product = product.sum(new AccurateNumber(new String(productRows.get(index)), ""));
		StringBuilder productIntegerDigits = new StringBuilder(), productDecimalDigits = new StringBuilder();
		for (index = 0; index < product.getIntegerDigits().length() || index < digitsAfterDecimal; ++index) {
			if (index < digitsAfterDecimal)
				try {
					productDecimalDigits
							.append(product.getIntegerDigits().charAt(product.getIntegerDigits().length() - 1 - index));
				} catch (StringIndexOutOfBoundsException sioobe) {
					productDecimalDigits.append("0");
				}
			else
				try {
					productIntegerDigits
							.append(product.getIntegerDigits().charAt(product.getIntegerDigits().length() - 1 - index));
				} catch (StringIndexOutOfBoundsException sioobe) {
					break;
				}
		}
		productIntegerDigits.reverse();
		productDecimalDigits.reverse();
		product.setIntegerDigits(productIntegerDigits.toString());
		product.setDecimalDigits(productDecimalDigits.toString());
		product.trimZeros();
		if (other instanceof AccurateNegativeNumber)
			return new AccurateNegativeNumber(product.getIntegerDigits(), product.getDecimalDigits());
		return product;
	}

	AccurateNumber quotient(AccurateNumber other) {
		if (other.isEqualTo(new AccurateNumber("0", "0")))
			return null;
		if (other instanceof AccurateNegativeNumber)
			return other.quotient(this);
		if (equals(other))
			return new AccurateNumber("1", "0");
		if (equals(new AccurateNumber("1", "0")))
			return this;
		AccurateNumber increment = new AccurateNumber(), Quotient = new AccurateNumber();
		increment.setIntegerDigits("1");
		Quotient.setIntegerDigits("1");
		int precisionDestination = 129, precisionFactor = 0;
		while (precisionFactor < precisionDestination) {
			boolean decrementNecessary = true;
			while (isGreaterThanOrEqualTo(Quotient.product(other))) {
				if (Quotient.getDecimalDigits().length() > 1)
					if (Quotient.getDecimalDigits()
							.substring(Quotient.getDecimalDigits().length() - 2, Quotient.getDecimalDigits().length())
							.equals(new String("99"))) {
						decrementNecessary = false;
						Quotient.setDecimalDigits(Quotient.getDecimalDigits() + "0");
						break;
					}
				Quotient = Quotient.sum(increment);
			}
			if (decrementNecessary)
				Quotient = Quotient.difference(increment);
			decrementNecessary = true;
			if (precisionFactor == 0) {
				increment.setIntegerDigits("0");
				increment.setDecimalDigits("1");
			} else
				increment.setDecimalDigits("0" + increment.getDecimalDigits());
			++precisionFactor;
		}
		return Quotient;
	}

	AccurateNumber modulus(AccurateNumber other) {
		if (isEqualTo(other))
			return new AccurateNumber("0", "0");
		if (isEqualTo(new AccurateNumber("0", "0")))
			return new AccurateNumber("0", "0");
		AccurateNumber factor = new AccurateNumber("0", "0");
		if (!(other instanceof AccurateNegativeNumber)) {
			while ((other.product(factor)).isLessThan(this))
				factor = factor.sum(new AccurateNumber("1", "0"));
			factor = factor.difference(new AccurateNumber("1", "0"));
			return difference(other.product(factor));
		}
		while ((other.product(factor)).isLessThan(this))
			factor = factor.difference(new AccurateNumber("1", "0"));
		return (other.product(factor)).difference(this);
	}

	AccurateNumber power(int x) {
		if (isEqualTo(new AccurateNumber("0", "0")))
			return this;
		if (x < 0)
			return new AccurateNumber("1", "").quotient(power(-x));
		AccurateNumber power;
		power = new AccurateNumber("1", "");
		int times = 0;
		while (++times <= x)
			power = power.product(this);
		return power;
	}

	AccurateNumber root(int x) {
		trimZeros();
		if (isEqualTo(new AccurateNumber("0", "0")))
			return this;
		int decimalDigs = decimalDigits.length();
		int iter = 0;
		AccurateNumber substitute = this;
		while (iter++ < decimalDigs)
			substitute = substitute.product(new AccurateNumber("10", "0"));
		if (isLessThan(new AccurateNumber("1", "0"))) {
			AccurateNumber subs_Root = substitute.root(x);
			iter = 0;
			while (iter++ < decimalDigs / 2)
				subs_Root = subs_Root.quotient(new AccurateNumber("10", "0"));
			return subs_Root;
		}
		if (x < 0)
			return new AccurateNumber("1", "").quotient(root(-x));
		if (x == 1)
			return this;
		AccurateNumber increment = new AccurateNumber(), Root = new AccurateNumber();
		increment.setIntegerDigits("1");
		Root.setIntegerDigits("1");
		int precisionDestination = 129, precisionFactor = 0;
		while (precisionFactor < precisionDestination) {
			boolean decrementNecessary = true;
			while (isGreaterThanOrEqualTo(Root.power(x))) {
				if (Root.getDecimalDigits().length() > 1)
					if (Root.getDecimalDigits()
							.substring(Root.getDecimalDigits().length() - 2, Root.getDecimalDigits().length())
							.equals(new String("99"))) {
						decrementNecessary = false;
						Root.setDecimalDigits(Root.getDecimalDigits() + "0");
						break;
					}
				Root = Root.sum(increment);
			}
			if (decrementNecessary)
				Root = Root.difference(increment);
			decrementNecessary = true;
			if (precisionFactor == 0) {
				increment.setIntegerDigits("0");
				increment.setDecimalDigits("1");
			} else
				increment.setDecimalDigits("0" + increment.getDecimalDigits());
			++precisionFactor;
		}
		return Root;
	}

	double asDouble() throws NumberTooLargeException {
		if (isGreaterThan(new AccurateNumber("2147483647", "0"))
				|| isLessThan(new AccurateNegativeNumber("2147483648", "")))
			throw new NumberTooLargeException();
		double asDouble = 0.0;
		short numIntDigs = (short) integerDigits.length();
		short numDecDigs = (short) decimalDigits.length();
		double factor;
		int power = numIntDigs;
		int index = 0;
		while (index < numIntDigs + numDecDigs) {
			power--;
			factor = Math.pow(10, power);
			if (index < numIntDigs) {
				asDouble += factor * charToInt(integerDigits.charAt(index++));
				continue;
			}
			asDouble += factor * charToInt(decimalDigits.charAt(index++ - numIntDigs));
		}
		return asDouble;
	}

	int asInt() throws NumberTooLargeException {
		return (int) asDouble();
	}

	AccurateNumber sine() throws NumberTooLargeException {
		if (isEqualTo(new AccurateNumber("0", "0")))
			return new AccurateNumber("0", "0");
		if (isEqualTo(new AccurateNumber("30", "0")))
			return new AccurateNumber("1", "2");
		if (isEqualTo(new AccurateNumber("90", "0")))
			return new AccurateNumber("1", "0");
		if (isEqualTo(new AccurateNumber("180", "0")))
			return new AccurateNumber("0", "0");
		if (isEqualTo(new AccurateNumber("270", "0")))
			return new AccurateNegativeNumber("1", "0");
		double asDouble = asDouble();
		double sine = Math.sin(Math.toRadians(asDouble));
		if (sine < 0)
			return new AccurateNegativeNumber(sine);
		return new AccurateNumber(sine);
	}

	AccurateNumber cosine() throws NumberTooLargeException {
		if (isEqualTo(new AccurateNumber("0", "0")))
			return new AccurateNumber("1", "0");
		if (isEqualTo(new AccurateNumber("90", "0")))
			return new AccurateNumber("0", "0");
		if (isEqualTo(new AccurateNumber("180", "0")))
			return new AccurateNegativeNumber("1", "0");
		if (isEqualTo(new AccurateNumber("270", "0")))
			return new AccurateNumber("0", "0");
		double asDouble = asDouble();
		double cosine = Math.cos(Math.toRadians(asDouble));
		if (cosine < 0)
			return new AccurateNegativeNumber(cosine);
		return new AccurateNumber(cosine);
	}

	AccurateNumber tangent() throws NumberTooLargeException {
		return sine().quotient(cosine());
	}

	AccurateNumber arcsine() throws NumberTooLargeException {
		if (isGreaterThan(new AccurateNumber("1", "0"))) {
			AccurateNumber duplicate = this;
			AccurateNumber subtrahend = new AccurateNumber("0", "0");
			subtrahend.setDecimalDigits(decimalDigits);
			duplicate = duplicate.difference(subtrahend);
			return duplicate.arcsine();
		}
		if (isLessThan(new AccurateNegativeNumber("1", "0"))) {
			AccurateNumber duplicate = this;
			AccurateNumber addend = new AccurateNumber("0", "0");
			addend.setDecimalDigits(decimalDigits);
			duplicate = duplicate.sum(addend);
			return duplicate.arcsine();
		}
		double asDouble = asDouble();
		double arcsine = Math.asin(asDouble);
		arcsine /= Math.PI;
		arcsine *= 180.0;
		return new AccurateNumber(arcsine);
	}

	AccurateNumber arccosine() throws NumberTooLargeException {
		if (isGreaterThan(new AccurateNumber("1", "0"))) {
			AccurateNumber duplicate = this;
			AccurateNumber subtrahend = new AccurateNumber("0", "0");
			subtrahend.setDecimalDigits(decimalDigits);
			duplicate = duplicate.difference(subtrahend);
			return duplicate.arccosine();
		}
		if (isLessThan(new AccurateNegativeNumber("1", "0"))) {
			AccurateNumber duplicate = this;
			AccurateNumber addend = new AccurateNumber("0", "0");
			addend.setDecimalDigits(decimalDigits);
			duplicate = duplicate.sum(addend);
			return duplicate.arccosine();
		}
		double asDouble = asDouble();
		double arccosine = Math.asin(asDouble);
		arccosine /= Math.PI;
		arccosine *= 180.0;
		return new AccurateNumber(arccosine);
	}

	AccurateNumber arctangent() throws NumberTooLargeException {
		if (isGreaterThan(new AccurateNumber("1", "0"))) {
			AccurateNumber duplicate = this;
			AccurateNumber subtrahend = new AccurateNumber("0", "0");
			subtrahend.setDecimalDigits(decimalDigits);
			duplicate = duplicate.difference(subtrahend);
			return duplicate.arctangent();
		}
		if (isLessThan(new AccurateNegativeNumber("1", "0"))) {
			AccurateNumber duplicate = this;
			AccurateNumber addend = new AccurateNumber("0", "0");
			addend.setDecimalDigits(decimalDigits);
			duplicate = duplicate.sum(addend);
			return duplicate.arctangent();
		}
		double asDouble = asDouble();
		double arctangent = Math.atan(asDouble);
		arctangent /= Math.PI;
		arctangent *= 180.0;
		return new AccurateNumber(arctangent);
	}

	AccurateNumber absoluteValue() {
		return this;
	}

	boolean isEmpty() {
		return integerDigits.length() + decimalDigits.length() == 0;
	}

	void trimZeros() {
		if (integerDigits.length() > 0) {
			try {
				while (integerDigits.charAt(0) == '0')
					setIntegerDigits(integerDigits.substring(1, integerDigits.length()));
			} catch (StringIndexOutOfBoundsException outOfBounds) {
			}
		}
		if (decimalDigits.length() > 0) {
			try {
				while (decimalDigits.charAt(decimalDigits.length() - 1) == '0')
					setDecimalDigits(decimalDigits.substring(0, decimalDigits.length() - 1));
			} catch (StringIndexOutOfBoundsException outOfBounds) {

			}
		}
	}

	void round(int place) {
		if (place < 0)
			try {
				if (charToInt(decimalDigits.charAt(-place)) >= 5) {
					short d = 0;
					StringBuilder additiveDecDigs = new StringBuilder();
					while (++d < -place)
						additiveDecDigs.append("0");
					additiveDecDigs.append("1");
					setDecimalDigits(sum(new AccurateNumber("0", additiveDecDigs.toString())).getDecimalDigits());
				}
				setDecimalDigits(decimalDigits.substring(0, -place));
			} catch (StringIndexOutOfBoundsException outOfBounds) {

			}
		else if (place > 0) {
			if (charToInt(integerDigits.charAt(integerDigits.length() - place)) >= 5) {
				short d = 0;
				StringBuilder additiveIntDigs = new StringBuilder();
				additiveIntDigs.append("1");
				while (++d <= place)
					additiveIntDigs.append("0");
				setIntegerDigits(sum(new AccurateNumber(additiveIntDigs.toString(), "0")).getIntegerDigits());
			}
			setIntegerDigits(integerDigits.substring(0, integerDigits.length() - place) + "0");
			setDecimalDigits("");
			while (place-- > 1)
				setIntegerDigits(product(new AccurateNumber("10", "0")).getIntegerDigits());
		}
	}

	boolean isGreaterThan(AccurateNumber other) {
		if (other instanceof AccurateNegativeNumber)
			return true;
		StringBuilder comparativeA, comparativeB;
		comparativeA = new StringBuilder();
		comparativeB = new StringBuilder();
		int differenceInLength = 1 + Math.abs(integerDigits.length() - other.getIntegerDigits().length());
		while (--differenceInLength > 0)
			if (integerDigits.length() > other.getIntegerDigits().length())
				comparativeB.append("0");
			else
				comparativeA.append("0");
		comparativeA.append(integerDigits);
		comparativeB.append(other.getIntegerDigits());
		int comparisonIndex = 0;
		while (comparisonIndex < comparativeA.length()) {
			if (charToInt(comparativeA.charAt(comparisonIndex)) < charToInt(comparativeB.charAt(comparisonIndex)))
				return false;
			else if (charToInt(comparativeA.charAt(comparisonIndex)) > charToInt(comparativeB.charAt(comparisonIndex)))
				return true;
			++comparisonIndex;
		}
		if (other.getDecimalDigits().length() + decimalDigits.length() > 0) {
			comparativeA = new StringBuilder();
			comparativeB = new StringBuilder();
			differenceInLength = 1 + Math.abs(decimalDigits.length() - other.getDecimalDigits().length());
			comparativeA.append(decimalDigits);
			comparativeB.append(other.getDecimalDigits());
			while (--differenceInLength > 0)
				if (decimalDigits.length() > other.getDecimalDigits().length())
					comparativeB.append("0");
				else if (decimalDigits.length() < other.getDecimalDigits().length())
					comparativeA.append("0");
			return new AccurateNumber(new String(comparativeA), "")
					.isGreaterThan(new AccurateNumber(new String(comparativeB), ""));
		}
		return false;
	}

	boolean isLessThan(AccurateNumber other) {
		if (other instanceof AccurateNegativeNumber)
			return false;
		StringBuilder comparativeA, comparativeB;
		comparativeA = new StringBuilder();
		comparativeB = new StringBuilder();
		int differenceInLength = 1 + Math.abs(integerDigits.length() - other.getIntegerDigits().length());
		while (--differenceInLength > 0)
			if (integerDigits.length() > other.getIntegerDigits().length())
				comparativeB.append("0");
			else
				comparativeA.append("0");
		comparativeA.append(integerDigits);
		comparativeB.append(other.getIntegerDigits());
		int comparisonIndex = 0;
		while (comparisonIndex < comparativeA.length()) {
			if (charToInt(comparativeA.charAt(comparisonIndex)) > charToInt(comparativeB.charAt(comparisonIndex)))
				return false;
			else if (charToInt(comparativeA.charAt(comparisonIndex)) < charToInt(comparativeB.charAt(comparisonIndex)))
				return true;
			++comparisonIndex;
		}
		if (other.getDecimalDigits().length() + decimalDigits.length() > 0) {
			comparativeA = new StringBuilder();
			comparativeB = new StringBuilder();
			differenceInLength = 1 + Math.abs(decimalDigits.length() - other.getDecimalDigits().length());
			comparativeA.append(decimalDigits);
			comparativeB.append(other.getDecimalDigits());
			while (--differenceInLength > 0)
				if (decimalDigits.length() > other.getDecimalDigits().length())
					comparativeB.append("0");
				else if (decimalDigits.length() < other.getDecimalDigits().length())
					comparativeA.append("0");
			return new AccurateNumber(new String(comparativeA), "")
					.isLessThan(new AccurateNumber(new String(comparativeB), ""));
		}
		return false;
	}

	boolean isEqualTo(AccurateNumber other) {
		if (other instanceof AccurateNegativeNumber)
			return isEmpty() && other.isEmpty();
		StringBuilder comparativeA, comparativeB;
		comparativeA = new StringBuilder();
		comparativeB = new StringBuilder();
		int differenceInLength = 1 + Math.abs(integerDigits.length() - other.getIntegerDigits().length());
		while (--differenceInLength > 0)
			if (integerDigits.length() > other.getIntegerDigits().length())
				comparativeB.append("0");
			else
				comparativeA.append("0");
		comparativeA.append(integerDigits);
		comparativeB.append(other.getIntegerDigits());
		int comparisonIndex = 0;
		while (comparisonIndex < comparativeA.length()) {
			if (charToInt(comparativeA.charAt(comparisonIndex)) != charToInt(comparativeB.charAt(comparisonIndex)))
				return false;
			++comparisonIndex;
		}
		if (other.getDecimalDigits().length() + decimalDigits.length() > 0) {
			comparativeA = new StringBuilder();
			comparativeB = new StringBuilder();
			differenceInLength = 1 + Math.abs(decimalDigits.length() - other.getDecimalDigits().length());
			comparativeA.append(decimalDigits);
			comparativeB.append(other.getDecimalDigits());
			while (--differenceInLength > 0)
				if (decimalDigits.length() > other.getDecimalDigits().length())
					comparativeB.append("0");
				else if (decimalDigits.length() < other.getDecimalDigits().length())
					comparativeA.append("0");
			return new AccurateNumber(new String(comparativeA), "")
					.isEqualTo(new AccurateNumber(new String(comparativeB), ""));
		}
		return true;
	}

	boolean isLessThanOrEqualTo(AccurateNumber other) {
		return isEqualTo(other) || isLessThan(other);
	}

	boolean isGreaterThanOrEqualTo(AccurateNumber other) {
		return isEqualTo(other) || isGreaterThan(other);
	}

	boolean isInExclusiveRange(AccurateNumber lowerEnd, AccurateNumber upperEnd) {
		return isGreaterThan(lowerEnd) && isLessThan(upperEnd);
	}

	boolean isInInclusiveRange(AccurateNumber lowerEnd, AccurateNumber upperEnd) {
		return isGreaterThanOrEqualTo(lowerEnd) && isLessThanOrEqualTo(upperEnd);
	}

	int charToInt(char c) {
		switch (c) {
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		default:
			return 0;
		}
	}

	public void setIntegerDigits(String integerDigits) {
		this.integerDigits = integerDigits;
	}

	public void setDecimalDigits(String decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public String getIntegerDigits() {
		return integerDigits;
	}

	public String getDecimalDigits() {
		return decimalDigits;
	}
}

class AccurateNegativeNumber extends AccurateNumber {
	public AccurateNegativeNumber(String integerDigits, String decimalDigits) {
		setIntegerDigits(integerDigits);
		setDecimalDigits(decimalDigits);
	}

	public AccurateNegativeNumber(double value) {
		value *= -1;
		if (value == 0.0) {
			setIntegerDigits("0");
			setDecimalDigits("0");
			return;
		}
		short digitsBeforeDecimal = 0;
		while (Math.pow(10.0, digitsBeforeDecimal++) <= value)
			;
		--digitsBeforeDecimal;
		long decimalReference = (int) value;
		int iteration = 0;
		while (iteration++ < 7) {
			decimalReference *= 10;
			value *= 10;
			decimalReference = (int) value;
		}
		String allDigits = "" + decimalReference;
		StringBuilder integerDigs = new StringBuilder();
		StringBuilder decimalDigs = new StringBuilder();
		iteration = 0;
		while (iteration < 16) {
			if (iteration < digitsBeforeDecimal) {
				try {
					integerDigs.append(allDigits.charAt(iteration++));
				} catch (StringIndexOutOfBoundsException sioobe) {
					break;
				}
				continue;
			}
			try {
				decimalDigs.append(allDigits.charAt(iteration++));
			} catch (StringIndexOutOfBoundsException sioobe) {
				decimalDigs.append("0");
			}
		}
		setIntegerDigits(integerDigs.toString());
		setDecimalDigits(decimalDigs.toString());
	}

	@Override
	public String toString() {
		if (decimalDigits.length() > 0)
			return "-" + integerDigits + "." + decimalDigits;
		else
			return "-" + integerDigits;
	}

	@Override
	AccurateNumber sum(AccurateNumber other) {
		if (other instanceof AccurateNegativeNumber)
			return new AccurateNegativeNumber(super.sum(other).getIntegerDigits(), super.sum(other).getDecimalDigits());
		else {
			if (isGreaterThan(other))
				return new AccurateNegativeNumber((super.difference(other)).getIntegerDigits(),
						((super.difference(other)).getDecimalDigits()));
			else
				return other.difference(this);
		}
	}

	@Override
	AccurateNumber difference(AccurateNumber other) {
		if (other instanceof AccurateNegativeNumber)
			return new AccurateNegativeNumber(super.sum(other).getIntegerDigits(), super.sum(other).getDecimalDigits());
		else {
			if (isGreaterThan(other))
				return new AccurateNegativeNumber(super.difference(other).getIntegerDigits(),
						super.difference(other).getDecimalDigits());
			else
				return new AccurateNumber(other.difference(this).getIntegerDigits(),
						other.difference(this).getDecimalDigits());
		}
	}

	@Override
	AccurateNumber product(AccurateNumber other) {
		if (other instanceof AccurateNegativeNumber)
			return super.product(new AccurateNumber(other.getIntegerDigits(),
					other.getDecimalDigits()));/**
												 * A negative number multiplied
												 * by another negative number
												 * returns a positive number.
												 */
		else
			return new AccurateNegativeNumber(super.product(other).getIntegerDigits(),
					super.product(other).getDecimalDigits());
	}

	@Override
	AccurateNumber quotient(AccurateNumber other) {
		if (other instanceof AccurateNegativeNumber)
			return super.quotient(
					other);/**
							 * A negative number divided by another negative
							 * number returns a positive number.
							 */
		else
			return new AccurateNegativeNumber(super.quotient(other).getIntegerDigits(),
					super.quotient(other).getDecimalDigits());
	}

	@Override
	AccurateNumber power(int x) {
		if (x % 2 == 0)
			return super.power(x);
		else
			return new AccurateNegativeNumber(super.power(x).getIntegerDigits(), super.power(x).getDecimalDigits());
	}

	@Override
	AccurateNumber absoluteValue() {
		return new AccurateNumber(integerDigits, decimalDigits);
	}

	@Override
	boolean isGreaterThan(AccurateNumber other) {
		if (!(other instanceof AccurateNegativeNumber))
			return isEmpty() && other.isEmpty();
		return super.isLessThan(new AccurateNumber(other.getIntegerDigits(), other.getDecimalDigits()));
	}

	@Override
	boolean isLessThan(AccurateNumber other) {
		if (!(other instanceof AccurateNegativeNumber))
			return isEmpty() && other.isEmpty();
		return super.isGreaterThan(new AccurateNumber(other.getIntegerDigits(), other.getDecimalDigits()));
	}

	@Override
	boolean isEqualTo(AccurateNumber other) {
		if (!(other instanceof AccurateNegativeNumber))
			return isEmpty() && other.isEmpty();
		return super.isEqualTo(new AccurateNumber(other.getIntegerDigits(), other.getDecimalDigits()));
	}
}

class NumberTooLargeException extends Exception {
	private static final long serialVersionUID = 1L;

	public NumberTooLargeException() {
		super();
	}
}
