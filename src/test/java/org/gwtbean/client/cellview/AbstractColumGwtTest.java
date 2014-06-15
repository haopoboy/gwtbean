package org.gwtbean.client.cellview;

import org.gwtbean.client.BeanObject;
import org.gwtbean.client.GwtBeanTestCase;

public class AbstractColumGwtTest extends GwtBeanTestCase {
	
	public void testWhenCellTypeIsNull() {
		boolean catched = false;
		try {
			AbstractColumn<BeanObject> impl = new AbstractColumn<BeanObject>(null, "propertyPath") {};
		} catch (NullPointerException exception) {
			catched = true;
		}
		
		assertTrue("No NullPointerException throws when cellType is null", catched);
	}
	
	public void testWhenPropertyPathIsNull() {
		boolean catched = false;
		try {
			AbstractColumn<BeanObject> impl = new AbstractColumn<BeanObject>("test", null) {};
		} catch (NullPointerException exception) {
			catched = true;
		}
		
		assertTrue("No NullPointerException throws when cellType is null", catched);
	}

}
