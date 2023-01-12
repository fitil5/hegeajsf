package net.atos.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.atos.practica.entity.UsuarioEntity;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	public UsuarioEntity findByUsername(String username);

	public void delete(UsuarioEntity entity);

}
