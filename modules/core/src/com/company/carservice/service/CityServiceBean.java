package com.company.carservice.service;

import com.company.carservice.entity.City;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(CityService.NAME)
public class CityServiceBean implements CityService {
    @Inject
    private Persistence persistence;

    private String query = "select c from carservice$City c where c.defaultCity = true";

    @Override
    public City getDefaultCity() {

        List<City> cities = persistence.callInTransaction(e -> {
            TypedQuery<City> typedQuery = e.createQuery(query, City.class);
            return typedQuery.getResultList();
        });
        if (cities.isEmpty()) {
            return null;
        }

        return cities.get(0);
    }

    @Override
    public void resetDefaultCity() {

        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            List<City> cities = persistence.callInTransaction(e -> {
                TypedQuery<City> typedQuery = e.createQuery(query, City.class);
                return typedQuery.getResultList();
            });
            for (City c : cities) {
                c.setDefaultCity(false);
                em.merge(c);
            }
            tx.commit();
        }
    }
}