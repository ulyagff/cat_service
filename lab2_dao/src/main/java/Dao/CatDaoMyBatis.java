package Dao;

import Entity.Cat;
import mappers.CatMapper;
import mappers.OwnerMapper;
import org.apache.ibatis.session.SqlSession;
import utils.MyBatisSession;

import java.util.List;

public class CatDaoMyBatis implements CatDao {
    @Override
    public List<Cat> getAll() {
        SqlSession session = MyBatisSession.getSession();
        var catMapper = session.getMapper(CatMapper.class);
        var resultList = catMapper.getAll();
        MyBatisSession.closeSession(session);
        return resultList;
    }

    @Override
    public Cat getById(int id) {
        SqlSession session = MyBatisSession.getSession();
        var catMapper = session.getMapper(CatMapper.class);
        var resultCat = catMapper.getById(id);
        MyBatisSession.closeSession(session);
        return resultCat;
    }

    @Override
    public void save(Cat cat) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(CatMapper.class);
        ownerMapper.save(cat);
        MyBatisSession.closeSession(session);
    }

    @Override
    public void deleteByEntity(Cat cat) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(CatMapper.class);
        ownerMapper.deleteByEntity(cat);
        MyBatisSession.closeSession(session);
    }

    @Override
    public void update(Cat cat) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(CatMapper.class);
        ownerMapper.update(cat);
        MyBatisSession.closeSession(session);
    }

    @Override
    public void deleteAll() {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(CatMapper.class);
        ownerMapper.deleteAll();
        MyBatisSession.closeSession(session);
    }

    @Override
    public void deleteById(int id) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(CatMapper.class);
        ownerMapper.deleteById(id);
        MyBatisSession.closeSession(session);
    }
}
