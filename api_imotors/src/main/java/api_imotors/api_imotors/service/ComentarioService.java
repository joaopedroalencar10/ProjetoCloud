package api_imotors.api_imotors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api_imotors.api_imotors.exception.CommentException;
import api_imotors.api_imotors.model.Comentario;
import api_imotors.api_imotors.model.Post;
import api_imotors.api_imotors.model.Usuario;
import api_imotors.api_imotors.repository.ComentarioRepository;

@Service
public class ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    PostService postService;

    public List<Comentario> findAll() {
        return this.comentarioRepository.findAll();
    }

    public Optional<Comentario> findById(long id) {
        return this.comentarioRepository.findById(id);
    }

    public Comentario create(long idPost, Comentario newComentario) throws CommentException {
        Optional<Post> opPost = this.postService.findById(idPost);

        if (opPost.isPresent() == false) {
            throw new CommentException ("Não encontrei o post para adicionar o comentário");
        }

        Post post = opPost.get();
        post.addComentario(newComentario);
        
        newComentario.setPost(post);
        this.comentarioRepository.save(newComentario);

        Comentario result = post.getCometarios().get(post.getCometarios().size() - 1);
        return result;
    }

    public Comentario update(long id, Comentario newData)  throws CommentException {
        Optional<Comentario> existingItemOptional = comentarioRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new CommentException ("Não encontrei o comentario a ser atualizado");

        Comentario existingItem = existingItemOptional.get();

        existingItem.setData(newData.getData());
        existingItem.setTexto(newData.getTexto());

        comentarioRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws CommentException {
        Optional<Comentario> comentario = this.comentarioRepository.findById(id);

        if (comentario.isPresent() == false)
            throw new  CommentException("Não encontrei o comentario a ser atualizado");

        this.comentarioRepository.delete(comentario.get());
    }
    
    
    public Comentario save(long idPost, Comentario item) throws CommentException {
        Optional<Post> opPost = this.postService.findById(idPost);

        if (opPost.isPresent() == false) {
            throw new CommentException("Post não encontrado");
        }

        Post post = opPost.get();
        item.setPost(post);
        this.comentarioRepository.save(item);
       
        return item;
    }

}
