package co.kr.mysite.nevergoback.post;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;


@RequestMapping("/posts")
@AllArgsConstructor
@NoArgsConstructor
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping()
    public String getAllPost(Model model) {
        Iterable<Post> postList = postService.findAllPost();
        model.addAttribute("postList", postList);
        return "post/postMain";
    }

    @GetMapping("/{id}")
    public String getPostById(Model model, @PathVariable Long id) {
        Post post = postService.findPostById(id);
        if (post == null) {
            return "post/postMain";
        }
        model.addAttribute("post", post);
        return "post/postDetail"; // Thymeleaf 템플릿 이름
    }

    @GetMapping("/create")
    public String create() {
        return "post/postCreate";
    }

    @PostMapping("/create")
    public String create(@RequestParam String title, String content, String writer) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        post.setWriteTime(LocalDateTime.now().toLocalDate()); // Todo: 현시각
        this.postService.createPost(post);
        System.out.println("post: "+ post.getTitle() + " created");
        return "redirect:/posts/" + post.getPostId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute(this.postService.findPostById(id));
        return "post/postUpdate";
    }

    @Transactional
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestParam String title, String content) {
        Post post = this.postService.findPostById(id);
        post.setTitle(title);
        post.setContent(content);
        this.postService.updatePost(post);
        return "redirect:/posts/" + post.getPostId();
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Long id) {
        this.postService.deletePost(id);
        return "redirect:/posts";
    }
}
