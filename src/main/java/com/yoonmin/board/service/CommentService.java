package com.yoonmin.board.service;

import com.yoonmin.board.domain.dto.CommentDto;
import com.yoonmin.board.domain.entity.CommentEntity;
import com.yoonmin.board.domain.entity.PostEntity;
import com.yoonmin.board.domain.repository.CommentRepository;
import com.yoonmin.board.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    //댓글 조회
    @Transactional
    public List<CommentDto> getCommentListByPostId(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        List<CommentEntity> commentEntities = commentRepository.findByPostId(postEntity);
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (CommentEntity commnentEntity : commentEntities) {
            CommentDto commentDto = CommentDto.builder()
                    .id(commnentEntity.getId())
                    .content(commnentEntity.getContent())
                    .postId(commnentEntity.getPostId().getId())
                    .username(commnentEntity.getUsername())
                    .createdAt(commnentEntity.getCreatedAt())
                    .password(commnentEntity.getPassword())
                    .build();
            commentDtoList.add(commentDto);
        }

        return commentDtoList;
    }

    //    댓글 등록
    @Transactional
    public Long saveComment(CommentDto commentDto) {
        return commentRepository.save(commentDto.toEntity(postRepository)).getId();
    }

    //    댓글 삭제
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
