package com.example.msblog.service;

import com.example.msblog.domain.Article;
import com.example.msblog.dto.AddArticleRequest;
import com.example.msblog.dto.UpdateArticleRequest;
import com.example.msblog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor                // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service                                // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 모든 글 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 하나를 조회, 조회 후 글이 없으면 예외 처리
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    // 삭제 메서드 추가
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    // 업데이트 메서드 추가
    @Transactional          // 트랜잭션 메서드
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }

}
