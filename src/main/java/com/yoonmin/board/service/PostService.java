package com.yoonmin.board.service;

import com.yoonmin.board.domain.dto.PostDto;
import com.yoonmin.board.domain.entity.PostEntity;
import com.yoonmin.board.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PostService {
    private PostRepository postRepository;
    private static final int BLOCK_PAGE_NUM_COUNT=10; //페이지 사이즈
    private static final int PAGE_POST_COUNT=15;//한 페이지당 나올 게시물 수

    @Transactional
    public Long savePost(PostDto postDto) {
        return postRepository.save(postDto.toEntity()).getId();
    }

    @Transactional
    public List<PostDto> getPostlist() {
        List<PostEntity> postEntities = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();

        for ( PostEntity postEntity : postEntities) {
            PostDto postDTO = PostDto.builder()
                    .id(postEntity.getId())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .writer(postEntity.getWriter())
                    .createdDate(postEntity.getCreatedDate())
                    .modifiedDate(postEntity.getModifiedDate())
                    .build();

            postDtoList.add(postDTO);
        }

        return postDtoList;
    }

    @Transactional
    public PostDto getPost(Long id) {
        Optional<PostEntity> postEntityWrapper = postRepository.findById(id);
        PostEntity postEntity = postEntityWrapper.get();

        PostDto postDto = PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .createdDate(postEntity.getCreatedDate())
                .build();

        return postDto;
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public List<PostDto> searchPosts(String keyword) {
        List<PostEntity> postEntities = postRepository.findByTitleContaining(keyword);
        List<PostDto> postDtoList = new ArrayList<>();

        if (postEntities.isEmpty()) return postDtoList;

        for (PostEntity postEntity : postEntities) {
            postDtoList.add(this.convertEntityToDto(postEntity));
        }

        return postDtoList;
    }

    private PostDto convertEntityToDto(PostEntity postEntity) {
        return PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .createdDate(postEntity.getCreatedDate())
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
