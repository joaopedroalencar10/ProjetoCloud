package api_imotors.api_imotors.controller;

import java.util.ArrayList;
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


import api_imotors.api_imotors.repository.UsuarioRepository;
import api_imotors.api_imotors.model.Usuario;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        try {
            return new ResponseEntity<>(this._usuarioRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario item) {
        try {
            Usuario result = this._usuarioRepository.save(item);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario item) {
        try {
            
            Usuario result = this._usuarioRepository.findByUsernameAndSenha(item.getUsername(), item.getSenha());
            
            if(result == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    } 

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") long id) {

        Optional<Usuario> result = this._usuarioRepository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") long id, @RequestBody Usuario pessoaNovosDados) {

        Optional<Usuario> result = this._usuarioRepository.findById(id);

        // Não achei a pessoa a ser atualizada
        if (result.isPresent() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Usuario pessoaASerAtualizada = result.get();
        pessoaASerAtualizada.setNome(pessoaNovosDados.getNome());
        pessoaASerAtualizada.setFotoPerfil(pessoaNovosDados.getFotoPerfil());
        pessoaASerAtualizada.setUsername(pessoaNovosDados.getUsername());
        pessoaASerAtualizada.setSenha(pessoaNovosDados.getSenha());

        this._usuarioRepository.save(pessoaASerAtualizada);

        return new ResponseEntity<>(pessoaASerAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {

            Optional<Usuario> pessoaASerExcluida = this._usuarioRepository.findById(id);

            // Não achei a pessoa a ser excluida
            if (pessoaASerExcluida.isPresent() == false) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            this._usuarioRepository.delete(pessoaASerExcluida.get());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}