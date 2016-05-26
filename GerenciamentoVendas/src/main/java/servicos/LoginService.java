package servicos;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

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

	public void cadastrarUsuario(Login login) throws Exception {
		String senhaMD5;

		senhaMD5 = convertStringToMd5(login.getSenha());
		login.setSenha(senhaMD5);

		loginDao.gravar(login);

	}

	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			return sb.toString();

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
