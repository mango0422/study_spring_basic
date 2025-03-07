package dev.mvc.step02.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.step02.model.Mouse;
import dev.mvc.step02.service.MouseService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/new-mouse-api/mice")
@RequiredArgsConstructor // Lombok annotation
public class NewMouseController {

    private final MouseService mouseService;

    @GetMapping // GET 요청에 대한 요청 매핑용 Annotation
    public ModelAndView getMice() {
        List<Mouse> mice = mouseService.findAll();
        ModelAndView mnv = new ModelAndView();
        mnv.addObject("mice", mice); // key값으로 mice, 실제 value에도 mice 리스트
        mnv.setViewName("mouseList"); // 파일명만 입력, 나머지는 ViewResolver가 처리

        return mnv;
    }

    @GetMapping(path = "/register")
    public String getMouseRegisterForm() { // 별도의 Model 데이터가 필요없는 페이지
        return "mouseRegisterForm";
    }

    @PostMapping(path = "/register")
    public String addMouse(@RequestParam("name") String name,
            @RequestParam("country") String country,
            @RequestParam("address") String address) {

        Mouse newMouse = new Mouse(name, country, address);
        mouseService.add(newMouse);

        return "redirect:/mouse-api/mice";
    }
}
