package app.exceptions;

public class DuplicateException extends Exception{

	public DuplicateException() {
		super("Error! Another entity already exists with the value you entered.");
	}

}
