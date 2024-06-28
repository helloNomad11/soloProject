package co.kr.mysite.nevergoback.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post findPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Iterable<Post> findAllPost(){
        return postRepository.findAll();
    }

    public Post findPostByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public Post findPostByContent(String content) {
        return postRepository.findByContent(content);
    }

    public Post findByTitleOrContent(String title, String content) {
        return postRepository.findByTitleOrContent(title, content);
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
