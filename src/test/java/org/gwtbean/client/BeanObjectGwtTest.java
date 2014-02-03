package org.gwtbean.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;



/**
 * Test {@link BeanObject} methods.
 * 
 * @author HaoPo Liao
 *
 */
public class BeanObjectGwtTest extends GwtBeanTestCase {

	private static final String TEMPLATE_JSON = 
					"{" +
					"\"name\":\"HaoPo\"," +
					"\"address\":{\"city\":\"Taiwan\"}," +
					"\"items\":[{},{}]" +
					"}";

	/**
	 * Test the transfer on String value.
	 */
	public void testStringValue() {
		BeanObject object = BeanObject.createObject().cast();

		object.setPropertyValue("foo", "bar");
		object.setPropertyValue("foo1", new String("bar1"));
		object.setStringValue("foo2", "bar2");
		object.setStringValue("foo3.foo4", "bar3");
		object.setStringValue("foo5.foo6.foo7", "bar5");

		assertEquals( "bar", object.getStringValue("foo") );
		assertEquals( "bar1", object.getStringValue("foo1") );
		assertEquals( "bar2", object.getStringValue("foo2") );
		assertEquals( "bar3", object.getStringValue("foo3.foo4") );
		assertEquals( "bar5", object.getStringValue("foo5.foo6.foo7") );
	}

	/**
	 * Test the transfer on Number value.
	 */
	public void testNumberValue() {
		BeanObject object = BeanObject.createObject().cast();

		object.setStringValue("fooInt", 123);
		object.setStringValue("fooLong", 123L);
		object.setStringValue("fooFloat", 123F);
		object.setStringValue("fooFloat1", 123.321F);
		object.setStringValue("fooDouble", 123D);
		object.setStringValue("fooDouble1", 123.321D);

		assertEquals( "123", object.getStringValue("fooInt") );
		assertEquals( "123", object.getStringValue("fooLong") );
		assertEquals( "123.0", object.getStringValue("fooFloat") );
		assertEquals( "123.321", object.getStringValue("fooFloat1") );
		assertEquals( "123.0", object.getStringValue("fooDouble") );
		assertEquals( "123.321", object.getStringValue("fooDouble1") );
	}

	/**
	 * Test the transfer on Boolean value.
	 */
	public void testBooleanValue() {
		BeanObject object = BeanObject.createObject().cast();

		object.setStringValue("fooTrue", true);
		object.setStringValue("fooTrue1", new Boolean(true) );
		object.setStringValue("fooTrue2", new Boolean("true") );
		object.setStringValue("fooFalse", false);
		object.setStringValue("fooFalse1", new Boolean(false) );
		object.setStringValue("fooFalse2", new Boolean("false") );

		assertEquals( "true", object.getStringValue("fooTrue") );
		assertEquals( "true", object.getStringValue("fooTrue1") );
		assertEquals( "true", object.getStringValue("fooTrue2") );
		assertEquals( "false", object.getStringValue("fooFalse") );
		assertEquals( "false", object.getStringValue("fooFalse1") );
		assertEquals( "false", object.getStringValue("fooFalse2") );
	}

	/**
	 * Test the [set|get]PropertyValue() method.
	 */
	public void testPropertyValue() {
		BeanObject object = BeanObject.createObject().cast();

		object.setPropertyValue("foo", "hello");
		object.setPropertyValue("fooInteger", 22);
		object.setPropertyValue("fooDouble", 22.5);
		object.setPropertyValue("fooBoolean", true);
		BeanObject testObject = BeanObject.createObject().cast();
		object.setPropertyValue( "fooObject", testObject );
		JsArray<?> testArray = BeanObject.createArray().cast();
		object.setPropertyValue("fooArray", testArray);

		BeanObject dsObject = JSONParser
				.parseStrict( new JSONObject(object).toString() )
				.isObject()
				.getJavaScriptObject()
				.cast();

		//		assertEquals( "String failed", "hello", dsObject.getPropertyValue("foo") );
		//		assertEquals( "Integer failed", 22, dsObject.getPropertyValue("fooInteger") );
		//		assertEquals( "Double failed", 22.5, dsObject.getPropertyValue("fooDouble") );
		//		assertEquals( "Boolean failed", true, dsObject.getPropertyValue("fooBoolean") );
		//		assertEquals( "JavaScriptObject failed", testObject , object.getPropertyValue("fooObject") );
		//		assertEquals( "JsArray failed", testArray, object.getPropertyValue("fooArray") );
	}

	/**
	 * Test nested property.
	 * 
	 */
	public void testNestedProperty() {
		BeanObject object = BeanObject.createObject().cast();
		object.setPropertyValue("foo.bar", "bar");
		System.out.println( object.getPropertyValue("foo.bar") );
	}

	public void testGetPropertyValueFromJson() {
		BeanObject object = JSONParser
				.parseStrict(TEMPLATE_JSON)
				.isObject()
				.getJavaScriptObject()
				.cast();


		assertEquals( "HaoPo", object.getPropertyValue("name") );
		assertEquals( "Taiwan", object.getPropertyValue("address.city") );
	}

	public void testToString() {
		assertEquals( "string", BeanObject.toString("string") );
		assertEquals( "true", BeanObject.toString(true) );
		assertEquals( "false", BeanObject.toString(false) );
		assertEquals( "true", BeanObject.toString(new Boolean("true")) );
		assertEquals( "false", BeanObject.toString(new Boolean("false")) );
		assertEquals( "123", BeanObject.toString(123) );
		assertEquals( "123", BeanObject.toString((Integer)123) );
		assertEquals( "123", BeanObject.toString(123L) );
		assertEquals( "123", BeanObject.toString((Long)123L) );
		assertEquals( "123.0", BeanObject.toString((Float)123F) );
		assertEquals( "123.0", BeanObject.toString((Double)123D) );
	}

}
