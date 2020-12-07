package in.co.rays.ors.util;

import java.math.MathContext;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * This class validates input data.
 *
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

/**
 *@author Session Facade
 * @version 1.0
 * Copyright (c) SunilOS
 */
public class DataValidator {

    /**
     * Checks if value is Null.
     *
     * @param val the val
     * @return true, if is null
     */
    public static boolean isNull(String val) {
        if (val == null || val.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if value is NOT Null.
     *
     * @param val the val
     * @return true, if is not null
     */
    public static boolean isNotNull(String val) {
        return !isNull(val);
    }

    /**
     * Checks if value is an Integer.
     *
     * @param val the val
     * @return true, if is integer
     */

    public static boolean isInteger(String val) {

        if (isNotNull(val)) {
     //   	System.out.println("datavalidate..DataValidator$"+val+"$");
            try {
            	
                int i = Integer.parseInt(val);
       //         System.out.println("data validate ......"+i);
                return true;
            } catch (NumberFormatException e) {
            	e.printStackTrace();
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is Long.
     *
     * @param val the val
     * @return true, if is long
     */
    public static boolean isLong(String val) {
        if (isNotNull(val)) {
            try {
                long i = Long.parseLong(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is valid Email ID.
     *
     * @param val the val
     * @return true, if is email
     */
    public static boolean isEmail(String val) {

        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (isNotNull(val)) {
            try {
                return val.matches(emailreg);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is Date.
     *
     * @param val the val
     * @return true, if is date
     */
    public static boolean isDate(String val) {

        Date d = null;
        if (isNotNull(val)) {
            d = DataUtility.getDate(val);
        }
        return d != null;
    }

    /**
     * Checks if value is Mobile Number.
     *
     * @param val the val
     * @return true, if is mobile no
     */
 
    public static boolean isMobileNo(String val){
    	
    	String mobreg = "^[6-9][0-9]{9}$";
    	
    			if (isNotNull(val) && val.matches(mobreg)) {
					
						return true;
    				}else
    				{	
    					return false;
					}	
    		}

    
    
    /**
     * checks if value is Name.
     *
     * @param val the val
     * @return true, if is name
     */
    public static boolean isName(String val){
    	
    //	String namereg = "^[a-zA-Z]+$";
   // 	String namereg = "^[a-zA-Z_-]+$";
   	String namereg = "^[^-\\s][\\p{L} .']+$";
    	

    			if (DataValidator.isNotNull(val)) {
    				boolean check = val.matches(namereg);
    
						return check;
    				}else
    				{	
    					return false;
					}	
    		}
   
    /**
     * check if value is Valid Name.
     *
     * @param val the val
     * @return true, if is valid name
     */
    public static boolean isValidName(String val){
    	 	
        	String namereg = "^[a-zA-Z_-]+$";
       
        	//	String namereg = "^[^-\\s][\\p{L} .']+$";
        	
        			if (DataValidator.isNotNull(val)) {
        				boolean check = val.matches(namereg);
        	
    						return check;
        				}else
        				{	
        					return false;
    					}	
        		}
    
    
    /**
     * check if value is password.
     *
     * @param val the val
     * @return true, if is password
     */
    public static boolean isPassword(String val){
    	
    	String pass = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15})";
    	
    			if (DataValidator.isNotNull(pass)) {
					boolean check =  val.matches(pass);
						return check;
    				}else
    				{	
    					return false;
					}	
    		}
    
    /**
     * check if value is Roll no.
     *
     * @param val the val
     * @return true, if is roll no
     */
    public static boolean isRollNo(String val){
    	
    	String roll = "^[0-9]{4}[A-Z]{2}[0-9]{2,6}$";
    
    			if (DataValidator.isNotNull(roll)) {
					boolean check = val.matches(roll);
                    		return check;
    				}else
    				{	
    					return false;
					}	
    		}
    
    /**
     * check if value is validate age.
     *
     * @param val the val
     * @return true, if is validate age
     */
    public static boolean isvalidateAge(String val){
    
    	Date today = new Date();
    	Date enterDate = DataUtility.getDate(val);
    	
    	int age = today.getYear() - enterDate.getYear();

    	if(age > 18 && isNotNull(val)){
    		return true;
    	}else{
    		return false;							
    	}
    }
    
    /**
     * Test above methods.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        /*System.out.println("Not Null 2" + isNotNull("ABC"));
        System.out.println("Not Null 3" + isNotNull(null));*/
       // System.out.println("Not Name" + isName("pankaj"));
      //  String s = "a hajk     a mdlvkj   lkjlkgkl.    /lkjlkhklvjlk";
        String a = "A           di    ty       a";
        String b = "Sourabhg91@"; 
        System.out.println(isPassword(b));
        
        /*System.out.println("Is Int " + isInteger(null));
        System.out.println("Is Int " + isInteger("ABC1"));
        System.out.println("Is Int " + isInteger("123"));
        System.out.println("Is Int " + isNotNull("123"));
    }*/
    }
}