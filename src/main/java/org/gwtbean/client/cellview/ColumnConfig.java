package org.gwtbean.client.cellview;

import org.gwtbean.client.BeanObject;

import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextHeader;

/**
 * Must call {@link #finish()} after build ready.
 * 
 * @param <T> type of {@link BeanObject} 
 * @author HaoPo Liao
 * 
 */
public class ColumnConfig<T> {
	private final AbstractCellTable<T> cellTable;
	private final Column<T, ?> column;
	private final String propertyPath;
	
	private Header<?> header;
	private Header<?> footer;
	private String width;
	
	public ColumnConfig(AbstractCellTable<T> cellTable, Column<T, ?> column, String propertyPath) {
		super();
		this.cellTable = cellTable;
		this.column = column;
		this.propertyPath = propertyPath;
	}
	
	public ColumnConfig<T> setHeaderString(String headerString) {
		this.header = new TextHeader(headerString);
		return this;
	}
	public ColumnConfig<T> setFooterString(String footerString) {
		this.footer = new TextHeader(footerString);
		return this;
	}
	public String getPropertyPah() {
		return propertyPath;
	}
	public String getWidth() {
		return width;
	}
	public ColumnConfig<T> setWidth(String width) {
		this.width = width;
		return this;
	}
	
	/**
	 * Add column to table and commit settings.
	 */
	public void finish() {
		cellTable.addColumn(column, header, footer);
		if (null != width) {
			cellTable.setColumnWidth(column, width);
		}
	}
}
