package com.company.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.DiscriminatorValue;

@NamePattern("%s|name")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "CARSERVICE_INDIVIDUAL")
@Entity(name = "carservice$Individual")
public class Individual extends Customer {
    private static final long serialVersionUID = -3204717424625856182L;

    @Column(name = "PASSPORT_NO")
    protected String passportNo;

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPassportNo() {
        return passportNo;
    }


}