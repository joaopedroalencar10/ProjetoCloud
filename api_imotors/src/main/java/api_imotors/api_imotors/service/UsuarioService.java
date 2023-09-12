package api_imotors.api_imotors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api_imotors.api_imotors.model.Usuario;
import api_imotors.api_imotors.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository _usuarioRepository;

    public List<Usuario> findAll() {
        return this._usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(long id) {
        return this._usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) throws Exception {
        if (this._usuarioRepository.countByUsername(usuario.getUsername()) > 0) {
            throw new Exception("Este Username já existe na base de dados");
        }
        this._usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario login(String username, String senha) throws Exception {

        Usuario result = this._usuarioRepository.findByUsernameAndSenha(username, senha);

        if (result == null) {
            throw new Exception("Usuário ou senha inválidos");
        }
        
        return result;
    }

    public Usuario update(long id, Usuario newData) throws Exception {
        Optional<Usuario> result = this._usuarioRepository.findById(id);

        if (result.isPresent() == false) {
            throw new Exception("Não encontrei a usuario a ser atualizada");
        }

        Usuario usuarioASerAtualizada = result.get();
        usuarioASerAtualizada.setNome(newData.getNome());
        usuarioASerAtualizada.setUsername(newData.getUsername());
        usuarioASerAtualizada.setFotoPerfil(newData.getFotoPerfil());
        this._usuarioRepository.save(usuarioASerAtualizada);
        return usuarioASerAtualizada;
    }

    public void delete(long id) throws Exception {
        Optional<Usuario> usuarioASerExcluida = this._usuarioRepository.findById(id);
        // Não achei a usuario a ser excluida
        if (usuarioASerExcluida.isPresent() == false) {
            throw new Exception("Não encontrei a usuario a ser atualizado");
        }
        this._usuarioRepository.delete(usuarioASerExcluida.get());
    }

    public void savePost(Usuario usuario) {
        this._usuarioRepository.save(usuario);
    }

}
