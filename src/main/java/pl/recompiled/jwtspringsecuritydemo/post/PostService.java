package pl.recompiled.jwtspringsecuritydemo.post;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.recompiled.jwtspringsecuritydemo.NotFoundException;

@Component
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Post createPost(String content) {
        final String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return postRepository.save(Post.newInstance(userId, content));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
