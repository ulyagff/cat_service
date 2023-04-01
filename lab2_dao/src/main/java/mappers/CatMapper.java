package mappers;

import Entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CatMapper {
    final String SQL_QUERY_GET_ALL = "SELECT * FROM cats";
    final String SQL_QUERY_GET_BY_ID = "SELECT * FROM cats AS c WHERE c.id = #{id}";
    final String SQL_QUERY_SAVE = "INSERT INTO cats(name, birthday, breed, color, owner_id) VALUES(#{name}, #{birthday}, #{breed}, #{color}, #{owner.id})";
    final String SQL_QUERY_DELETE = "DELETE FROM cats WHERE id = #{id}";
    final String SQL_QUERY_DELETE_ALL = "DELETE FROM cats";
    final String SQL_QUERY_UPDATE = "UPDATE cats SET name = #{name}, birthday = #{birthday}, breed = #{breed}, color = #{color}, owner_id = #{owner.getId()} WHERE id = #{id}";


    @Select(SQL_QUERY_GET_ALL)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "breed", column = "breed"),
            @Result(property = "color", column = "color"),
            @Result(property = "owner", jdbcType = JdbcType.INTEGER,
                    column = "owner_id", javaType = Owner.class,
                    one = @One(select = "mapper.OwnerMapper.getById")
            )
    })
    List<Cat> getAll();

    @Select(SQL_QUERY_GET_BY_ID)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "breed", column = "breed"),
            @Result(property = "color", column = "color"),
            @Result(property = "owner", jdbcType = JdbcType.INTEGER,
                    column = "owner_id", javaType = Owner.class,
                    one = @One(select = "mapper.OwnerMapper.getById")
            )
    })
    Cat getById(int id);
    @Insert(SQL_QUERY_SAVE)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Cat cat);
    @Update(SQL_QUERY_UPDATE)
    void update(Cat cat);
    @Delete(SQL_QUERY_DELETE)
    void deleteByEntity(Cat cat);
    @Delete(SQL_QUERY_DELETE)
    void deleteById(int id);
    @Delete(SQL_QUERY_DELETE_ALL)
    void deleteAll();
}
