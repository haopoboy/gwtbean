package org.gwtbean.client.cellview;

import org.gwtbean.client.cellview.ui.BeanTable;

import com.google.gwt.user.cellview.client.AbstractCellTable;

/**
 * 
 * @author HaoPo Liao
 * @see BeanTable
 * @see AbstractColumn
 * 
 */
public interface IsColumn<T> {
	
	/**
	 * 
	 * @param cellTable
	 */
	void build(AbstractCellTable<T> cellTable);
}