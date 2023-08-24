package api_imotors.api_imotors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api_imotors.api_imotors.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
