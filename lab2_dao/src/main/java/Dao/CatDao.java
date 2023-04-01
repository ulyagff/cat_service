package Dao;

import Entity.Cat;

import java.util.List;

public interface CatDao {
    public List<Cat> getAll();

    public Cat getById(int id);
    public void save(Cat cat);
    public void deleteByEntity(Cat cat);
    public void update(Cat cat);
    public void deleteAll();
    public void deleteById(int id);
}
