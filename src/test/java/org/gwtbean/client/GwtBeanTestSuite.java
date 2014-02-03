package org.gwtbean.client;

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
		suite.addTestSuite( BeanObjectGwtTest.class );
		return suite;
	}
}
