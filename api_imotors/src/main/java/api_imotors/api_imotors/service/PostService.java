package api_imotors.api_imotors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api_imotors.api_imotors.exception.BusinessException;
import api_imotors.api_imotors.exception.PostException;
import api_imotors.api_imotors.model.Post;
import api_imotors.api_imotors.model.Usuario;
import api_imotors.api_imotors.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private AzureStorageAccountService azureStorageAccountService;

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    public Optional<Post> findById(long id) {
        return this.postRepository.findById(id);
    }

    public List<Post> getAll() {
        return this.postRepository.findAll();
    }

    public void saveOrUpdate(Post item) {
        this.postRepository.save(item);
    }

    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    public Post update(long id, Post newData) throws PostException{
        Optional<Post> existingItemOptional = postRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new PostException("Não encontrei o post a ser atualizado");

        Post existingItem = existingItemOptional.get();

        existingItem.setData(newData.getData());
        existingItem.setUrlFoto(newData.getUrlFoto());

        postRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws PostException {
        Optional<Post> endereco = this.postRepository.findById(id);

        if (endereco.isPresent() == false)
            throw new PostException("Não encontrei o endereco a ser atualizado");

        this.postRepository.delete(endereco.get());
    }

    public void saveComentario(Post post) {
        this.postRepository.save(post);
    }

    public void uploadFileToPost(MultipartFile file, long id) throws PostException, Exception {
        
        Optional<Post> opPost = this.postRepository.findById(id);
        
        if (opPost.isPresent() == false) {
            throw new PostException("Não encontrei o post a ser atualizado");
        }

        Post post = opPost.get();
        String ulrImage = this.azureStorageAccountService.uploadFileToAzure(file);
        post.setUrlFoto(ulrImage);
        this.postRepository.save(post);
    }

}
