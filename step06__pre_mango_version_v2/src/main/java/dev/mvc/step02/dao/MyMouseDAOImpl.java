package dev.mvc.step02.dao;

import dev.mvc.step02.model.Mouse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MyMouseDAOImpl implements MyMouseDAO {
    private final List<Mouse> mice = new ArrayList<>();

    // 초기 데이터 설정 (필요시)
    public MyMouseDAOImpl() {
        mice.add(new Mouse(10, "미니", "하와이", "구직중"));
        mice.add(new Mouse(25, "미키", "런던", "맨체스터 유나이티드"));
        mice.add(new Mouse(15, "제이", "상하이", "텐센트"));
        mice.add(new Mouse(20, "제리", "도쿄", "라인"));
        mice.add(new Mouse(40, "제이미", "대한민국", "삼성"));
    }

    @Override
    public List<Mouse> findAll() {
        return mice;
    }

    @Override
    public void add(Mouse mouse) {
        mice.add(mouse);
    }
}
