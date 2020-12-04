package network.Exceptions;

public class InvalidPositionException extends RuntimeException {

    static final long serialVersionUID = 0L;
    
    private static final String DEFAULT_MSG = "Invalid position.";

    public InvalidPositionException( )
    {
        super(DEFAULT_MSG);
    }

}
