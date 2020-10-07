package com.santam.adplaytest.service;

import com.santam.adplaytest.entity.mongodb.TotalstatsMongoEntity;
import com.santam.adplaytest.entity.mysql.TotalstatsMysqlEntity;
import com.santam.adplaytest.repository.mongodb.TotalstatsRepositoryMongo;
import com.santam.adplaytest.repository.mysql.TotalstatsRepositoryMysql;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TotalstatsServiceImpl implements TotalstatsService {


    @Autowired
    TotalstatsRepositoryMongo totalstatsRepositoryMongo;
    @Autowired
    TotalstatsRepositoryMysql totalstatsRepositoryMysql;

    //Mongodb Ops
    @Override
    public void addTotalStatsMongo(TotalstatsMongoEntity totalstatsEntity) {
        try{
            deleteAllTotalStatsMongo();
        }catch (Exception e){}
        totalstatsRepositoryMongo.save(totalstatsEntity);
    }

    @Override
    public List<TotalstatsMongoEntity> getTotalStatsMongo() {
        return totalstatsRepositoryMongo.findAll();
    }


    @Override
    public void deleteAllTotalStatsMongo() {
        totalstatsRepositoryMongo.deleteAll();

    }

    //Mysql Ops
    @Override
    public void addTotalStatsMysql(TotalstatsMysqlEntity totalstatsMysqlEntity) {
        totalstatsRepositoryMysql.save(totalstatsMysqlEntity);
    }

    @Override
    public boolean isExists(Timestamp updatedAt) {
        return totalstatsRepositoryMysql.checkUpdatedExists(updatedAt);
    }

    @Override
    public List<TotalstatsMysqlEntity> getTotalStatsMysql() {
        return totalstatsRepositoryMysql.findAll();
    }

    @Override
    public void deleteAllTotalStatsMysql() {
        totalstatsRepositoryMysql.deleteAll();
    }


}