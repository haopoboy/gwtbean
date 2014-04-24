package org.gwtbean.shared;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AssertionsUnitTest {

	@Test(expected = NullPointerException.class)
	public void notNull() {
		Assertions.notNull(null);
	}
	
	@Test
	public void notNullWithMessageFormat() {
		try {
			Assertions.notNull(null, "I'm %s", "Assertions");
		} catch(NullPointerException exception) {
			assertThat( exception.getMessage(), is("I'm Assertions") );
		}
	}
}