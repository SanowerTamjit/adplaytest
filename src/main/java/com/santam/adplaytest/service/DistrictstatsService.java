package com.santam.adplaytest.service;

import com.santam.adplaytest.entity.mongodb.DistrictstatsMongoEntity;
import com.santam.adplaytest.entity.mongodb.DistrictstatsMongoEntity;
import com.santam.adplaytest.entity.mysql.DistrictstatsMysqlEntity;
import com.santam.adplaytest.entity.mysql.DistrictstatsMysqlEntity;

import java.sql.Timestamp;
import java.util.List;

public interface DistrictstatsService {
    //Mongo
    void addDistrictStatsMongo(List<DistrictstatsMongoEntity> districtstatsMongoEntity);

    List<DistrictstatsMongoEntity> getDistrictStatsMongo();

    void deleteAllDistrictStatsMongo();

    //Mysql
    void addDistrictStatsMysql(DistrictstatsMysqlEntity districtstatsMysqlEntity);

    List<DistrictstatsMysqlEntity> getDistrictStatsMysql();

    void deleteAllDistrictStatsMysql();

    boolean isExists(Timestamp updatedAt, int districtID);
}