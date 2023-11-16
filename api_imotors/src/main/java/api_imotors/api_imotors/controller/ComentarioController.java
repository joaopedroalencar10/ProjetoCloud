package api_imotors.api_imotors.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api_imotors.api_imotors.service.ComentarioService;
import api_imotors.api_imotors.exception.CommentException;
import api_imotors.api_imotors.model.Comentario;


@RestController
@RequestMapping("/comentario")
@CrossOrigin
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<Comentario>> getAll() {
        try {
            return new ResponseEntity<>(comentarioService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Comentario> getById(@PathVariable("id") Long id) {
        Optional<Comentario> existingItemOptional = comentarioService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{idPost}")
    public ResponseEntity<Comentario> create(@PathVariable("idPost") long idPost, @RequestBody Comentario comentario) throws CommentException {
            Comentario savedItem = this.comentarioService.save(idPost, comentario);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }
   
    @PutMapping("{id}")
    public ResponseEntity<Comentario> update(@PathVariable("id") Long id, @RequestBody Comentario endereco) throws CommentException {
        return new ResponseEntity<>(comentarioService.update(id,endereco), HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id)  throws CommentException{
            comentarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    
}
}