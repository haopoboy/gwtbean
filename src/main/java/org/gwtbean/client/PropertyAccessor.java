package org.gwtbean.client;

import java.util.List;


/**
 * Accessor tool to help find out properties.
 * 
 * @author HaoPo Liao
 *
 */
public class PropertyAccessor {

	/**
	 * Path separator for nested properties.
	 * Follows normal Java conventions: getFoo()getBar() would be "foo.bar".
	 */
	public static final String NESTED_PROPERTY_SEPARATOR = "\\.";


	/**
	 * Marker that indicates the start of a property key for an
	 * indexed or mapped property like "person.addresses[0]".
	 */
	public static final String PROPERTY_KEY_PREFIX = "[";


	/**
	 * Marker that indicates the end of a property key for an
	 * indexed or mapped property like "person.addresses[0]".
	 */
	public static final String PROPERTY_KEY_SUFFIX = "]";

	/**
	 * 
	 * 
	 * @param propertyPath
	 * @param object
	 * @return
	 */
	public static BeanObject getFinalNestedObject(String propertyPath, BeanObject object) {
		String[] propertyList = splitPropertyPath(propertyPath);

		for ( int i = 0; i < propertyList.length - 1; i++ ) {
			String nestedProperty = propertyList[i];
			int propertyIndex = getIndexOfArrayProperty(nestedProperty);
			
			if (propertyIndex == -1) {
				object =  object.getBeanObject(nestedProperty);
			} else {
				nestedProperty = getNameOfArrayProperty(nestedProperty);
				List<BeanObject> list = object.getPropertyList(nestedProperty);
				object = list.get(propertyIndex);
			}
		}
		return object;
	}
	
	/**
	 * Avoid {@link NullPointerException} when access with nested property.
	 * 
	 * @param propertyPath
	 */
	public static final void fillupNestedObject(String propertyPath, BeanObject object) {
		String[] propertyList = propertyPath.split(NESTED_PROPERTY_SEPARATOR);

		for ( int i = 0; i < propertyList.length - 1; i++ ) {
			String nestedProperty = propertyList[i];
			int propertyIndex = getIndexOfArrayProperty(nestedProperty);

			// Re-Index the object.
			if (propertyIndex == -1) {
				BeanObject transientObject = object.getBeanObject(nestedProperty);
				object.setPropertyValue(nestedProperty, transientObject);
				object = transientObject;
			} else {
				nestedProperty = getNameOfArrayProperty(nestedProperty);
				BeanObject array = object.getJsArray(nestedProperty).cast();
				object.setPropertyValue(nestedProperty, array);
				object = array.getBeanObject("" + propertyIndex).cast();
			}
		}
	}
	
	/**
	 * <ul>
	 * <li>foo.bar => bar</li>
	 * <li>foo.bar.wine => wine</li>
	 * <li>foo[0].bar = > bar</li>
	 * </ul>
	 * 
	 * @param propertyPath
	 * @return
	 */
	public static String getFinalNestedPropertyName(String propertyPath) {
		String[] propertyList = splitPropertyPath(propertyPath);
		return propertyList[propertyList.length - 1];
	}
	
	/**
	 * 
	 * @param arrayProperty
	 * @return
	 */
	public static String getNameOfArrayProperty(String arrayProperty) {
		return arrayProperty.substring( 0, arrayProperty.indexOf(PROPERTY_KEY_PREFIX) );
	}
	
	/**
	 * <ul>Returns:
	 * <li>foo => -1</li>
	 * <li>foo[0] => 0</li>
	 * <li>foo[1] => 1</li>
	 * </ul>
	 * 
	 * @param arrayProperty
	 * @return
	 */
	public static int getIndexOfArrayProperty(String arrayProperty) {
		int prefixIndex = arrayProperty.indexOf(PROPERTY_KEY_PREFIX);
		if (prefixIndex == -1) {
			return -1;
		}

		prefixIndex++;
		int suffixIndex = arrayProperty.indexOf(PROPERTY_KEY_SUFFIX);
		String stringOfIndex = arrayProperty.substring(prefixIndex, suffixIndex);
		return Integer.valueOf(stringOfIndex);
	}

	/**
	 * 
	 * @param propertyPath
	 * @return
	 */
	public static String[] splitPropertyPath(String propertyPath) {
		return propertyPath.split(NESTED_PROPERTY_SEPARATOR);
	}
}
