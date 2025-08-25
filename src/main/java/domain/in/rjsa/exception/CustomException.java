package domain.in.rjsa.exception;

public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7075492858161405687L;
	
	public CustomException() {
		super();
	}

	public CustomException(String msg) {
		super(msg);
	}

}
