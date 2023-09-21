package api_imotors.api_imotors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api_imotors.api_imotors.model.Post;
import api_imotors.api_imotors.model.Usuario;
import api_imotors.api_imotors.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UsuarioService usuarioService;

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    public Optional<Post> findById(long id) {
        return this.postRepository.findById(id);
    }

    public Post create(long idUsuario, Post newPost) throws Exception {
        Optional<Usuario> opUsuario = this.usuarioService.findById(idUsuario);

        if (opUsuario.isPresent() == false) {
            throw new Exception("Não encontrei o usuario para adicionar o post");
        }

        Usuario usuario = opUsuario.get();
        usuario.addPost(newPost);
        
        newPost.setUsuario(usuario);
        this.postRepository.save(newPost);

        Post result = usuario.getPosts().get(usuario.getPosts().size() - 1);
        return result;
    }

    public Post update(long id, Post newData) throws Exception {
        Optional<Post> existingItemOptional = postRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new Exception("Não encontrei o post a ser atualizado");

        Post existingItem = existingItemOptional.get();

        existingItem.setData(newData.getData());
        existingItem.setUrlFoto(newData.getUrlFoto());

        postRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws Exception {
        Optional<Post> endereco = this.postRepository.findById(id);

        if (endereco.isPresent() == false)
            throw new Exception("Não encontrei o endereco a ser atualizado");

        this.postRepository.delete(endereco.get());
    }

    public void saveComentario(Post post) {
        this.postRepository.save(post);
    }

}
