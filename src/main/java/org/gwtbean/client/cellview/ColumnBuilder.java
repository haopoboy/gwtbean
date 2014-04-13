package org.gwtbean.client.cellview;

import java.util.Date;

import org.gwtbean.client.BeanObject;

import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.user.cellview.client.Column;

/**
 * Convenience builder for {@link AbstractCellTable}.<br/>
 * 
 * @param <T> type of {@link BeanObject}
 * @author HaoPo Liao
 * @see CellType
 * @see ColumnFactory
 * 
 */
public class ColumnBuilder<T extends BeanObject> {
	
	private final AbstractCellTable<T> cellTable;

	public ColumnBuilder(AbstractCellTable<T> cellTable) {
		super();
		this.cellTable = cellTable;
	}
	
	/**
	 * 
	 * @param column
	 * @param propertyPath
	 * @return
	 */
	public ColumnConfig<T> buildColumn(Column<T, ?> column, String propertyPath) {
		return new ColumnConfig<T>(cellTable, column, propertyPath);
	}
	
	/**
	 * 
	 * @param cellType
	 * @param propertyPath
	 * @return
	 */
	public ColumnConfig<T> buildColumn(String cellType, String propertyPath) {
		Column<T, ?> column = ColumnFactory.createColumn(cellType, propertyPath);
		return new ColumnConfig<T>(cellTable, column, propertyPath);
	}
	
	/**
	 * 
	 * @param cellType
	 * @param propertyPath
	 * @return
	 */
	public ColumnConfig<T> buildColumn(CellType cellType, String propertyPath) {
		Column<T, ?> column = ColumnFactory.createColumn(cellType, propertyPath);
		return new ColumnConfig<T>(cellTable, column, propertyPath);
	}
	
	/**
	 * 
	 * @param propertyPath
	 * @return
	 */
	public ColumnConfig<T> buildTextColumn(String propertyPath) {
		Column<T, String> column = ColumnFactory.createTextColumn(propertyPath);
		return new ColumnConfig<T>(cellTable, column, propertyPath);
	}
	
	/**
	 * 
	 * @param propertyPath
	 * @return
	 */
	public ColumnConfig<T> buildEditTextColumn(String propertyPath) {
		Column<T, String> column = ColumnFactory.createEditTextColumn(propertyPath);
		return new ColumnConfig<T>(cellTable, column, propertyPath);
	}
	
	/**
	 * 
	 * @param propertyPath
	 * @return
	 */
	public ColumnConfig<T> buildNumberColumn(String propertyPath) {
		Column<T, Number> column = ColumnFactory.createNumberColumn(propertyPath);
		return new ColumnConfig<T>(cellTable, column, propertyPath);
	}
	
	/**
	 * 
	 * @param propertyPath
	 * @return
	 */
	public ColumnConfig<T> buildDateColumn(String propertyPath) {
		Column<T, Date> column = ColumnFactory.createDateColumn(propertyPath);
		return new ColumnConfig<T>(cellTable, column, propertyPath);
	}
}
