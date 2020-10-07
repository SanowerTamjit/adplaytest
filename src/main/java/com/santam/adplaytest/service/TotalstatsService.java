package com.santam.adplaytest.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.santam.adplaytest.entity.mongodb.TotalstatsMongoEntity;
import com.santam.adplaytest.entity.mysql.TotalstatsMysqlEntity;

public interface TotalstatsService {
 void addTotalStatsMongo(TotalstatsMongoEntity totalstatsEntity);
 List<TotalstatsMongoEntity> getTotalStatsMongo();
 void deleteAllTotalStatsMongo();


 void addTotalStatsMysql(TotalstatsMysqlEntity totalstatsMysqlEntity);
 List<TotalstatsMysqlEntity> getTotalStatsMysql();
 void deleteAllTotalStatsMysql();
 boolean isExists(Timestamp updatedAt);
}