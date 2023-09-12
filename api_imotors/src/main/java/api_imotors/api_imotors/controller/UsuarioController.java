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


import api_imotors.api_imotors.service.UsuarioService;
import jakarta.validation.Valid;
import api_imotors.api_imotors.model.Usuario;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService _usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        try {
            return new ResponseEntity<>(this._usuarioService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario item) {
        try {
            Usuario result = this._usuarioService.save(item);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") long id) {

        Optional<Usuario> result = this._usuarioService.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } 
            
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") long id, @RequestBody Usuario pessoaNovosDados) {
        try {
            Usuario result = this._usuarioService.update(id, pessoaNovosDados);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            this._usuarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Usuario> login(@RequestBody String username, @RequestBody String senha) {
        try {
            
            Usuario result = this._usuarioService.login(username, senha);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    } 

    
}