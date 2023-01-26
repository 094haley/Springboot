package kr.co.farmstory.controller;

import kr.co.farmstory.service.UserService;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("user/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("user/terms")
    public String terms(Model model){

        TermsVO vo = service.selectTerms();
        model.addAttribute("vo", vo);

        return "user/terms";
    }

    @GetMapping("user/register")
    public String register(){
        return "user/register";
    }

    @PostMapping("user/register")
    public String register(UserVO vo, HttpServletRequest req){

        vo.setRegip(req.getRemoteAddr());
        int result = service.insertUser(vo);
        return "redirect:/user/login?success="+result;
    }

    @ResponseBody
    @GetMapping("user/checkUid")
    public Map<String, Integer> checkUid(@RequestParam("uid") String uid){

        int result = service.countByUid(uid);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    @ResponseBody
    @GetMapping("user/checkNick")
    public Map<String, Integer> checkNick(@RequestParam("nick") String nick){

        int result = service.countByNick(nick);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

}
