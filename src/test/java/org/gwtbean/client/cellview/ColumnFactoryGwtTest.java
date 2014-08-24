package org.gwtbean.client.cellview;

import org.gwtbean.client.BeanObject;
import org.gwtbean.client.GwtBeanTestCase;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;

public class ColumnFactoryGwtTest extends GwtBeanTestCase {
	
	public void testCreateTextColumn() {
		BeanObject object = BeanObject.createObject().cast();
		object.setPropertyValue("foo", "bar");
		
		Column<BeanObject, String> column = ColumnFactory.createTextColumn("foo");
		assertNotNull(column);
		assertEquals( "bar", column.getValue(object) );
	}
	
	public void testCreateEditTextColumn() {
		BeanObject object = BeanObject.createObject().cast();
		object.setPropertyValue("foo", "bar");
		
		Column<BeanObject, String> column = ColumnFactory.createEditTextColumn("foo");
		assertNotNull(column);
		assertEquals( "bar", column.getValue(object) );
		
		// Change value.
		FieldUpdater<BeanObject, String> updater = column.getFieldUpdater();
		assertNotNull(updater);
		updater.update(0, object, "changed");
		assertEquals( "changed", column.getValue(object) );
	}
}
