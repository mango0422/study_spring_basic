package dev.mvc.step02.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.step02.model.Mouse;

@Controller
@RequestMapping("/mouse-api/mice")
public class MouseController {
    List<Mouse> mouseList = new ArrayList<>();

    @GetMapping("/register")
    public String showRegisterForm() {
        // Mouse 등록 폼을 보여주는 JSP로 포워딩
        return "mouseRegisterForm";
    }

    @GetMapping("")
    public String listMice(Model model) {
        // Mouse 목록 조회 후 모델에 담아 뷰로 전달
        model.addAttribute("mice", mouseList);
        return "mouseList";
    }
}