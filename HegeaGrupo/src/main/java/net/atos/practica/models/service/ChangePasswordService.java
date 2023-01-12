package net.atos.practica.models.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.atos.practica.entity.UsuarioEntity;
import net.atos.practica.repository.IUsuarioRepository;

@Service
public class ChangePasswordService implements IChangePasswordService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public void updatePrimerAcceso(String username, String password) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity = usuarioRepository.findByUsername(username);

		usuarioEntity.setPassword(password);
		usuarioEntity.setChangepassword(true);

		usuarioRepository.save(usuarioEntity);

	}
}
