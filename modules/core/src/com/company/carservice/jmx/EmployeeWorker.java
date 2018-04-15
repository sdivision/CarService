package com.company.carservice.jmx;

import com.company.carservice.entity.Employee;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.app.EmailerAPI;
import com.haulmont.cuba.core.app.EmailerConfig;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.EmailException;
import com.haulmont.cuba.security.app.Authenticated;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component("carservice_EmployeeWorkerMBean")
public class EmployeeWorker implements EmployeeWorkerMBean {

    @Inject
    protected EmailerAPI emailer;

    @Inject
    protected Persistence persistence;

    protected EmailerConfig config;

    @Inject
    public void setConfiguration(Configuration configuration) {
        this.config = configuration.getConfig(EmailerConfig.class);
    }

    @Override
    public String getFromAddress() {
        return config.getFromAddress();
    }
    @Authenticated
    @Override
    public void setFromAddress(String address) {
        if (address != null) {
            config.setFromAddress(address);
        }
    }

    @Authenticated
    @Override
    public String sendGreetings() {

        String nativeQuery = "SELECT * FROM CARSERVICE_EMPLOYEE " +
                "WHERE DATE_PART('day', BIRTH_DATE) = DATE_PART('day', CURRENT_DATE) " +
                "AND DATE_PART('month', BIRTH_DATE) = DATE_PART('month', CURRENT_DATE)";

        List<Employee> employees = persistence.callInTransaction(em -> {
            TypedQuery<Employee> typedQuery = em.createNativeQuery(nativeQuery, Employee.class);

            return typedQuery.getResultList();
        });
        for (Employee e : employees){
            try {
                emailer.sendEmail(e.getEmail(), "Greetings", "Поздравляем вас с днём рождения, уважаемый "
                        + e.getFirstName());
            } catch (EmailException e1) {
                e1.printStackTrace();
            }
        }
        return "Successful";
    }

}
