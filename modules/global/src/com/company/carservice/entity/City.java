package com.company.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "CARSERVICE_CITY")
@Entity(name = "carservice$City")
public class City extends StandardEntity {
    private static final long serialVersionUID = -5775640058674963835L;

    @Column(name = "DEFAULT_CITY")
    protected Boolean defaultCity;

    @Column(name = "NAME", unique = true)
    protected String name;

    @Column(name = "CODE", length = 25)
    protected String code;

    public void setDefaultCity(Boolean defaultCity) {
        this.defaultCity = defaultCity;
    }

    public Boolean getDefaultCity() {
        return defaultCity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}