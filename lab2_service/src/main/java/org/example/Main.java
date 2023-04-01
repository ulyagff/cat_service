package org.example;

import Controller.MainController;
import Dao.*;
import Entity.Cat;
import Entity.Owner;
import Services.CatService;
import Services.OwnerService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        OwnerDao ownerDaoHibernate = new OwnerDaoHibernate();
        OwnerService ownerServiceHibernate = new OwnerService(ownerDaoHibernate);
        CatDao catDaoHibernate = new CatDaoHibernate();
        CatService catServiceHibernate = new CatService(catDaoHibernate);
        MainController controllerHibernate = new MainController(ownerServiceHibernate, catServiceHibernate);

        long startTimeHibernateSave = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            controllerHibernate.createOwner("putin", LocalDate.of(1952, 10, 7));
        }
        long endTimeHibernateSave = System.currentTimeMillis();
        System.out.println("time for saving 100 owners by hibernate: " + (endTimeHibernateSave - startTimeHibernateSave) + "ms");

        long startTimeHibernateGetAll = System.currentTimeMillis();
        controllerHibernate.getAllOwners();
        long endTimeHibernateGetAll = System.currentTimeMillis();
        System.out.println("time for get 100 owners by hibernate: " + (endTimeHibernateGetAll - startTimeHibernateGetAll) + "ms");

        controllerHibernate.deleteAll();


        OwnerDao ownerDaoJDBC = new OwnerDaoJDBC();
        OwnerService ownerServiceJDBC = new OwnerService(ownerDaoJDBC);
        CatDao catDaoJDBC = new CatDaoJDBC(ownerDaoJDBC);
        CatService catServiceJDBC = new CatService(catDaoJDBC);
        MainController controllerJDBC = new MainController(ownerServiceJDBC, catServiceJDBC);

        long startTimeJDBCSave = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            controllerJDBC.createOwner("putin", LocalDate.of(1952, 10, 7));
        }
        long endTimeJDBCteSave = System.currentTimeMillis();
        System.out.println("time for saving 100 owners by JDBC: " + (endTimeJDBCteSave - startTimeJDBCSave) + "ms");

        long startTimeJDBCGetAll = System.currentTimeMillis();
        controllerJDBC.getAllOwners();
        long endTimeJDBCGetAll = System.currentTimeMillis();
        System.out.println("time for get 100 owners by JDBC: " + (endTimeJDBCGetAll - startTimeJDBCGetAll) + "ms");

        controllerJDBC.deleteAll();

        OwnerDao ownerDaoMyBatis = new OwnerDaoMyBatis();
        OwnerService ownerServiceMyBatis = new OwnerService(ownerDaoMyBatis);
        CatDao catDaoMyBatis = new CatDaoMyBatis();
        CatService catServiceMyBatis = new CatService(catDaoMyBatis);
        MainController controllerMyBatis = new MainController(ownerServiceMyBatis, catServiceMyBatis);

        long startTimeMyBatisSave = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            controllerMyBatis.createOwner("putin", LocalDate.of(1952, 10, 7));
        }
        long endTimeMyBatisteSave = System.currentTimeMillis();
        System.out.println("time for saving 100 owners by JDBC: " + (endTimeMyBatisteSave - startTimeMyBatisSave) + "ms");

        long startTimeMyBatisGetAll = System.currentTimeMillis();
        controllerMyBatis.getAllOwners();
        long endTimeMyBatisGetAll = System.currentTimeMillis();
        System.out.println("time for get 100 owners by MyBatis: " + (endTimeMyBatisGetAll - startTimeMyBatisGetAll) + "ms");

        controllerMyBatis.deleteAll();

    }
}