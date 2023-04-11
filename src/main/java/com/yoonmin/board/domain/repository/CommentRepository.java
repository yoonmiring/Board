package com.yoonmin.board.domain.repository;

import com.yoonmin.board.domain.entity.CommentEntity;
import com.yoonmin.board.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository  extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPostId(PostEntity postEntity);

}
