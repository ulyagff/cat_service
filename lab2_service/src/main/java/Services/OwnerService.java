package Services;

import Dao.OwnerDao;
import Entity.Cat;
import Entity.Owner;

import java.time.LocalDate;
import java.util.List;

public class OwnerService {
    private OwnerDao dao;
    public OwnerService(OwnerDao dao) {
        this.dao = dao;
    }
    public Owner createOwner(String name, LocalDate birthday) {
        Owner owner = new Owner(name, birthday);
        dao.save(owner);
        return owner;
    }

    public void AddCatToOwner(Cat cat, Owner owner) {
        owner.addCat(cat);
    }
    public List<Cat> getCatsByOwnerId(int id) {
        return dao.getAllCatsByOwnerId(id);
    }


        public Owner getOwnerById(int id){
        return dao.getById(id);
    }
    public void deleteOwner(Owner owner){
        dao.deleteByEntity(owner);
    }

    public void deleteAll() {
        dao.deleteAll();
    }

    public List<Owner> getAll() {
        return dao.getAll();
    }

    }

