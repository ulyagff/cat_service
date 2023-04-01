package Exceptons;

public class OwnerException extends RuntimeException{
    private OwnerException(String message) {
        super(message);
    }

    public static OwnerException OwnerHasCat()
    {
        return new OwnerException("the owner has the cat");
    }
}
