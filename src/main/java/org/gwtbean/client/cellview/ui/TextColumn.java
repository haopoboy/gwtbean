package org.gwtbean.client.cellview.ui;

import org.gwtbean.client.BeanObject;
import org.gwtbean.client.cellview.AbstractColumn;
import org.gwtbean.client.cellview.CellType;
import org.gwtbean.client.cellview.ColumnFactory;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.cellview.client.Column;

/**
 * 
 * @author HaoPo Liao
 *
 * @param <T>
 */
public class TextColumn<T extends BeanObject> extends AbstractColumn<T> {

	@UiConstructor
	public TextColumn(String propertyPath) {
		super(propertyPath);
	}

	@Override
	protected Column<T, ?> createColumn() {
		return ColumnFactory.createColumn( CellType.TEXT, getPropertyPath() );
	}
}