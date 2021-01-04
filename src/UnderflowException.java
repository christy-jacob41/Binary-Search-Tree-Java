/**
* Exception class for access in empty containers
* such as stacks, queues, and priority queues.
*
* @author Mark Allen Weiss
* @author Albert J. Wong
*
* Modified by Albert J. Wong October 2003
*  - Made this into a runtime exception.  It shouldn't be
*    a non-runtime exception because...well, it's a condition
*    that is detectable before triggering the exception, so there 
*    should be no reason to ever have this exception thrown except
*    in the case of a program error.  Thus, it shouldn't be checked.
*  - Added a construction with a "string" argument, cause often, it's
*    nice to be able to put cute messages in with your exceptions :)
*/
public class UnderflowException extends RuntimeException {
	public UnderflowException() 
	{}

	public UnderflowException(String message) {
		super(message);
	}
}