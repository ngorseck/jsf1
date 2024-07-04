package com.samanecorp.jsf1.controller;
import java.io.Serializable;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import com.samanecorp.jsf1.dto.UserDto;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named("loginbean")
@SessionScoped
public class LoginBean implements Serializable {

	
	private UserDto user;
	private String user_params;
	private String error_message;
	
	public LoginBean() {
		user = new UserDto("test JSF", "", "");
	}

	public String getLogin() {
		String url = null;
		try {
			//User u = userdao.getConnection(user.getEmail(), user.getPassword());
			if(user != null) {
				//Demarrage de la session
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(true);
				session.setAttribute("user_session", user);
				url = "/accueil.jsf";
				user_params = user.getFullName();
				error_message = null;
			} else {
				error_message = "Email ou Mot de passe incorrect";
				url = "/index.jsf";
			}
			//Redirection
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;		
	}
	
	public String logOut() {
		try {
			//Destruction de la session
		    FacesContext facesContext = FacesContext.getCurrentInstance();
		    HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(false);
		    session.invalidate();
		    
		    //Gestion de la redirection
		    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    externalContext.redirect(externalContext.getRequestContextPath() + "/index.jsf");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index.jsf";
	}
	
	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getUser_params() {
		return user_params;
	}

	public void setUser_params(String user_params) {
		this.user_params = user_params;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	
}
	