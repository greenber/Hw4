package project3;

import java.math.BigInteger;

/**
 * A simple implementation of Fractions.
 * 
 * @author Samuel A. Rebelsky
 * @author YOUR NAME HERE
 * @author CSC152 2005S
 * @version 1.0 of February 2005
 */
public class Fraction {
	// +------------------+---------------------------------------------
	// | Design Decisions |
	// +------------------+
	/*
	 * (1) Denominators are always positive. Therefore, negative fractions are
	 * represented with a negative numerator. Similarly, if a fraction has a
	 * negative numerator, it is negative.
	 * 
	 * (2) Fractions are not necessarily stored in simplified form. To obtain a
	 * fraction in simplified form, one must call the simplify method.
	 */

	// +--------+-------------------------------------------------------
	// | Fields |
	// +--------+

	/** The numerator of the fraction. Can be positive, zero or negative. */
	BigInteger num;

	/** The denominator of the fraction. Must be non-negative. */
	BigInteger denom;

	// +--------------+-------------------------------------------------
	// | Constructors |
	// +--------------+

	/**
	 * Build a new fraction with numerator num and denominator denom.
	 * 
	 * Warning! Not yet stable.
	 */
	public Fraction(BigInteger num, BigInteger denom) {
		this.num = num;
		this.denom = denom;
	} // Fraction(BigInteger, BigInteger)

	/**
	 * Build a new fraction with numerator num and denominator denom.
	 * 
	 * Warning! Not yet stable.
	 */
	public Fraction(int num, int denom) {
		this.num = BigInteger.valueOf(num);
		this.denom = BigInteger.valueOf(denom);
	} // Fraction(int, int)

	// +---------+------------------------------------------------------
	// | Methods |
	// +---------+

	/**
	 * Express this fraction as a double.
	 */
	public double doubleValue() {
		return this.num.doubleValue() / this.denom.doubleValue();
	} // doubleValue()

	/**
	 * Add the fraction other to this fraction.
	 */
	public Fraction add(Fraction addMe) {
		BigInteger resultNumerator;
		BigInteger resultDenominator;

		// The denominator of the result is the
		// product of this object's denominator
		// and addMe's denominator
		resultDenominator = this.denom.multiply(addMe.denom);
		// The numerator is more complicated
		resultNumerator = (this.num.multiply(addMe.denom)).add(addMe.num
				.multiply(this.denom));

		// Return the computed value
		return new Fraction(resultNumerator, resultDenominator);
	}// add(Fraction)

	/**
	 * Convert this fraction to a string for ease of printing.
	 */
	public String toString() {
		// Special case: It's zero
		if (this.num.equals(BigInteger.ZERO)) {
			return "0";
		} // if it's zero

		// Lump together the string represention of the numerator,
		// a slash, and the string representation of the denominator
		// return this.num.toString().concat("/").concat(this.denom.toString());
		return this.num + "/" + this.denom;
	} // toString()

	// public Fraction subtract(Fraction sub)
	// {
	// BigInteger newdenom = this.LCD(sub);
	//
	// }

	public Fraction divide(Fraction divisor) {

		BigInteger num = this.num.multiply(divisor.denom);
		BigInteger denom = this.denom.multiply(divisor.num);

		return new Fraction(num, denom);

	}// divide

	public BigInteger LCD(Fraction comp) {
		BigInteger leastcommon = BigInteger.ZERO;
		for (BigInteger i = BigInteger.ONE; i.compareTo(comp.denom) != 1; i = i
				.add(BigInteger.ONE)) {
			leastcommon = this.denom.multiply(i);
			if (leastcommon.mod(comp.denom) == BigInteger.ZERO) {
				break;
			}// if
		}// for

		return leastcommon;
	}// LCD

	public BigInteger GCD(Fraction comp)

	{
		BigInteger bigger;
		BigInteger smaller;
		if (comp.denom.compareTo(this.denom) == -1) {
			bigger = this.denom;
			smaller = comp.denom;

		} else {
			bigger = comp.denom;
			smaller = this.denom;
		}// end of if

		BigInteger GCD = BigInteger.ONE;
		for (BigInteger i = smaller; i.compareTo(BigInteger.ZERO) != 0; i = i
				.subtract(BigInteger.ONE)) {
			if ((smaller.mod(i) == BigInteger.ZERO)
					&& (bigger.mod(i) == BigInteger.ZERO)) {
				GCD = i;
				break;
			}// if

		}// for
		return GCD;
	}// GCD

	public Fraction toFraction(String frac) {
		int index = frac.indexOf('/');
		BigInteger num = new BigInteger(frac.substring(0, index));
		BigInteger denom = new BigInteger(frac.substring(index + 1));
		return new Fraction(num, denom);
	}// toFraction

	// These two methods are from Helen Dougherty and Shen Zhang
	public Fraction fractional() throws Exception {
		BigInteger resultNumerator = this.num.remainder(this.denom);

		return new Fraction(resultNumerator, this.denom);

	}// fractional

	/**
	 * multiply two fractions
	 */
	public Fraction multiplyFraction(Fraction multiplyMe) throws Exception {
		BigInteger resultNumerator;
		BigInteger resultDenominator;

		// The denominator of the result is the
		// product of this object's denominator
		// and addMe's denominator
		resultDenominator = this.denom.multiply(multiplyMe.denom);
		// The numerator is more complicated
		resultNumerator = this.num.multiply(multiplyMe.num);
		// Return the computed value
		if (resultDenominator.compareTo(BigInteger.ZERO) == 0)
			throw new Exception("The denominator is zero.");
		return new Fraction(resultNumerator, resultDenominator);

	} // multiplyFraction

	public Fraction subtract(Fraction sub) {
		BigInteger resultNumerator;
		BigInteger resultDenominator;

		resultDenominator = this.denom.multiply(sub.denom);

		resultNumerator = (this.num.multiply(sub.denom)).subtract(sub.num
				.multiply(this.denom));

		return new Fraction(resultNumerator, resultDenominator);

	}// subtract
}// Class

