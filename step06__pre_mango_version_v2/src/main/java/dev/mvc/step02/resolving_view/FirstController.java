package dev.mvc.step02.resolving_view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FirstController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ModealAndView 객체에 적절한 View 파일명과 화면에 렌더링할 Mpdel 데이터만 바인딩해주고,,
        // 반환하면
        ModelAndView mv = new ModelAndView();

        mv.setViewName("home");

        return null;
    }
}
