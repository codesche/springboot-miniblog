package com.example.msblog.controller;

import com.example.msblog.domain.Article;
import com.example.msblog.dto.ArticleListViewResponse;
import com.example.msblog.dto.ArticleViewResponse;
import com.example.msblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);       // 블로그 글 리스트 저장
        return "articleList";                                       // articleList.html 라는 뷰 조회
    }

    // 블로그 글 반환
    // 인자 id에 URL로 넘어온 값을 받아 findById() 메서드로 넘겨 글을 조회하고,
    // 화면에서 사용할 모델에 데이터 저장 후
    // 보여줄 화면의 템플릿 이름을 반환
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터 값을 id 변수에 매핑(id가 없을 수도 있다)
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {       // id가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else {                // id가 없으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }

}
