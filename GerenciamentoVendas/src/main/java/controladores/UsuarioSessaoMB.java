package controladores;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import arquitetura.SessionContext;
import entidades.Login;
import servicos.LoginService;
import util.MensagensUtil;

@ManagedBean(name = "usuarioSessaoMB")
@SessionScoped
public class UsuarioSessaoMB {

	@EJB
	LoginService loginService;

	private String usuario;
	private String senha;

	private Login login;

	/**
	 * Retorna usuario logado
	 */
	public Login getUser() {
		return (Login) SessionContext.getInstance().getUsuarioLogado();
	}

	public void doLogin() {

		try {
			System.out.println("Tentando logar com usuário " + usuario);
			login = loginService.buscarLogin(usuario, senha);

			if (login == null) {
				MensagensUtil.adicionaMensagemErro("Login ou Senha errado, tente novamente !");
				FacesContext.getCurrentInstance().validationFailed();

			}

			System.out.println("Login efetuado com sucesso");
			SessionContext.getInstance().setAttribute("usuarioLogado", login);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsp");

		} catch (Exception e) {
			MensagensUtil.adicionaMensagemErro(e.getMessage());
		}

	}

	public String doLogout() {
		System.out
				.println("Fazendo logout com usuário " + SessionContext.getInstance().getUsuarioLogado().getUsuario());
		SessionContext.getInstance().encerrarSessao();
		MensagensUtil.adicionaMensagemSucesso("Logout realizado com sucesso !");
		return "index.jsp?faces-redirect=true";
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
