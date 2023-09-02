package api_imotors.api_imotors.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api_imotors.api_imotors.repository.PostRepository;
import api_imotors.api_imotors.repository.UsuarioRepository;
import api_imotors.api_imotors.model.Post;
import api_imotors.api_imotors.model.Usuario;


@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository _postRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        try {
            return new ResponseEntity<>(this._postRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getById(@PathVariable("id") Long id) {
        Optional<Post> existingItemOptional = _postRepository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("{idUsuario}")
    public ResponseEntity<Post> create(@PathVariable("idUsuario") long idUsuario, @RequestBody Post post) {
        try {

            Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

            if (usuario.isPresent() == false)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            usuario.get().addPost(post);
            post.setUsuario(usuario.get());
            this._postRepository.save(post);

            return new ResponseEntity<>(post, HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> update(@PathVariable("id") Long id, @RequestBody Post post) {

        Optional<Post> existingItemOptional = _postRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Post existingItem = existingItemOptional.get();
        
        existingItem.setData(post.getData());
        existingItem.setUrlFoto(post.getUrlFoto());        

        _postRepository.save(existingItem);

        return new ResponseEntity<>(existingItem, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            _postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}