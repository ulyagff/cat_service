package Dao;

import Entity.Cat;
import Entity.Owner;
import utils.DataSource;

import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CatDaoJDBC implements CatDao{
    private OwnerDao ownerDao;

    public CatDaoJDBC(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    public List<Cat> getAll() {
        String SQL_QUERY_GET_ALL = "SELECT * FROM cats";
        List<Cat> cats = null;


        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementCat = connection.prepareStatement(SQL_QUERY_GET_ALL);
             ResultSet setCats = statementCat.executeQuery();) {

            cats = new ArrayList<>();
            Cat cat;
            while (setCats.next()) {
                String name = setCats.getString("name");
                LocalDate date = setCats.getDate("birthday")
                        .toLocalDate();
                String breed = setCats.getString("breed");
                Cat.ColorType colorType = Cat.ColorType.valueOf(setCats.getString("color"));
                int ownerId = setCats.getInt("owner_id");
                Owner owner = ownerDao.getById(ownerId);
                cat = new Cat(name, date, breed, colorType, owner);
                cats.add(cat);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cats;
    }


    @Override
    public Cat getById(int id) {
        String SQL_QUERY_GET_BY_ID = MessageFormat.format("SELECT * FROM cats AS c WHERE c.id={0}", id);
        Cat cat = null;


        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementCat = connection.prepareStatement(SQL_QUERY_GET_BY_ID);
             ResultSet setCats = statementCat.executeQuery();) {

            String name = setCats.getString("name");
            LocalDate date = setCats.getDate("birthday")
                    .toLocalDate();
            String breed = setCats.getString("breed");
            Cat.ColorType colorType = Cat.ColorType.valueOf(setCats.getString("color"));
            int ownerId = setCats.getInt("owner_id");
            Owner owner = ownerDao.getById(ownerId);
            cat = new Cat(name, date, breed, colorType, owner);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cat;
    }

    @Override
    public void save(Cat cat) {
        String SQL_QUERY_SAVE = "INSERT INTO cats(name, birthday, breed, color, owner_id) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementCat = connection.prepareStatement(SQL_QUERY_SAVE, Statement.RETURN_GENERATED_KEYS)) {

            statementCat.setString(1, cat.getName());
            statementCat.setDate(2, Date.valueOf(cat.getBirthday()));
            statementCat.setString(3, cat.getBreed());
            statementCat.setString(4, cat.getColor().toString());
            statementCat.setInt(5, cat.getOwner().getId());
            statementCat.executeUpdate();
            ResultSet key = statementCat.getGeneratedKeys();
            if (key.next()) {
                cat.setId(key.getInt(1));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteByEntity(Cat cat) {
        String SQL_QUERY_DELETE = MessageFormat.format("DELETE FROM cats WHERE id = {1}", cat.getId());
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementCat = connection.prepareStatement(SQL_QUERY_DELETE)) {
            statementCat.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Cat cat) {
        String SQL_QUERY_DELETE = "UPDATE cats SET name = ?, birthday = ?, breed = ?, color = ?, owner_id = ? WHERE id = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementCat = connection.prepareStatement(SQL_QUERY_DELETE)) {

            statementCat.setString(1, cat.getName());
            statementCat.setDate(2, Date.valueOf(cat.getBirthday()));
            statementCat.setString(3, cat.getBreed());
            statementCat.setString(4, cat.getColor().toString());
            statementCat.setInt(5, cat.getOwner().getId());
            statementCat.setInt(6, cat.getId());
            statementCat.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        String SQL_QUERY_DELETE = "DELETE FROM cats";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementCat = connection.prepareStatement(SQL_QUERY_DELETE)) {
            statementCat.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String SQL_QUERY_DELETE = MessageFormat.format("DELETE FROM cats WHERE id = {1}", id);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementCatDelete = connection.prepareStatement(SQL_QUERY_DELETE)) {
            statementCatDelete.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
