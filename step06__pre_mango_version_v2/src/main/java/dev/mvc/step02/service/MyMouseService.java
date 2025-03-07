package dev.mvc.step02.service;

import dev.mvc.step02.dao.MyMouseDAO;
import dev.mvc.step02.model.Mouse;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MyMouseService {
    private final MyMouseDAO mouseDAO;

    public MyMouseService(MyMouseDAO mouseDAO) {
        this.mouseDAO = mouseDAO;
    }

    public List<Mouse> findAll() {

        // DB에서 Mouse 목록 조회 처리 수행했다고 가정(DAO코드 작성 부분)
        return mouseDAO.findAll();
    }

    public void add(Mouse mouse) {
        mouseDAO.add(mouse);
    }

    public int generateNewId() {
        return mouseDAO.findAll().stream()
                .mapToInt(Mouse::getId)
                .max()
                .orElse(0) + 1;
    }
}
