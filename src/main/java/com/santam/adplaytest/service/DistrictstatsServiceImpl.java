package com.santam.adplaytest.service;

import com.santam.adplaytest.entity.mongodb.DistrictstatsMongoEntity;
import com.santam.adplaytest.entity.mongodb.TotalstatsMongoEntity;
import com.santam.adplaytest.entity.mysql.DistrictstatsMysqlEntity;
import com.santam.adplaytest.entity.mysql.TotalstatsMysqlEntity;
import com.santam.adplaytest.repository.mongodb.DistrictstatsRepositoryMongo;
import com.santam.adplaytest.repository.mysql.DistrictstatsRepositoryMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class DistrictstatsServiceImpl implements DistrictstatsService {


    @Autowired
    DistrictstatsRepositoryMongo distictstatsRepositoryMongo;
    @Autowired
    DistrictstatsRepositoryMysql districtstatsRepositoryMysql;

    //Mongodb Ops
    @Override
    public void addDistrictStatsMongo(List<DistrictstatsMongoEntity> districtstatsMongoEntity) {
        try {
            deleteAllDistrictStatsMongo();
        } catch (Exception e) {
        }
        distictstatsRepositoryMongo.saveAll(districtstatsMongoEntity);
    }

    @Override
    public List<DistrictstatsMongoEntity> getDistrictStatsMongo() {
        return distictstatsRepositoryMongo.findAll();
    }

    @Override
    public void deleteAllDistrictStatsMongo() {
        distictstatsRepositoryMongo.deleteAll();
    }


    //Mysql Ops
    @Override
    public void addDistrictStatsMysql(DistrictstatsMysqlEntity districtstatsMysqlEntity) {
        districtstatsRepositoryMysql.save(districtstatsMysqlEntity);
    }

    @Override
    public List<DistrictstatsMysqlEntity> getDistrictStatsMysql() {
        return districtstatsRepositoryMysql.findAll();
    }

    @Override
    public void deleteAllDistrictStatsMysql() {
        districtstatsRepositoryMysql.deleteAll();
    }

    @Override
    public boolean isExists(Timestamp updatedAt, int districtID) {
        return districtstatsRepositoryMysql.checkUpdatedExists(updatedAt, districtID);
    }


}