package com.company.carservice.web.city;

import com.company.carservice.entity.City;
import com.company.carservice.service.CityService;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import java.util.List;


public class CityEdit extends AbstractEditor<City> {
    @Inject
    private CityService cityService;
    @Inject
    private Datasource<City> cityDs;
    @Inject
    private DataManager dataManager;

    @Override
    protected boolean preCommit() {
        if (cityDs.getItem().getDefaultCity() != null){
            resetCity();
            //cityService.resetDefaultCity();
            return true;
        }
        else return true;
    }
    private void resetCity(){
        String query = "select c from carservice$City c where c.defaultCity = true";
        LoadContext<City> loadContext = LoadContext.create(City.class)
                .setQuery(LoadContext.createQuery(query));
        List<City> cityList = dataManager.loadList(loadContext);
        CommitContext commitContext = new CommitContext();
        for (City c : cityList){
            c.setDefaultCity(false);
            commitContext.addInstanceToCommit(c);
        }
        dataManager.commit(commitContext);
    }
}