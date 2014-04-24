package org.gwtbean.client.cellview.ui;

import java.util.Iterator;

import org.gwtbean.client.MethodNotSupportedException;
import org.gwtbean.client.cellview.IsColumn;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * Support UiBinder that implements {@link HasWidgets}.
 * 
 * @param <T>
 * @author HaoPo Liao
 *
 */
public class BeanTable<T> extends com.google.gwt.user.cellview.client.CellTable<T> 
	implements HasWidgets.ForIsWidget {

	/**
	 * 
	 * 
	 * @throws IllegalArgumentException if widget does not implement {@link IsColumn}.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(IsWidget w) {
		assertIsColumn(w);
		IsColumn<T> column = (IsColumn<T>) w;
		column.build(this);
	}

	/**
	 * Assert widget implements {@link IsColumn} or not.
	 * 
	 * @param w
	 * @throws IllegalArgumentException if widget does not implement {@link IsColumn}.
	 */
	private static void assertIsColumn(IsWidget w) {
		if ( !(w instanceof IsColumn) ) {
			throw new IllegalArgumentException("Only support child Column widget, see IsColumn.");
		}
	}
	
	/**
	 * @throws MethodNotSupportedException if invoked.
	 */
	private static void alwaysThrowsNotSupported() throws MethodNotSupportedException {
		throw new MethodNotSupportedException("This widget is for UiBinder, not all methods are suppported");
	}

	/**
	 * @throws MethodNotSupportedException if call this method.
	 */
	@Override
	public void add(Widget w) {
		alwaysThrowsNotSupported();
	}

	/**
	 * @throws MethodNotSupportedException if call this method.
	 */
	@Override
	public void clear() {
		alwaysThrowsNotSupported();
	}

	/**
	 * @throws MethodNotSupportedException if call this method.
	 */
	@Override
	public Iterator<Widget> iterator() {
		alwaysThrowsNotSupported();
		return null;
	}

	/**
	 * @throws IllegalArgumentException if call this method.
	 */
	@Override
	public boolean remove(Widget w) {
		alwaysThrowsNotSupported();
		return false;
	}

	/**
	 * @throws MethodNotSupportedException if call this method.
	 */
	@Override
	public boolean remove(IsWidget w) {
		alwaysThrowsNotSupported();
		return false;
	}
}