package org.gwtbean.shared;

/**
 * Collect assertion methods.
 * 
 * @author HaoPo Liao
 *
 */
public class Assertions {

	/**
	 * Assert object is not null.
	 * 
	 * @param object be asserted
	 * @throws NullPointException if object null
	 */
	public static void notNull(Object object) {
		if (null == object) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * Assert object is not null.
	 * 
	 * @param object be asserted
	 * @param format {@link String#format(String, Object...)}
	 * @param args {@link String#format(String, Object...)}
	 * @throws NullPointException if object or format is null
	 */
	public static void notNull(Object object, String format, Object... args) {
		if (null == object) {
			throw new NullPointerException( String.format(format, args) );
		}
	}
}
