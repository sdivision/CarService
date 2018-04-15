package com.company.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|description")
@Table(name = "CARSERVICE_REPAIR")
@Entity(name = "carservice$Repair")
public class Repair extends StandardEntity {
    private static final long serialVersionUID = -2704024133519763457L;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CENTER_ID")
    protected CarServiceCenter center;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    protected Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }


    public void setCenter(CarServiceCenter center) {
        this.center = center;
    }

    public CarServiceCenter getCenter() {
        return center;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}