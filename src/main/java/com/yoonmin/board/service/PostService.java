package com.yoonmin.board.service;

import com.yoonmin.board.domain.dto.BoardDto;
import com.yoonmin.board.domain.dto.PostDto;
import com.yoonmin.board.domain.entity.PostEntity;
import com.yoonmin.board.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import javax.persistence.EntityManager;
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


    //전체 게시물 목록 조회
    @Transactional
    public List<BoardDto> getPostlist() {
        List<PostEntity> postEntities = postRepository.findAll();
        List<BoardDto> postDtoList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            BoardDto boardDTO = BoardDto.builder()
                    .id(postEntity.getId())
                    .title(postEntity.getTitle())
                    .username(postEntity.getUsername())
                    .createdAt(postEntity.getCreatedAt())
                    .updatedAt(postEntity.getUpdatedAt())
                    .hits(postEntity.getHits())
                    .build();

            postDtoList.add(boardDTO);
        }

        return postDtoList;
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
//    @Transactional
//    public void updateHits(Long id, BoardDto boardDto){
//        Optional<PostEntity> byId = postRepository.findById(id);
//        PostEntity updateHits = byId.orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));
//        updateHits.updateHits(updateHits.getHits());
//    }
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
        return PostDto.builder()
                .id(updatePost.getId())
                .build();
    }

//    게시글 검색
//    @Transactional
//    public List<BoardDto> searchBoard(String keyword) {
//
//        List<PostEntity> postEntities = postRepository.findByTitleContaining(keyword);
//        List<BoardDto> postDtoList = new ArrayList<>();
//
//        if (postEntities.isEmpty()) return postDtoList;
//
//        for (PostEntity postEntity : postEntities) {
//            postDtoList.add(this.convertEntityToDto(postEntity));
//        }
//        return postDtoList;
//    }

    @Transactional
    public List<BoardDto> searchBoard(String keyword, String target) {
        List<PostEntity> postEntities;

        if (target.equals("title")) {
            postEntities = postRepository.findByTitleContaining(keyword);
        } else if (target.equals("username")) {
            postEntities = postRepository.findByUsernameContaining(keyword);
        } else {
            postEntities = Collections.emptyList();
        }

        List<BoardDto> postDtoList = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            postDtoList.add(this.convertEntityToDto(postEntity));
        }

        return postDtoList;
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
////페이징
//    @Transactional
//    public List<PostDto> getBoardlist(Integer pageNum) {
//        Page<PostEntity> page = postRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));
//
//        List<PostEntity> postEntities = page.getContent();
//        List<PostDto> postDtoList = new ArrayList<>();
//
//        for (PostEntity postEntity : postEntities) {
//            postDtoList.add(this.convertEntityToDto(postEntity));
//        }
//
//        return postDtoList;
//    }
//
//    @Transactional
//    public Long getPostCount() {
//        return postRepository.count();
//    }
//
//    public Integer[] getPageList(Integer curPageNum) {
//        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];
//
//        Double postsTotalCount = Double.valueOf(this.getPostCount());
//
//
//        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
//
//
//        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
//                ? curPageNum + BLOCK_PAGE_NUM_COUNT
//                : totalLastPageNum;
//
//
//        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;
//
//
//        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
//            pageList[idx] = val;
//        }
//
//        return pageList;
//    }
}
