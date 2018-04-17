package com.company.carservice.jmx;

import com.company.carservice.entity.Employee;
import com.haulmont.cuba.core.*;
import com.haulmont.cuba.core.app.EmailerAPI;
import com.haulmont.cuba.core.app.EmailerConfig;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.EmailException;
import com.haulmont.cuba.security.app.Authenticated;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
                emailer.sendEmail(e.getEmail(), "Поздравляем с днём рождения!",
                        "Поздравляем вас с днём рождения, уважаемый " + e.getFirstName() +
                              "! \nЖелаем всего наилучшего в " + getAge(e) + "! \n" +
                              "С уважением, коллектив автосервиса " + getEmployeeCenter(e) + ".");
            } catch (EmailException e1) {
                e1.printStackTrace();
            }
        }
        return "Successful";
    }

    public String getEmployeeCenter(Employee employee){
        String result;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Query query = em.createQuery("select csc.name from carservice$CarServiceCenter csc " +
                    "join carservice$Employee emp " +
                    "where emp.center.id = csc.id and emp.id = :employee");
            query.setParameter("employee",employee.getId());
            result = String.valueOf(query.getFirstResult());
            tx.commit();
        }
        return result;
    }

    public String getAge(Employee employee){
        LocalDate date = employee.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        String age = String.valueOf(Period.between(date, now).getYears());
        char lastDigit = age.charAt(age.length() - 1);
        if (lastDigit == '1') {
            return "ваш " + age + " год";
        }
        if (lastDigit == '2' || lastDigit == '3' || lastDigit == '4')
            return "ваши " + age + " года";
        return "ваши " + age + " лет";
    }

}
