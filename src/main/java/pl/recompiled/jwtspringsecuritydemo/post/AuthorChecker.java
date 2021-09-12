package pl.recompiled.jwtspringsecuritydemo.post;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.recompiled.jwtspringsecuritydemo.user.AppUser;

@Component
@RequiredArgsConstructor
public class AuthorChecker {

    private final PostService postService;

    public boolean check(Long postId) {
        final Post post = postService.getPost(postId);
        final AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return post.getAuthorId().equals(user.getId().toString());
    }

}
