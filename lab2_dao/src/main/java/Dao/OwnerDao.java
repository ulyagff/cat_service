package Dao;

import Entity.*;

import java.util.List;
public interface OwnerDao {
    public List<Owner> getAll();
    public Owner getById(int id);
    public void save(Owner owner);
    public void deleteByEntity(Owner owner);
    public void update(Owner owner);
    public void deleteAll();
    public void deleteById(int id);
    public List<Cat> getAllCatsByOwnerId(int id);

}
