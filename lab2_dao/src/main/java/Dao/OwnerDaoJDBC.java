package Dao;

import Entity.Cat;
import Entity.Owner;
import utils.DataSource;

import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OwnerDaoJDBC implements OwnerDao{
    @Override
    public List<Owner> getAll() {
        String SQL_QUERY_GET_ALL = "SELECT * FROM owners";
        List<Owner> owners = null;


        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementOwner = connection.prepareStatement(SQL_QUERY_GET_ALL);
             ResultSet setOwners = statementOwner.executeQuery();) {

            owners = new ArrayList<>();
            Owner owner;
            while (setOwners.next()) {
                int id = setOwners.getInt("id");
                String name = setOwners.getString("name");
                LocalDate date = setOwners.getDate("birthday")
                        .toLocalDate();
                owner = new Owner(name, date);
                owner.setId(id);

                List<Cat> cats = getAllCatsByOwnerId(owner.getId());
                owner.setCats(cats);
                owners.add(owner);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return owners;
    }

    @Override
    public Owner getById(int id) {
        String SQL_QUERY_GET_BY_ID = MessageFormat.format("SELECT * FROM OWNERS AS o WHERE o.id={0}", id);
        Owner owner = null;


        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementOwner = connection.prepareStatement(SQL_QUERY_GET_BY_ID);
             ResultSet setOwners = statementOwner.executeQuery();) {

            String name = setOwners.getString("name");
            LocalDate date = setOwners.getDate("birthday")
                        .toLocalDate();

            owner = new Owner(name, date);
            owner.setId(id);

            List<Cat> cats = getAllCatsByOwnerId(owner.getId());
            owner.setCats(cats);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return owner;
    }

    @Override
    public void save(Owner owner) {
        String SQL_QUERY_SAVE = "INSERT INTO owners(name, birthday) VALUES(?, ?)";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementOwner = connection.prepareStatement(SQL_QUERY_SAVE, Statement.RETURN_GENERATED_KEYS)) {
            statementOwner.setString(1, owner.getName());
            statementOwner.setDate(2, Date.valueOf(owner.getBirthday()));
            statementOwner.executeUpdate();
            ResultSet key = statementOwner.getGeneratedKeys();
            if (key.next()) {
                owner.setId(key.getInt(1));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteByEntity(Owner owner) {
        String SQL_QUERY_DELETE = "DELETE FROM owners WHERE id = ?";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementOwner = connection.prepareStatement(SQL_QUERY_DELETE)) {
            statementOwner.setInt(1, owner.getId());
            statementOwner.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Owner owner) {
        String SQL_QUERY_UPDATE = "UPDATE owners SET name = ?, birthday = ? WHERE id = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementOwner = connection.prepareStatement(SQL_QUERY_UPDATE)) {

            statementOwner.setString(1, owner.getName());
            statementOwner.setDate(2, Date.valueOf(owner.getBirthday()));
            statementOwner.setInt(3, owner.getId());
            statementOwner.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        String SQL_QUERY_DELETE = "DELETE FROM owners";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementOwner = connection.prepareStatement(SQL_QUERY_DELETE)) {
            statementOwner.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String SQL_QUERY_DELETE = MessageFormat.format("DELETE FROM owners WHERE id = {1}", id);
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementOwner = connection.prepareStatement(SQL_QUERY_DELETE)) {
            statementOwner.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Cat> getAllCatsByOwnerId(int id) {
        String SQL_QUERY_SELECT = "SELECT * FROM cats as c WHERE c.owner_id = ?";
        List<Cat> cats = null;

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statementOwner = connection.prepareStatement(SQL_QUERY_SELECT)) {

            statementOwner.setInt(1, id);
            ResultSet setCats = statementOwner.executeQuery();

            cats = new ArrayList<>();
            Cat cat;
            while (setCats.next()) {
                    String name = setCats.getString("name");
                    LocalDate date = setCats.getDate("birthday")
                            .toLocalDate();
                    String breed = setCats.getString("breed");
                    Cat.ColorType colorType = Cat.ColorType.valueOf(setCats.getString("color"));

                    Owner owner = getById(id);
                    cat = new Cat(name, date, breed, colorType, owner);
                    cats.add(cat);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cats;
    }
}
