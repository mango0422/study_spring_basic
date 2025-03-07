package dev.mvc.step02.request_test;

import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 개발자가 만든 하나의 비즈니스 객체이기 때문에 빈으로 등록
@Controller
public class SecondController {

    public SecondController() {
        System.out.println("FirstController() called");
    }

    @RequestMapping(value = "/second-controller", method = RequestMethod.GET)
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("first-controller가 호출됨");
    }
}
