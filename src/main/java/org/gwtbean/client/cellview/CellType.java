package org.gwtbean.client.cellview;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;

/**
 * Cell type for column.
 * 
 * @author HaoPo
 *
 */
public enum CellType {

	/**
	 * Type of {@link TextCell}.
	 */
	TEXT, 
	
	/**
	 * Type of {@link EditTextCell}.
	 */
	EDIT_TEXT, 
	
	/**
	 * Type of {@link NumberCell}.
	 */
	NUMBER, 
	
	/**
	 * Type of {@link DateCell}.
	 */
	DATE
}
