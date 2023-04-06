package com.yoonmin.board.domain.repository;

import com.yoonmin.board.domain.dto.BoardDto;
import com.yoonmin.board.domain.dto.PostDto;
import com.yoonmin.board.domain.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Page<PostEntity> findAll(Pageable pageable);
    Page<PostEntity> findByTitleContaining(String keyword,Pageable pageable);

    Page<PostEntity> findByUsernameContaining(String keyword,Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE PostEntity p SET p.hits = p.hits + 1 WHERE p.id = :id")
    void updateHits(@Param("id") Long id);
}
