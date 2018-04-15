package com.company.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.DiscriminatorValue;

@NamePattern("%s|name")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "CARSERVICE_COMPANY")
@Entity(name = "carservice$Company")
public class Company extends Customer {
    private static final long serialVersionUID = -8714640807853116962L;

    @Column(name = "INN")
    protected String inn;

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getInn() {
        return inn;
    }


}