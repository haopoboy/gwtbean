package org.gwtbean.client.cellview;


import org.gwtbean.client.BeanObject;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;

/**
 * Simple factory to create {@link Cell} and {@link Column} widget. 
 * 
 * @author HaoPo
 *
 */
public class ColumnFactory {

	
	/**
	 * Create {@link TextCell} in Column with property path.
	 * 
	 * @param propertyPath
	 * @return
	 */
	public static <T extends BeanObject> Column<T, String> createTextColumn(final String propertyPath) {
		Column<T, String> column = new Column<T, String>( new TextCell() ) {
			
			@Override
			public String getValue(T object) {
				return object.getStringValue(propertyPath, "");
			}
		};
		return column;
	}
	
	/**
	 * Create {@link EditTextCell} in Column with property path.
	 * 
	 * @param propertyPath
	 * @return
	 */
	public static <T extends BeanObject> Column<T, String> createEditTextColumn(final String propertyPath) {
		Column<T, String> column = new Column<T, String>( new EditTextCell() ) {
			
			@Override
			public String getValue(T object) {
				return object.getStringValue(propertyPath, "");
			}
		};
		
		column.setFieldUpdater( new FieldUpdater<T, String>() {

			@Override
			public void update(int index, T object, String value) {
				object.setPropertyValue(propertyPath, value);
			}
		});
		
		return column;
	}
}
