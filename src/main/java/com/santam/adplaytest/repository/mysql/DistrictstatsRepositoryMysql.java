package com.santam.adplaytest.repository.mysql;

import com.santam.adplaytest.entity.mysql.DistrictstatsMysqlEntity;
import com.santam.adplaytest.entity.mysql.TotalstatsMysqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DistrictstatsRepositoryMysql extends JpaRepository<DistrictstatsMysqlEntity, Long> {
    @Query("select case when count(c)> 0 then true else false end from DistrictstatsMysqlEntity c where c.districtId = :districtID AND c.updatedAt = :updatedat")
    boolean checkUpdatedExists(@Param("updatedat") Timestamp updatedat, @Param("districtID") int districtID);

    @Override
    @Query("select e from DistrictstatsMysqlEntity e order by e.updatedAt desc")
    List<DistrictstatsMysqlEntity> findAll();
}