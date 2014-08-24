package org.gwtbean.shared;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AssertionsUnitTest {

	@Test(expected = NullPointerException.class)
	public void notNull() {
		Assertions.notNull(null);
	}
	
	@Test
	public void notNullWithMessage() {
		try {
			Assertions.notNull(null, "I'm not null assertion");
		} catch(NullPointerException exception) {
			assertThat( exception.getMessage() ).isEqualTo("I'm not null assertion");
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void notNullNorEmptyWhenNull() {
		Assertions.notNullNorEmpty(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void notNullNorEmptyWhenEmpty() {
		Assertions.notNullNorEmpty("");
	}
	
	@Test
	public void notNullNorEmptyWithMessage() {
		try {
			Assertions.notNull(null, "I'm not null nor empty assertion");
		} catch(NullPointerException exception) {
			assertThat( exception.getMessage() ).isEqualTo("I'm not null nor empty assertion");
		}
	}
	
}