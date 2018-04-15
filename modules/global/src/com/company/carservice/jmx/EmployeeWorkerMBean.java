package com.company.carservice.jmx;

import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Management interface of the {@link com.company.carservice.jmx.EmployeeWorkerMBean} MBean.
 *
 */

@ManagedResource(description = "Perform happy birthday greetings")
public interface EmployeeWorkerMBean {

    String getFromAddress();
    void setFromAddress(String address);

    String sendGreetings();
}
