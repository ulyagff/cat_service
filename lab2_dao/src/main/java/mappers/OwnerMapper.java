package mappers;

import Entity.Cat;
import Entity.Owner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OwnerMapper {
    final String SQL_QUERY_GET_ALL = "SELECT * FROM owners";
    final String SQL_QUERY_GET_BY_ID = "SELECT * FROM OWNERS AS o WHERE o.id = #{id}";
    final String SQL_QUERY_SAVE = "INSERT INTO owners(name, birthday) VALUES(#{name}, #{birthday})";
    final String SQL_QUERY_DELETE = "DELETE FROM owners WHERE id = #{id}";
    final String SQL_QUERY_DELETE_ALL = "DELETE FROM owners";
    String SQL_QUERY_GET_CATS_BY_OWNER = "SELECT * FROM cats as c WHERE c.owner_id = #{id}";
    final String SQL_QUERY_UPDATE = "UPDATE owners SET name = #{name}, birthday = #{birthday} WHERE id = #{id}";

    @Select(SQL_QUERY_GET_ALL)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "cats", javaType = List.class,
                    column = "id", many = @Many(select = "getAllCatsByOwnerId"))
    })
    List<Owner> getAll();

    @Select(SQL_QUERY_GET_BY_ID)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "cats", javaType = List.class,
                    column = "id", many = @Many(select = "getAllCatsByOwnerId"))
    })
    Owner getById(int id);
    @Insert(SQL_QUERY_SAVE)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Owner owner);
    @Update(SQL_QUERY_UPDATE)
    void update(Owner owner);
    @Delete(SQL_QUERY_DELETE)
    void deleteByEntity(Owner owner);
    @Delete(SQL_QUERY_DELETE)
    void deleteById(int id);
    @Delete(SQL_QUERY_DELETE_ALL)
    void deleteAll();

    @Select(SQL_QUERY_GET_CATS_BY_OWNER)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "breed", column = "breed"),
            @Result(property = "color", column = "color"),


    })
    List<Cat> getAllCatsByOwnerId(int id);
}
