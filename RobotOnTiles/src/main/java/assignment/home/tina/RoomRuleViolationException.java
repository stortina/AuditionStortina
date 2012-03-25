package assignment.home.tina;

public class RoomRuleViolationException extends Exception {
	
    public RoomRuleViolationException() {
    	super();
        }

        /**
         * Constructs an {@code IOException} with the specified detail message.
         *
         * @param message
         *        The detail message (which is saved for later retrieval
         *        by the {@link #getMessage()} method)
         */
        public RoomRuleViolationException(String message) {
        	super(message);
        }

}
