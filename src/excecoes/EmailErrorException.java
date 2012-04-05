package excecoes;

public class EmailErrorException extends Exception {
	public EmailErrorException(String message) {
		super(message);
	}
}