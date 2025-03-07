package dev.mvc.step02.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.step02.HomeController;
import dev.mvc.step02.dto.MouseDTO;
import dev.mvc.step02.model.Mouse;
import dev.mvc.step02.service.MouseService;

@Controller
@RequestMapping("/mouse-api/mice")
public class MouseController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final MouseService mouseService;

    @Autowired
    public MouseController(MouseService mouseService) {
        this.mouseService = mouseService;
    }

    List<Mouse> mouseList = new ArrayList<>();

    @GetMapping("/register")
    public String showRegisterForm() {
        // Mouse 등록 폼을 보여주는 JSP로 포워딩
        return "mouseRegisterForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute MouseDTO mouseDTO) {
        int newId = mouseService.generateNewId();
        Mouse newMouse = new Mouse(newId, mouseDTO.getName(), mouseDTO.getCountry(), mouseDTO.getAddress());
        mouseService.add(newMouse);
        return "redirect:/mouse-api/mice";
    }

    @GetMapping("")
    public String listMice(Model model) {
        // Mouse 목록 조회 후 모델에 담아 뷰로 전달
        List<Mouse> mouseList = mouseService.findAll();
        model.addAttribute("mice", mouseList);
        logger.info("mouseList: " + mouseList);
        return "mouseList";
    }
}