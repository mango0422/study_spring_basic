package dev.mvc.step02.request_test;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 개발자가 만든 하나의 비즈니스 객체이기 때문에 빈으로 등록
@Component(value = "/first-controller") // lh:8080/step02/first-controller로 요청 시 처리할 핸들러
public class FirstController implements Controller {

    public FirstController() {
        System.out.println("FirstController() called");
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("first-controller가 호출됨");
        return null;
    }
}
