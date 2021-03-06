package hr.fer.zemris.java.tecaj.hw5.db.operators;

/**
 * LessOperator class represents {@literal "<"} comparison operator.
 * It implements IComparisonOperator interface.
 * @author Filip Gulan
 * @version 1.0
 *
 */
public class LessOperator implements IComparisonOperator {

	/**
	 * Checks if given value1 is lexicographically lower than value2. 
	 * @param value1 Value to compare.
	 * @param value2 Value to compare with.
	 * @return <code>true</code> if given strings satisfy comparison condition,
	 * <code>false</code> otherwise.
	 * @throws IllegalArgumentException If wildcard is used.
	 */
	@Override
	public boolean satisfied(String value1, String value2) {
		if (value2.contains("*")) {
			throw new IllegalArgumentException("Wildcard can only be used with equal operator!");
		}
		return value1.compareTo(value2) < 0;
	}

}
