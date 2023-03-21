package com.yoonmin.board.domain.repository;

import com.yoonmin.board.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByTitleContaining(String keyword);
}
