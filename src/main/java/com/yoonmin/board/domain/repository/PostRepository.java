package com.yoonmin.board.domain.repository;

import com.yoonmin.board.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByTitleContaining(String keyword);

    List<PostEntity> findByUsernameContaining(String keyword);
}
