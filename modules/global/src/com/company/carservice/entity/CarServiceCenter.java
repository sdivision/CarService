package com.company.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.NamePattern;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.security.entity.User;

@NamePattern("%s|name")
@Table(name = "CARSERVICE_CAR_SERVICE_CENTER")
@Entity(name = "carservice$CarServiceCenter")
public class CarServiceCenter extends StandardEntity {
    private static final long serialVersionUID = -3322109071793040170L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "PHONE")
    protected String phone;

    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID")
    protected City city;

    @Column(name = "ADDRESS")
    protected String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID")
    protected User creator;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "center")
    protected List<Repair> repair;

    @JoinTable(name = "CARSERVICE_CENTER_CUSTOMER_LINK",
        joinColumns = @JoinColumn(name = "CAR_SERVICE_CENTER_ID"),
        inverseJoinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    @ManyToMany
    protected List<Customer> customer;

    @OneToMany(mappedBy = "center")
    protected List<Employee> employees;

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getCreator() {
        return creator;
    }


    public void setRepair(List<Repair> repair) {
        this.repair = repair;
    }

    public List<Repair> getRepair() {
        return repair;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomer() {
        return customer;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}