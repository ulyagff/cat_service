import Controller.MainController;
import Dao.*;
import Entity.*;
import Services.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class CatTestHibernateRealDB {
    private static MainController controller;

    @BeforeAll
    public static void init(){
        OwnerDao ownerDao = new OwnerDaoHibernate();
        OwnerService ownerService = new OwnerService(ownerDao);
        CatDao catDao = new CatDaoHibernate();
        CatService catService = new CatService(catDao);
        controller = new MainController(ownerService, catService);
    }

    @Test
    public void CreateOwnerAddCat() {
        Owner owner1 = controller.createOwner("Ульяна Глебова", LocalDate.of(2003, 8, 8));
        Cat cat1 = controller.createCat("barsik", LocalDate.of(2015, 1, 13), "Siamsky", Cat.ColorType.white, owner1);
        Owner owner2 = controller.createOwner("Dima", LocalDate.of(2005, 9, 8));
        Cat cat2 = controller.createCat("murzik", LocalDate.of(2019, 1, 3), "Siamsky", Cat.ColorType.black, owner2);
        Cat cat3 = controller.createCat("saske", LocalDate.of(2020, 1, 3), "Siamsky", Cat.ColorType.black, owner2);
        Assertions.assertEquals(2, owner2.getCats().size());
        Assertions.assertEquals(1, owner1.getCats().size());
    }

    @Test
    public void CreateOwnerAddCatDeleteOwner() {
        Owner owner1 = controller.createOwner("Jim Kerri", LocalDate.of(2000, 11, 30));
        Cat cat1 = controller.createCat("barsik", LocalDate.of(2015, 1, 13), "Siamsky", Cat.ColorType.white, owner1);
        controller.deleteOwner(owner1);
        Owner expectedNullOwner = controller.getOwnerById(owner1.getId());
        Cat expectedNullCat = controller.getCatById(cat1.getId());
        Assertions.assertNull(expectedNullCat);
        Assertions.assertNull(expectedNullOwner);
    }

    @Test
    public void GetCatsByOwner() {
        Owner owner = controller.createOwner("Dima", LocalDate.of(2005, 9, 8));
        Cat cat1 = controller.createCat("murzik", LocalDate.of(2019, 1, 3), "Siamsky", Cat.ColorType.black, owner);
        Cat cat2 = controller.createCat("saske", LocalDate.of(2020, 1, 3), "Siamsky", Cat.ColorType.black, owner);

        Assertions.assertEquals(2, controller.getCatsByOwnerId(owner.getId()).size());
    }
}
