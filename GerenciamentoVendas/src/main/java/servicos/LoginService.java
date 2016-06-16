package servicos;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import entidades.Login;
import persistence.LoginDao;

@Stateless
public class LoginService {

	@EJB
	LoginDao loginDao;

	public Login buscarLogin(String usuario, String senha) {
		String senhaMD5;

		senhaMD5 = convertStringToMd5(senha);
		try {
			return loginDao.buscaLogin(usuario, senhaMD5);

		} catch (Exception e) {
			return null;
		}
	}

	public List<Login> listarLogins() {
		return loginDao.listarTudo();
	}

	public void cadastrarUsuario(Login login) throws Exception {
		String senhaMD5;
		senhaMD5 = convertStringToMd5(login.getSenha());
		login.setSenha(senhaMD5);
		loginDao.gravar(login);

	}

	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(valor.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
