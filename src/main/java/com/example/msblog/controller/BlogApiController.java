package com.example.msblog.controller;

import com.example.msblog.domain.Article;
import com.example.msblog.dto.AddArticleRequest;
import com.example.msblog.dto.ArticleResponse;
import com.example.msblog.service.BlogService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController                         // HTTP RESPONSE BODY에 객체 데이터를 JSON 형태로 반환
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // @RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    // 전체 글을 조회한 뒤 반환하는 findAllArticles() 메서드 추가
    // /api/articles GET 요청이 오면 글 전체를 조회하는 findAll() 메서드 호출한 다음
    // 응답용 객체인 ArticleResponse로 파싱해 body에 담아 클라이언트에게 전송
    // stream: 여러 데이터가 모여 있는 컬렉션을 간편하게 처리하기 위한 기능
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }

}
