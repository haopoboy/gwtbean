package org.gwtbean.client.cellview;

import org.gwtbean.client.BeanObject;
import org.gwtbean.client.GwtBeanTestCase;
import org.gwtbean.client.PropertyAccessor;

import com.google.gwt.core.client.JavaScriptObject;

public class PropertyAccesstorGwtTest extends GwtBeanTestCase {

	public void testSplitPropertyPath() {
		String property = "foo.bar";
		String[] properties = PropertyAccessor.splitPropertyPath(property);
		
		assertEquals( "foo", properties[0] );
		assertEquals( "bar", properties[1] );
	}
	
	public void testGetIndexOfArrayProperty() {
		String oneProperty = "foo[0]";
		String twoProperty = "foo[1]";
		
		assertEquals( 0, PropertyAccessor.getIndexOfArrayProperty(oneProperty) );
		assertEquals( 1, PropertyAccessor.getIndexOfArrayProperty(twoProperty) );
	}
	
	public void testGetNameOfArrayProperty() {
		String oneProperty = "foo[0]";
		String twoProperty = "bar[1]";
		
		assertEquals( "foo", PropertyAccessor.getNameOfArrayProperty(oneProperty) );
		assertEquals( "bar", PropertyAccessor.getNameOfArrayProperty(twoProperty) );
	}
	
	public void testFillupNestedObject() {
		BeanObject object = BeanObject.createObject().cast();
		PropertyAccessor.fillupNestedObject("foo.bar", object);
		
		assertNotNull( object.getPropertyValue("foo") );
		assertNull( object.getPropertyValue("foo.bar") );
	}
	
	public void testFillupNestedObjectWithArray() {
		BeanObject object = BeanObject.createObject().cast();
		PropertyAccessor.fillupNestedObject("foo[0].bar", object);
		
		assertNotNull( object.getPropertyValue("foo") );
		assertEquals( JavaScriptObject.class, object.getPropertyValue("foo").getClass() );
	}
}
