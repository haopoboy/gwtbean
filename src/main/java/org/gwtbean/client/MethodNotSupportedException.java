package org.gwtbean.client;


/**
 * Throws when method are not supported.
 * 
 * @author HaoPo
 *
 */
public class MethodNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = -5390010948818337778L;

	public MethodNotSupportedException() {
		super();
	}

	public MethodNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodNotSupportedException(String message) {
		super(message);
	}

	public MethodNotSupportedException(Throwable cause) {
		super(cause);
	}
}