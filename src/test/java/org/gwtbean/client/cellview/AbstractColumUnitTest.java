package org.gwtbean.client.cellview;

import org.gwtbean.client.BeanObject;
import org.gwtbean.client.MethodNotSupportedException;
import org.junit.Test;

import com.google.gwt.user.cellview.client.Column;

public class AbstractColumUnitTest {
	
	private final AbstractColumn<BeanObject> impl = new AbstractColumn<BeanObject>("") {
		
		@Override
		protected Column<BeanObject, ?> createColumn() {
			return null;
		}
	};

	@Test(expected = NullPointerException.class)
	public void whenPropertyPathIsNull() {
		new AbstractColumn<BeanObject>(null) {

			@Override
			protected Column<BeanObject, ?> createColumn() {
				return null;
			}};
	}
	
	@Test(expected = MethodNotSupportedException.class)
	public void asWidget() {
		impl.asWidget();
	}
}