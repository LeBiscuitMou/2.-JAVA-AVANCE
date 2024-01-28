package keepfit.model.dao;

@SuppressWarnings("serial")
public class DaoException extends Exception {
	
	public DaoException() {		
	}
	
	public DaoException(String msg) {
		super(msg);
	}

	public DaoException(Throwable cause) {
		super(cause);		
	}


}
