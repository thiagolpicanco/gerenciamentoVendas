package controladores;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.security.auth.spi.Users.User;
import org.primefaces.context.RequestContext;

import entidades.Login;
import persistence.LoginDao;

@ManagedBean
@SessionScoped
public class PrincipalMB {

	private Login login;
	private Login loginAtivo;
	private String originalURL;

	@EJB
	private LoginDao userService;

	@PostConstruct
	public void init() {
		RequestContext requestContext = RequestContext.getCurrentInstance().getCurrentInstance();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

		if (login == null) {
			login = new Login();
		}
		if (null == loginAtivo) {
			requestContext.execute("PF('dlg').show();");
		}

		if (originalURL == null) {
			originalURL = externalContext.getRequestContextPath() + "/home.xhtml";
		} else {
			String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

			if (originalQuery != null) {
				originalURL += "?" + originalQuery;
			}
		}
	}

	public void login() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		try {
			loginAtivo = userService.buscaLogin(login);
			externalContext.getSessionMap().put("user", loginAtivo);
			externalContext.redirect(originalURL);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Usuario Invalido"));
		}
	}

	public void logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.invalidateSession();
		externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
	}

	public Login getLoginAtivo() {
		return loginAtivo;
	}

	public void setLoginAtivo(Login loginAtivo) {
		this.loginAtivo = loginAtivo;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	public LoginDao getUserService() {
		return userService;
	}

	public void setUserService(LoginDao userService) {
		this.userService = userService;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
