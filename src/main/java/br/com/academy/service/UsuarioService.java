package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.Exceptions.CriptoExistsException;
import br.com.academy.Exceptions.EmailExistsException;
import br.com.academy.Exceptions.ServiceExc;
import br.com.academy.model.Usuario;
import br.com.academy.repositorios.UsuarioRepositorio;
import br.com.academy.util.Util;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	
	
	public void salvarusuario(Usuario user) throws Exception {
		try {
			if(usuarioRepositorio.findByEmail(user.getEmail()) != null) {
				throw new EmailExistsException("Já existe um email cadastrado para: "+user.getEmail());
			}
			
			user.setSenha(Util.md5(user.getSenha()));
			
		}catch (NoSuchAlgorithmException e) {
			throw new CriptoExistsException("Erro na Criptogafia da senha");
			
		}finally {
		usuarioRepositorio.save(user);
		}
	}

	public void salvarUsuario(Usuario user) throws Exception {
		try {
			if(usuarioRepositorio.findByEmail(user.getEmail()) != null) {
				throw new EmailExistsException("Já existe um email cadastrado para: "+user.getEmail());
			}
			
			user.setSenha(Util.BCrypt(user.getSenha()));
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
		usuarioRepositorio.save(user);
		}
	}
	public Usuario loginUser(String user, String senha) throws ServiceExc{
		Usuario userLogin = usuarioRepositorio.buscarLogin(user, senha);
		return userLogin;
	}
}
