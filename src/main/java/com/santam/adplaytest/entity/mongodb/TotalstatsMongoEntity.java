package com.santam.adplaytest.entity.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Document(collection = "totalstats")
public class TotalstatsMongoEntity {
//    private int id;
    private Integer dLast24;
    private Integer dTotal;
    private Integer pLast24;
    private Integer pTotal;
    private Integer rLast24;
    private Integer rTotal;
    private Integer tLast24;
    private Integer tTotal;
    private Date updatedAt;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public Integer getdLast24() {
        return dLast24;
    }

    public void setdLast24(Integer dLast24) {
        this.dLast24 = dLast24;
    }

    public Integer getdTotal() {
        return dTotal;
    }

    public void setdTotal(Integer dTotal) {
        this.dTotal = dTotal;
    }

    public Integer getpLast24() {
        return pLast24;
    }

    public void setpLast24(Integer pLast24) {
        this.pLast24 = pLast24;
    }

    public Integer getpTotal() {
        return pTotal;
    }

    public void setpTotal(Integer pTotal) {
        this.pTotal = pTotal;
    }

    public Integer getrLast24() {
        return rLast24;
    }

    public void setrLast24(Integer rLast24) {
        this.rLast24 = rLast24;
    }

    public Integer getrTotal() {
        return rTotal;
    }

    public void setrTotal(Integer rTotal) {
        this.rTotal = rTotal;
    }

    public Integer gettLast24() {
        return tLast24;
    }

    public void settLast24(Integer tLast24) {
        this.tLast24 = tLast24;
    }

    public Integer gettTotal() {
        return tTotal;
    }

    public void settTotal(Integer tTotal) {
        this.tTotal = tTotal;
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
        TotalstatsMongoEntity that = (TotalstatsMongoEntity) o;
        return
                Objects.equals(dLast24, that.dLast24) &&
                Objects.equals(dTotal, that.dTotal) &&
                Objects.equals(pLast24, that.pLast24) &&
                Objects.equals(pTotal, that.pTotal) &&
                Objects.equals(rLast24, that.rLast24) &&
                Objects.equals(rTotal, that.rTotal) &&
                Objects.equals(tLast24, that.tLast24) &&
                Objects.equals(tTotal, that.tTotal) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dLast24, dTotal, pLast24, pTotal, rLast24, rTotal, tLast24, tTotal, updatedAt);
    }
}
