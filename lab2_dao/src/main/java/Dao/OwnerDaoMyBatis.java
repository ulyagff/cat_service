package Dao;

import Entity.Cat;
import Entity.Owner;
import mappers.OwnerMapper;
import org.apache.ibatis.session.SqlSession;
import utils.MyBatisSession;

import java.util.List;

public class OwnerDaoMyBatis implements OwnerDao{
    @Override
    public List<Owner> getAll() {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(OwnerMapper.class);
        var resultList = ownerMapper.getAll();
        MyBatisSession.closeSession(session);
        return resultList;
    }

    @Override
    public Owner getById(int id) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(OwnerMapper.class);
        var resultOwner = ownerMapper.getById(id);
        MyBatisSession.closeSession(session);
        return resultOwner;
    }

    @Override
    public void save(Owner owner) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.save(owner);
        MyBatisSession.closeSession(session);
    }

    @Override
    public void deleteByEntity(Owner owner) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.deleteByEntity(owner);
        MyBatisSession.closeSession(session);
    }

    @Override
    public void update(Owner owner) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.update(owner);
        MyBatisSession.closeSession(session);
    }

    @Override
    public void deleteAll() {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.deleteAll();
        MyBatisSession.closeSession(session);
    }

    @Override
    public void deleteById(int id) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.deleteById(id);
        MyBatisSession.closeSession(session);
    }

    @Override
    public List<Cat> getAllCatsByOwnerId(int id) {
        SqlSession session = MyBatisSession.getSession();
        var ownerMapper = session.getMapper(OwnerMapper.class);
        var resultList = ownerMapper.getAllCatsByOwnerId(id);
        MyBatisSession.closeSession(session);
        return resultList;
    }
}
