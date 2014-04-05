package org.gwtbean.client.cellview;


import java.util.Date;

import org.gwtbean.client.BeanObject;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;

/**
 * Simple factory to create {@link Cell} and {@link Column} widget. 
 * 
 * @author HaoPo
 *
 */
public class ColumnFactory {
	
	/**
	 * A quick method to create column by {@link CellType}.
	 * 
	 * @param cellType
	 * @param propertyPath
	 * @return
	 * @see CellType
	 */
	public static Column<?, ?> createColumn(String cellType, String propertyPath) {
		return createColumn( CellType.valueOf(cellType), propertyPath );
	}
	
	/**
	 * A quick method to create column by {@link CellType}.
	 * 
	 * @param cellType
	 * @param propertyPath
	 * @return
	 * @see CellType
	 */
	public static Column<?, ?> createColumn(CellType cellType, String propertyPath) {
		
		// Cast to write generic type to avoid compiler fails.
		switch (cellType) {
		case EDIT_TEXT:
			return (Column<? extends BeanObject, String>) createEditTextColumn(propertyPath);
		case NUMBER:
			return (Column<? extends BeanObject, Number>) createNumberColumn(propertyPath);
		case DATE:
			return (Column<? extends BeanObject, Date>) createDateColumn(propertyPath);
		case TEXT:
		default:
			return (Column<? extends BeanObject, String>) createTextColumn(propertyPath);
		}
	}
	
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
		
		FieldUpdater<T, String> updater = createFieldUpdater(propertyPath);
		column.setFieldUpdater(updater);
		
		return column;
	}
	
	/**
	 * Create {@link NumberCell} in Column with property path.
	 * 
	 * @param propertyPath
	 * @return
	 */
	public static <T extends BeanObject> Column<T, Number> createNumberColumn(final String propertyPath) {
		Column<T, Number> column = new Column<T, Number>( new NumberCell() ) {
			
			@Override
			public Number getValue(T object) {
				return Double.valueOf( object.getStringValue(propertyPath, "0") );
			}
		};
		
		return column;
	}
	
	/**
	 * Create {@link DateCell} in Column with property path.
	 * 
	 * @param propertyPath
	 * @return
	 */
	public static <T extends BeanObject> Column<T, Date> createDateColumn(final String propertyPath) {
		Column<T, Date> column = new Column<T, Date>( new DateCell() ) {
			
			@Override
			public Date getValue(T object) {
				Long date = Long.valueOf( object.getStringValue(propertyPath, "0") );
				if (date == 0) {
					return null;
				} else {
					return new Date(date);
				}
			}
		};
		
		return column;
	}

	/**
	 * Create updater through {@link BeanObject#setPropertyValue(String, Object)}.
	 * 
	 * @param propertyPath
	 * @return
	 */
	public static <T extends BeanObject, V>  FieldUpdater<T, V> createFieldUpdater(final String propertyPath) {
		return new FieldUpdater<T, V>() {
	
			@Override
			public void update(int index, T object, V value) {
				object.setPropertyValue(propertyPath, value);
			}
		};
	}
}
