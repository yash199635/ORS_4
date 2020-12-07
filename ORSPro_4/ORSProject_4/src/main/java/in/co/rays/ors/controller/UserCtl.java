package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ors.bean.BaseBean;
import in.co.rays.ors.bean.UserBean;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.exception.RecordNotFoundException;
import in.co.rays.ors.model.RoleModel;
import in.co.rays.ors.model.UserModel;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class UserCtl.
 * @author Session Facade
 * @version 1.0
 * Copyright (c) SunilOS
 * 
 */
@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl" })
public class UserCtl extends BaseCtl {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = Logger.getLogger(UserCtl.class);

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		RoleModel model = new RoleModel();
		try {
			List l = model.list();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserCtl Method validate Started");

		boolean pass = true;

	
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		else if (!DataValidator.isValidName(request.getParameter("firstName"))) {
      	  request.setAttribute("firstName",PropertyReader.getValue("error.name", "Invalid First"));
            pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}
		else if (!DataValidator.isValidName(request.getParameter("lastName"))) {
      	  request.setAttribute("lastName",PropertyReader.getValue("error.name", "Invalid Last"));
            pass = false;
		}
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (DataValidator.isNotNull(request.getParameter("login"))&& !DataValidator.isEmail(request.getParameter("login"))) {
            request.setAttribute("login", PropertyReader.getValue("error.email", "Invalid "));
            pass = false;
        }
		
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;
		}
		else if (!DataValidator.isMobileNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			pass = false;
		}
		
		 long id = DataUtility.getLong(request.getParameter("id"));
	        if (id > 0) {
	        	
	        	
	        }
	        else{
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}else if (!DataValidator.isPassword(request.getParameter("password"))) {
        	request.setAttribute("password", "Password contain 8 letters with alpha-numeric,capital latter & special Character");
        	pass = false;
        }
          
		
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}else if (!DataValidator.isPassword(request.getParameter("password"))) {
        	request.setAttribute("password", "Password contain 8 letters with alpha-numeric & special Character");
        	pass = false;
        }
		else if(!request.getParameter("password").equals(request.getParameter("confirmPassword"))){
			request.setAttribute("confirmPassword", "New password and Confirm password must be same!!");
			pass = false;
			}
			
	        }
	        
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("roleId"))) {
			request.setAttribute("roleId", PropertyReader.getValue("error.require", "RoleName"));
			pass = false;
		}
		
		if (DataValidator.isNull("dob")) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} /*else if (!DataValidator.isDate("dob")) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		}*/
		
		/*if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			ServletUtility.setErrorMessage("Confirm  Password  should be matched.", request);
			pass = false;
		}
*/
		/*else if(!request.getParameter("newPassword").equals(request.getParameter("confirmPassword"))){
			request.setAttribute("confirmPassword", "New password and Confirm password must be same!!");
			pass = false;
			}*/
		
		log.debug("UserCtl Method validate Ended");

		return pass;

	}

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
	 */
	
	protected BaseBean populateBean(HttpServletRequest request) {
		  log.debug("UserCtl Method populatebean Started");

	        UserBean bean = new UserBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));
	      

	        bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
	        

	        bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
	       
	       
	        bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
	        
	       
	        bean.setLogin(DataUtility.getString(request.getParameter("login")));
	        
	     
	        bean.setPassword(DataUtility.getString(request.getParameter("password")));
	        
	        bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
  
	       
	        bean.setGender(DataUtility.getString(request.getParameter("gender")));
	        
	        
	        bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
	        
	       
	        bean.setDob(DataUtility.getDate(request.getParameter("dob")));
	        
	       
	        populateDTO(bean, request);

	        log.debug("UserCtl Method populatebean Ended");

	        return bean;

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  log.debug("UserCtl Method doGet Started");
	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get model
	        UserModel model = new UserModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            UserBean bean;
	            try {
	                bean = model.findByPK(id);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }

	        ServletUtility.forward(getView(), request, response);
	        log.debug("UserCtl Method doGet Ended");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UserCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
         
		//System.out.println(">>>><<<<>><<><<><<><>**********" +request.getParameter("id"));
		
	        UserModel model = new UserModel();
	       if (OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op)) {
	            UserBean bean = (UserBean) populateBean(request);

	            try {
	                if (id > 0) {
	     
	                	//System.out.println("hi i am in dopost update");
	                	model.update(bean);
	                    ServletUtility.setBean(bean, request);

	                    ServletUtility.setSuccessMessage("User is successfully Updated", request);
		                       
	                } else {
	                	//System.out.println(">>>>><<<<<)))(((()(()(())");
	                    long pk = model.add(bean);
	                  //  bean.setId(pk);
	                   // ServletUtility.setBean(bean, request);

	                    ServletUtility.setSuccessMessage("User is successfully Added", request);
	                    ServletUtility.forward(getView(), request, response);
	                    bean.setId(pk);
	                }
	               /* ServletUtility.setBean(bean, request);
	                ServletUtility.setSuccessMessage("User is successfully saved",
	                        request);*/
	                
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Login id already exists",
	                        request);
	            } catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else if (OP_DELETE.equalsIgnoreCase(op)) {

	            UserBean bean = (UserBean) populateBean(request);
	            try {
	                model.delete(bean);
	                ServletUtility.redirect(ORSView.USER_CTL, request,
	                        response);
	                return;
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.USER_CTL, request, response);
	            return;
	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("UserCtl Method doPostEnded");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}

}
