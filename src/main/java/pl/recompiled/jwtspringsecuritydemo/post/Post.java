package pl.recompiled.jwtspringsecuritydemo.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorId;
    private String content;

    public static Post newInstance(String authorId, String content) {
        final Post post = new Post();
        post.authorId = authorId;
        post.content = content;
        return post;
    }
}
