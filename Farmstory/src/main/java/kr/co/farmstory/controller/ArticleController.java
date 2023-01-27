package kr.co.farmstory.controller;

import kr.co.farmstory.security.MyUserDetails;
import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService service;

    @GetMapping("board/list")
    public String list(Model model, String group, String cate, String pg){

        // 게시물 목록 & 페이징 처리
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        int total = service.getTotalCount(cate);
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        List<ArticleVO> articles = service.selectArticles(start, cate);

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("pg", currentPage);

        model.addAttribute("articles", articles);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);

        return "board/list";
    }

    @GetMapping("board/modify")
    public String modify(Model model, String group, String cate, int no, int pg){

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("pg", pg);

        ArticleVO article = service.selectArticle(no);
        model.addAttribute("article", article);

        return "board/modify";
    }

    @PostMapping("board/modify")
    public String modify(String group, String cate, int no, int pg, ArticleVO vo){
        service.updateArticle(vo);
        return "redirect:/board/view?group="+group+"&cate="+cate+"&no="+no+"&pg="+pg;
    }

    @GetMapping("board/view")
    public String view(Model model, String group, String cate, int no, int pg){

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("pg", pg);
        
        // 게시글 가져오기
        ArticleVO article = service.selectArticle(no);
        model.addAttribute("article", article);

        // 조회수 +1
        service.updateHit(no);

        return "board/view";
    }

    @GetMapping("board/write")
    public String write(Model model, String group, String cate){

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);

        return "board/write";
    }

    @PostMapping("board/write")
    public String write(String group, String cate, ArticleVO vo){
        service.insertArticle(vo);
        return "redirect:/board/list?group="+group+"&cate="+cate;
    }

    @GetMapping("board/download")
    public ResponseEntity<Resource> download(int fno) throws IOException {

        FileVO vo = service.selectFile(fno);

        ResponseEntity<Resource> respEntity = service.fileDownload(vo);
        return respEntity;
    }

    @GetMapping("board/delete")
    public String delete(String group, String cate, String pg, int no){
        service.deleteArticle(no);
        return "redirect:/board/list?group="+group+"&cate="+cate+"&pg="+pg;
    }
}
