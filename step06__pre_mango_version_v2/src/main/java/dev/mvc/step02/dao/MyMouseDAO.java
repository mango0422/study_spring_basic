package dev.mvc.step02.dao;

import dev.mvc.step02.model.Mouse;
import java.util.List;

public interface MyMouseDAO {
    List<Mouse> findAll();

    void add(Mouse mouse);
}
