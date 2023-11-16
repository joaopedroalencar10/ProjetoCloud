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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api_imotors.api_imotors.service.PostService;
import api_imotors.api_imotors.exception.PostException;
import api_imotors.api_imotors.model.Post;


@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        try {
            return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<Post> getById(@PathVariable("id") Long id) {
        Optional<Post> existingItemOptional = postService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{idPessoa}")
    public ResponseEntity<Post> create(@PathVariable("idPessoa") long idPessoa, @RequestBody Post endereco) throws PostException{
     
            Post savedItem= postService.create(endereco);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> update(@PathVariable("id") Long id, @RequestBody Post endereco) throws PostException{
        return new ResponseEntity<>(postService.update(id, endereco), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id)  throws PostException {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping("{id}/file")
    public ResponseEntity<String> uploadPostImage(@PathVariable("id") long id, @RequestParam("file") MultipartFile file)  throws PostException, Exception{
        postService.uploadFileToPost(file, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}