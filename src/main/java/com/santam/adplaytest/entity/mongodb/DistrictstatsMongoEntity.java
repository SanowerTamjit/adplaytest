package com.santam.adplaytest.entity.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Document(collection = "districstats")
public class DistrictstatsMongoEntity {
//    private int id;
    private String district;
    private Integer districtId;
    private Integer tCount;
    private Integer pCount;
    private Date updatedAt;
//
//    @Id
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer gettCount() {
        return tCount;
    }

    public void settCount(Integer tCount) {
        this.tCount = tCount;
    }

    public Integer getpCount() {
        return pCount;
    }

    public void setpCount(Integer pCount) {
        this.pCount = pCount;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictstatsMongoEntity that = (DistrictstatsMongoEntity) o;
        return
                Objects.equals(district, that.district) &&
                Objects.equals(districtId, that.districtId) &&
                Objects.equals(tCount, that.tCount) &&
                Objects.equals(pCount, that.pCount) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(district, districtId, tCount, pCount, updatedAt);
    }
}
