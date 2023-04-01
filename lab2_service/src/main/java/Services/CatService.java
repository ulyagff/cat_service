package Services;

import Dao.CatDao;
import Entity.Cat;
import Entity.Owner;

import java.time.LocalDate;
import java.util.List;

public class CatService {
    private CatDao dao;

    public CatService(CatDao dao) {
        this.dao = dao;
    }

    public Cat createCat(String name, LocalDate birthday, String breed, Cat.ColorType color, Owner owner) {
        Cat cat = new Cat(name, birthday, breed, color, owner);
        dao.save(cat);
        return cat;
    }

    public Cat getCatById(int id) {
        return dao.getById(id);
    }

    public void deleteCat(Cat cat) {
        dao.deleteByEntity(cat);
    }

    public void deleteAll() {
        dao.deleteAll();
    }
}
