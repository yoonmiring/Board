package com.yoonmin.board.service;

import com.yoonmin.board.domain.dto.BoardDto;
import com.yoonmin.board.domain.dto.PostDto;
import com.yoonmin.board.domain.entity.PostEntity;
import com.yoonmin.board.domain.repository.PostRepository;
import lombok.AllArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 10; //페이지 사이즈
    private static final int PAGE_POST_COUNT = 15;//한 페이지당 나올 게시물 수


    @Transactional
    public Page<BoardDto> getPostlist(Pageable pageable) {
        Page<PostEntity> postEntities = postRepository.findAll(pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            BoardDto boardDto = BoardDto.builder()
                    .id(postEntity.getId())
                    .title(postEntity.getTitle())
                    .username(postEntity.getUsername())
                    .createdAt(postEntity.getCreatedAt())
                    .updatedAt(postEntity.getUpdatedAt())
                    .hits(postEntity.getHits())
                    .build();

            boardDtoList.add(boardDto);
        }

        return new PageImpl<>(boardDtoList, pageable, postEntities.getTotalElements());
    }

    //    개별 게시물 조회
    @Transactional
    public PostDto getPost(Long id) {
        Optional<PostEntity> postEntityWrapper = postRepository.findById(id);
        PostEntity postEntity = postEntityWrapper.get();

        PostDto postDto = PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .username(postEntity.getUsername())
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .hits(postEntity.getHits()+1)
                .build();

        return postDto;
    }
//    //조회수 증가
    @Transactional
    public void increaseViewCount(Long postId) {
        Optional<PostEntity> postEntityOpt = postRepository.findById(postId);
        if (postEntityOpt.isPresent()) {
            postRepository.updateHits(postId);
        } else {
            throw new EntityNotFoundException("Post not found");
        }
    }
    //게시물 작성
    @Transactional
    public Long savePost(PostDto postDto) {
        return postRepository.save(postDto.toEntity()).getId();
    }

    //게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    //게시물 수정
    @Transactional
    public PostDto updatePost(Long id, PostDto postDto){
        Optional<PostEntity> byId = postRepository.findById(id);
        PostEntity updatePost = byId.orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));
        updatePost.update(postDto.getTitle(), postDto.getContent());
        updatePost.setUpdatedAt(LocalDateTime.now());
        return PostDto.builder()
                .id(updatePost.getId())
                .build();
    }

//    게시글 검색
    @Transactional
    public Page<BoardDto> searchBoard(String keyword, String target,Pageable pageable) {
        Page<PostEntity> postEntities;

        if (target.equals("title")) {
            postEntities = postRepository.findByTitleContaining(keyword, pageable);
        } else if (target.equals("username")) {
            postEntities = postRepository.findByUsernameContaining(keyword, pageable);
        } else {
            postEntities = Page.empty();
        }

        return postEntities.map(this::convertEntityToDto);
    }
    private BoardDto convertEntityToDto(PostEntity postEntity) {
        return BoardDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .username(postEntity.getUsername())
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .hits(postEntity.getHits())
                .build();
    }
}
