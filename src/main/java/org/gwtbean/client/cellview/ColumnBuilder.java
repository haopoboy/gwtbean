package org.gwtbean.client.cellview;

import java.util.Date;

import org.gwtbean.client.BeanObject;

import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextHeader;

/**
 * Quick builder for {@link AbstractCellTable}.
 * 
 * @author HaoPo
 *
 * @param <T>
 * 
 * @see CellType
 * @see ColumnFactory
 */
public class ColumnBuilder<T extends BeanObject> {
	
	private final AbstractCellTable<?> cellTable;

	public ColumnBuilder(AbstractCellTable<?> cellTable) {
		super();
		this.cellTable = cellTable;
	}
	
	/**
	 * 
	 * @param cellType
	 * @param propertyPath
	 * @return
	 */
	public ColumnConfig buildColumn(String cellType, String propertyPath) {
		Column<?, ?> column = ColumnFactory.createColumn(cellType, propertyPath);
		return new ColumnConfig(cellTable, column, propertyPath);
	}
	
	public ColumnConfig buildColumn(CellType cellType, String propertyPath) {
		Column<?, ?> column = ColumnFactory.createColumn(cellType, propertyPath);
		return new ColumnConfig(cellTable, column, propertyPath);
	}
	
	public ColumnConfig buildTextColumn(String propertyPath) {
		Column<T, String> column = ColumnFactory.createTextColumn(propertyPath);
		return new ColumnConfig(cellTable, column, propertyPath);
	}
	
	public ColumnConfig buildEditTextColumn(String propertyPath) {
		Column<T, String> column = ColumnFactory.createEditTextColumn(propertyPath);
		return new ColumnConfig(cellTable, column, propertyPath);
	}
	
	public ColumnConfig buildNumberColumn(String propertyPath) {
		Column<T, Number> column = ColumnFactory.createNumberColumn(propertyPath);
		return new ColumnConfig(cellTable, column, propertyPath);
	}
	
	public ColumnConfig buildDateColumn(String propertyPath) {
		Column<T, Date> column = ColumnFactory.createDateColumn(propertyPath);
		return new ColumnConfig(cellTable, column, propertyPath);
	}
	
	/**
	 * Must call {@link #finish()} after build ready.
	 */
	public static class ColumnConfig {
		
		@SuppressWarnings("rawtypes")
		private final AbstractCellTable cellTable;
		private final Column<?, ?> column;
		private final String propertyPath;
		
		private Header<?> header;
		private Header<?> footer;
		private String width;
		
		public ColumnConfig(AbstractCellTable<?> cellTable, Column<?, ?> column, String propertyPath) {
			super();
			this.cellTable = cellTable;
			this.column = column;
			this.propertyPath = propertyPath;
		}
		
		public ColumnConfig setHeaderString(String headerString) {
			this.header = new TextHeader(headerString);
			return this;
		}
		public ColumnConfig setFooterString(String footerString) {
			this.footer = new TextHeader(footerString);
			return this;
		}
		public String getPropertyPah() {
			return propertyPath;
		}
		public String getWidth() {
			return width;
		}
		public ColumnConfig setWidth(String width) {
			this.width = width;
			return this;
		}
		
		/**
		 * Add column to table and commit settings.
		 */
		@SuppressWarnings("unchecked")
		public void finish() {
			cellTable.addColumn(column, header, footer);
			if (null != width) {
				cellTable.setColumnWidth(column, width);
			}
		}
	}
}
