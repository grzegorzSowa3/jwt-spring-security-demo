package pl.recompiled.jwtspringsecuritydemo.post;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody CreatePostDto dto) {
        postService.createPost(dto.content);
        return ResponseEntity.status(CREATED).build();
    }

    @DeleteMapping("{postId}")
    @PreAuthorize("hasAuthority('ADMIN') or @authorChecker.check(#postId)")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Data
    public static class CreatePostDto {
        private String content;
    }
}
