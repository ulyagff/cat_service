package Controller;

import Entity.Cat;
import Entity.Owner;
import Services.CatService;
import Services.OwnerService;

import java.time.LocalDate;
import java.util.List;

public class MainController {
    private OwnerService ownerService;
    private CatService catService;
    public MainController(OwnerService ownerService, CatService catService) {
        this.ownerService = ownerService;
        this.catService = catService;
    }

    public Owner createOwner(String name, LocalDate birthday) {
        return ownerService.createOwner(name, birthday);
    }

    public Cat createCat(String name, LocalDate birthday, String breed, Cat.ColorType color, Owner owner) {
        Cat cat = catService.createCat(name, birthday, breed, color, owner);
        ownerService.AddCatToOwner(cat, owner);
        return cat;
    }
    public Owner getOwnerById(int id){
        return ownerService.getOwnerById(id);
    }
    public Cat getCatById(int id) {
        return catService.getCatById(id);
    }
    public List<Cat> getCatsByOwnerId(int id) {
        return ownerService.getCatsByOwnerId(id);
    }
        public void deleteCat(Cat cat){
        catService.deleteCat(cat);
    }
    public void deleteOwner(Owner owner){
        ownerService.deleteOwner(owner);
    }

    public void deleteAll(){
        ownerService.deleteAll();
        catService.deleteAll();
    }

    public List<Owner> getAllOwners() {
        return ownerService.getAll();
    }


}
