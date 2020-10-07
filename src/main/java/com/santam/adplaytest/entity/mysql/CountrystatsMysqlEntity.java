package com.santam.adplaytest.entity.mysql;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "countrystats", schema = "covidbd", catalog = "")
public class CountrystatsMysqlEntity {
    private long id;
    private String country;
    private String countryCode;
    private Long confirmed;
    private Long dead;
    private Long recovered;
    private Timestamp updatedAt;
    private Double gLat;
    private Double gLong;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "country_code")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Basic
    @Column(name = "confirmed")
    public Long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Long confirmed) {
        this.confirmed = confirmed;
    }

    @Basic
    @Column(name = "dead")
    public Long getDead() {
        return dead;
    }

    public void setDead(Long dead) {
        this.dead = dead;
    }

    @Basic
    @Column(name = "recovered")
    public Long getRecovered() {
        return recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
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
        CountrystatsMysqlEntity that = (CountrystatsMysqlEntity) o;
        return id == that.id &&
                Objects.equals(country, that.country) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(confirmed, that.confirmed) &&
                Objects.equals(dead, that.dead) &&
                Objects.equals(recovered, that.recovered) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, countryCode, confirmed, dead, recovered, updatedAt);
    }

    @Basic
    @Column(name = "g_lat")
    public Double getgLat() {
        return gLat;
    }

    public void setgLat(Double gLat) {
        this.gLat = gLat;
    }

    @Basic
    @Column(name = "g_long")
    public Double getgLong() {
        return gLong;
    }

    public void setgLong(Double gLong) {
        this.gLong = gLong;
    }
}
