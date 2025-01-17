package com.example.relation.global;


import com.example.relation.domain.comment.Comment;
import com.example.relation.domain.comment.CommentRepository;
import com.example.relation.domain.post.entity.Post;
import com.example.relation.domain.post.entity.PostTag;
import com.example.relation.domain.post.repository.PostRepository;
import com.example.relation.domain.post.repository.PostTagRepository;
import com.example.relation.domain.tag.Tag;
import com.example.relation.domain.tag.TagRepository;
import com.example.relation.domain.user.dto.request.SignupRequestDto;
import com.example.relation.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PostRepository postRepository;
    private final AuthService authService;
    private final PostTagRepository postTagRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;


    @Override
    public void run(String... args) throws Exception {
        initUser();
        initPost();
    }

    public void initUser() {

        SignupRequestDto requestDto = SignupRequestDto.builder()
                .username("jun")
                .password("haha1332!")
                .email("jun@example.com")
                .build();

        authService.signup(requestDto);
    }


    @Transactional
    public void initPost(){
        if (postRepository.count() == 0){

            for (int i=1; i<=3; i++) {
                Post post = Post.builder()
                        .title("title" + i)
                        .content("content" + i)
                        .build();
                postRepository.save(post);

                for (int j=1; j<=2; j++){
                    Tag tag = Tag.builder().name("tag" + i + j).build();
                    tagRepository.save(tag);

                    PostTag postTag = new PostTag();
                    postTag.addPost(post);
                    postTag.addTag(tag);

                    postTagRepository.save(postTag);

                    Comment comment = Comment.builder()
                            .content("comment" + i + j)
                            .post(post).build();
                    commentRepository.save(comment);
                }
            }
        }
    }
}
