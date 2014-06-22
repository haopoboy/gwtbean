package org.gwtbean.client.cellview;

import org.gwtbean.client.BeanObject;
import org.junit.Test;

import com.google.gwt.user.cellview.client.Column;

public class AbstractColumUnitTest {

	@Test(expected = NullPointerException.class)
	public void whenPropertyPathIsNull() {
		new AbstractColumn<BeanObject>(null) {

			@Override
			protected Column<BeanObject, ?> createColumn() {
				return null;
			}};
	}

}
