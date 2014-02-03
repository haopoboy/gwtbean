package org.gwtbean.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * <p>
 * Bean object represent enhanced {@link JavaScriptObject} with some useful methods,
 * property value could be set or get by it's name.
 * </p>
 * <ul>list of method support:
 * <li>{set | get}PropertyValue, handle all value of property.</li>
 * <li>{set | get}StringValue, handle string property.</li>
 * <li>{set | get}BooleanValue, handle boolean property.</li>
 * <li>{set | get}IntegerValue, handle Integer property.</li>
 * <li>{set | get}JavaScripObject, handle {@link JavaScriptObject} property</li>
 * <li>{set | get}JsArray:setJsArray,handle {@link JsArray} property</li>
 * </ul>
 * 
 * @author HaoPo Liao
 *
 */
public class BeanObject extends JavaScriptObject {

	/**
	 * Path separator for nested properties.
	 * Follows normal Java conventions: getFoo()getBar() would be "foo.bar".
	 */
	private static final String NESTED_PROPERTY_SEPARATOR = "\\.";


	/**
	 * Marker that indicates the start of a property key for an
	 * indexed or mapped property like "person.addresses[0]".
	 */
	private static final String PROPERTY_KEY_PREFIX = "[";


	/**
	 * Marker that indicates the end of a property key for an
	 * indexed or mapped property like "person.addresses[0]".
	 */
	private static final String PROPERTY_KEY_SUFFIX = "]";

	protected BeanObject() {}

	final native void setPropertyObjectValue(String property, Object value) /*-{
		this[property] = value;
	}-*/;

	final native Object getPropertyObjectValue(String property) /*-{
		return this[property];
	}-*/;

	final native Object getPropertyObjectValue(String property, Object defaultValue) /*-{
		return this[property] || defaultValue;
	}-*/;
	
	final native void setPropertyStringValue(String property, String value) /*-{
		this[property] = value;
	}-*/;

	final native void setPropertyDoubleValue(String property, double value) /*-{
		this[property] = value;
	}-*/;
	
	final native void setPropertyBooleanValue(String property, boolean value) /*-{
		this[property] = value;
	}-*/;
	
	final native JavaScriptObject getJavaScriptObject(String property) /*-{
		return this[property] || {};
	}-*/;

	final native JavaScriptObject setJavaScriptObject(String property, JavaScriptObject jsObject) /*-{
		this[property] = jsObject;
	}-*/;

	final List<BeanObject> getPropertyList(String property) {
		JsArray<BeanObject> array = getJsArray(property);
		return toList(array);
	}

	final void setPropertyList(String property, List<BeanObject> list) {
		setJsArray(property, toJsArray(list) );
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	static final String toString(Object value) {
		return null == value ? null : value.toString();
	}

	/**
	 * Get property value. All type of value would cast to Object.
	 * 
	 * @param property
	 * @return
	 */
	public final Object getPropertyValue(String property) {
		BeanObject object = getFinalNestedObject(property).cast();
		return object.getPropertyObjectValue( getFinalNestedPropertyName(property) );
	}

	/**
	 * Set value of property. 
	 * Support primitive types or object that extends {@link JavaScriptObject}.
	 * 
	 * @param propertyPath
	 * @param value
	 * @return
	 */
	public final void setPropertyValue(String propertyPath, Object value) {
		PropertyAccessor.fillupNestedObject(propertyPath, this);
		BeanObject object = getFinalNestedObject(propertyPath);
		String finalPorperty = getFinalNestedPropertyName(propertyPath);
		
		// Redirect the set method.
		if (value instanceof JavaScriptObject) {
			object.setJavaScriptObject( finalPorperty, (JavaScriptObject)value );
		}
		else if (value instanceof Number) {
			object.setPropertyDoubleValue( finalPorperty, ((Number)value).doubleValue() );
		}
		else if (value instanceof Boolean) {
			object.setPropertyBooleanValue( finalPorperty, (Boolean)value );
		} else {
			object.setPropertyStringValue( finalPorperty, toString(value) );
		}
	}

	/**
	 * Get property value or default when property is empty. 
	 * All type of value would cast to Object.
	 * 
	 * @param property
	 * @param defaultValue
	 * @return
	 */
	public final Object getPropertyValue(String property, Object defaultValue) {
		BeanObject object = getFinalNestedObject(property).cast();
		return object.getPropertyObjectValue( getFinalNestedPropertyName(property), defaultValue );
	}
	
	/**
	 * Cast the value to String and set to property.
	 * 
	 * @param propertyPath
	 * @param value
	 */
	public final void setStringValue(String propertyPath, Object value) {
		value = toString(value);
		setPropertyValue(propertyPath, value);
	}
	
	/**
	 * Get and auto cast all type of value to String.
	 * 
	 * @param propertyPath
	 * @return
	 */
	public final String getStringValue(String propertyPath) {
		return (String) getPropertyValue(propertyPath).toString();
	}

	/**
	 * Get and auto cast all type of value(or default value) to String.
	 * 
	 * @param propertyPath
	 * @return
	 */
	public final String getStringValue(String propertyPath, String defaultValue) {
		return (String) getPropertyValue(propertyPath, defaultValue).toString();
	}

	public final native <E extends JavaScriptObject> JsArray<E> getJsArray(String property) /*-{
		return this[property] || [];
	}-*/;

	public final native <E extends JavaScriptObject> JsArray<E> setJsArray(String property, JsArray<?> array) /*-{
		return this[property] = array;
	}-*/;


	/**
	 * Get final object through property expression.
	 * 
	 * @param propertyPath
	 * @return
	 */
	public final <E extends BeanObject> E getFinalNestedObject(String propertyPath) {
		return PropertyAccessor.getFinalNestedObject(propertyPath, this).cast();
	}

	/**
	 * 
	 * 
	 * @param propertyPath
	 * @return
	 */
	public static final String getFinalNestedPropertyName(String propertyPath) {
		return PropertyAccessor.getFinalNestedPropertyName(propertyPath);
	}

	/**
	 * Get index from property name, such as property[20] is return 20.
	 * If there are no index prefix, it return -1.
	 * 
	 * @param propertyName
	 * @return index of array property
	 */
	public static final int getIndexOfArrayProperty(String propertyName) {

		int prefixIndex = propertyName.indexOf(PROPERTY_KEY_PREFIX);
		if (-1 == prefixIndex) {
			return -1;
		}

		prefixIndex++;
		int suffixIndex = propertyName.indexOf(PROPERTY_KEY_SUFFIX);
		String stringOfIndex = propertyName.substring(prefixIndex, suffixIndex);
		return Integer.valueOf(stringOfIndex);
	}

	/**
	 * Get name of array property.
	 * Example: "property[0]" returns "property".
	 * 
	 * @param propertyName
	 * @return
	 */
	public static final String getNameOfArrayProperty(String propertyName) {
		return propertyName.substring( 0, propertyName.indexOf(PROPERTY_KEY_PREFIX) );
	}

	/**
	 * Cast {@link JsArray} to {@link List}.
	 * 
	 * @param array
	 * @return
	 */
	public static final <E extends JavaScriptObject> List<E> toList(JsArray<E> array) {
		List<E> list = new ArrayList<E>();

		for (int i = 0; i < array.length(); i++) {
			list.add(array.get(i));
		}
		return list;
	}

	/**
	 * Cast {@link List} to {@link JsArray}.
	 * 
	 * @param list
	 * @return
	 */
	public static final <E extends JavaScriptObject> JsArray<E> toJsArray(List<E> list) {
		JsArray<E> jsArray = JsArray.createArray().cast();

		for (E e : list) {
			jsArray.push(e);
		}

		return jsArray;
	}

	public final native BeanObject getBeanObject(String property) /*-{
		return this[property] || {};
	}-*/;

	/**
	 * Support nested properties.
	 * 
	 * @param property
	 * @return
	 */
	public final <E extends JavaScriptObject> List<E> getList(String property) {
		BeanObject object = getFinalNestedObject(property);
		JsArray<E> array = object.getJsArray(property);
		return toList(array);
	}
	
	/**
	 * Support nested properties.
	 * 
	 * @param property
	 * @param list
	 */
	public final <E extends JavaScriptObject> void setList(String propertyPath, List<E> list) {
		PropertyAccessor.fillupNestedObject(propertyPath, this);
		BeanObject object = getFinalNestedObject(propertyPath);
		JsArray<E> array = toJsArray(list);
		object.setJsArray(propertyPath, array);
	}
}