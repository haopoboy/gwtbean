package org.gwtbean.client;

import org.gwtbean.client.cellview.ColumnFactoryGwtTest;
import org.gwtbean.client.cellview.PropertyAccesstorGwtTest;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

/**
 * 
 * 
 * @author HaoPo Liao
 *
 */
public class GwtBeanTestSuite extends GWTTestSuite {

	/**
	 * Suite for this package.
	 * 
	 * @return
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Core Test Suite");
		suite.addTestSuite( PropertyAccesstorGwtTest.class );
		suite.addTestSuite( BeanObjectGwtTest.class );
		suite.addTestSuite( ColumnFactoryGwtTest.class );
		return suite;
	}
}
