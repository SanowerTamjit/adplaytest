package com.santam.adplaytest.repository.mysql;

import com.santam.adplaytest.entity.mysql.DistrictstatsMysqlEntity;
import com.santam.adplaytest.entity.mysql.TotalstatsMysqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TotalstatsRepositoryMysql extends JpaRepository<TotalstatsMysqlEntity, Long> {
    @Query("select case when count(c)> 0 then true else false end from TotalstatsMysqlEntity c where c.updatedAt = :updatedat")
    boolean checkUpdatedExists(@Param("updatedat") Timestamp updatedat);

    @Override
    @Query("select e from TotalstatsMysqlEntity e order by e.updatedAt desc")
    List<TotalstatsMysqlEntity> findAll();
}