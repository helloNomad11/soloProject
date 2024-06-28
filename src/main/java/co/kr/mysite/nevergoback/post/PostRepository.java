package co.kr.mysite.nevergoback.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Post findByTitle(String title);
    public Post findByContent(String content);
    public Post findByTitleOrContent(String title, String content);
}
