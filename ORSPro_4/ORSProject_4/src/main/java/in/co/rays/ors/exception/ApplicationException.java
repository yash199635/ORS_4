package in.co.rays.ors.exception;

// TODO: Auto-generated Javadoc
/**
 * ApplicationException is propogated from Service classes when an business
 * logic exception occurered.
 * 
 * @author Abstract Factory
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
/**
 * @author Session Facade
 *
 */
public class ApplicationException extends Exception {

    /**
     * Instantiates a new application exception.
     *
     * @param msg the msg
     */
    public ApplicationException(String msg) {
    	super(msg);
    }
}
