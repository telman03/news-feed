package app.controller;

import app.service.ArticleService;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import app.entity.Article;

import java.util.ArrayList;

@Log4j2
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticleService articleService;
    private final UserService userService;



    @GetMapping("/news_feed")
    public String showDesignForm(Model model) {

        model.addAttribute("article", new Article());
        return "main-page";
    }

}