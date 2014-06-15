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
	 * @param message
	 * @throws NullPointException if object is null
	 */
	public static void notNull(Object object, String message) {
		if (null == object) {
			throw new NullPointerException(message);
		}
	}
	
	/**
	 * Assert object is not null nor empty.
	 * 
	 * @param object be asserted
	 * @throws NullPointException if object is null or object.toString() is empty.
	 */
	public static void notNullNorEmpty(Object object) {
		if (null == object || object.toString().isEmpty() ) {
			throw new NullPointerException("object is null or empty");
		}
	}
	
	/**
	 * Assert object is not null nor empty.
	 * 
	 * @param object be asserted
	 * @param message
	 * @throws NullPointException if object is null or object.toString() is empty.
	 */
	public static void notNullNorEmpty(Object object, String message) {
		if (null == object || object.toString().isEmpty() ) {
			throw new NullPointerException(message);
		}
	}
}
