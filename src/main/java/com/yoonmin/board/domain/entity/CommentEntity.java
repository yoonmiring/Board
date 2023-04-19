package com.yoonmin.board.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(length = 10, nullable = false)
    private String username;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private PostEntity postId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(length = 10, nullable = false)
    private String password;
    @Builder
    public CommentEntity(Long id, String content, String username, PostEntity postId, LocalDateTime createdAt, String password) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.postId = postId;
        this.createdAt = createdAt;
        this.password = password;
    }
}
