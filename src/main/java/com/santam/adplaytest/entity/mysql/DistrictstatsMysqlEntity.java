package com.santam.adplaytest.entity.mysql;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "districtstats", schema = "covidbd", catalog = "")
public class DistrictstatsMysqlEntity {
    private int id;
    private String district;
    private Integer districtId;
    private Integer tCount;
    private Integer pCount;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "district_id")
    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @Basic
    @Column(name = "t_count")
    public Integer gettCount() {
        return tCount;
    }

    public void settCount(Integer tCount) {
        this.tCount = tCount;
    }

    @Basic
    @Column(name = "p_count")
    public Integer getpCount() {
        return pCount;
    }

    public void setpCount(Integer pCount) {
        this.pCount = pCount;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictstatsMysqlEntity that = (DistrictstatsMysqlEntity) o;
        return id == that.id &&
                Objects.equals(district, that.district) &&
                Objects.equals(districtId, that.districtId) &&
                Objects.equals(tCount, that.tCount) &&
                Objects.equals(pCount, that.pCount) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, district, districtId, tCount, pCount, updatedAt);
    }
}
