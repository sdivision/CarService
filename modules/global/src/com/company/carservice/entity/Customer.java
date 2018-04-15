package com.company.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import org.hibernate.validator.constraints.Email;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorColumn;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "CARSERVICE_CUSTOMER")
@Entity(name = "carservice$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = -7608840236888915985L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "PHONE")
    protected String phone;

    @Email
    @Column(name = "EMAIL")
    protected String email;

    @JoinTable(name = "CARSERVICE_CENTER_CUSTOMER_LINK",
        joinColumns = @JoinColumn(name = "CUSTOMER_ID"),
        inverseJoinColumns = @JoinColumn(name = "CAR_SERVICE_CENTER_ID"))
    @ManyToMany
    protected List<CarServiceCenter> center;

    public void setCenter(List<CarServiceCenter> center) {
        this.center = center;
    }

    public List<CarServiceCenter> getCenter() {
        return center;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }



}