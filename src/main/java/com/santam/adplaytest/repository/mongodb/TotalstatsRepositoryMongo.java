package com.santam.adplaytest.repository.mongodb;

import com.santam.adplaytest.entity.mongodb.TotalstatsMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalstatsRepositoryMongo extends MongoRepository<TotalstatsMongoEntity,Long> {

}