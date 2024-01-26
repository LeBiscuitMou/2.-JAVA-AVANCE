package garage.model.exceptions;

@SuppressWarnings("serial")
public class BusinessException extends Exception {
	public BusinessException(String msg) {
		super(msg);
	}
}
