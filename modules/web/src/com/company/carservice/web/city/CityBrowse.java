package com.company.carservice.web.city;

import com.company.carservice.entity.City;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.UUID;

public class CityBrowse extends AbstractLookup {

    @Named("citiesTable.create")
    private CreateAction citiesTableCreate;
    @Named("citiesTable.edit")
    private EditAction citiesTableEdit;
    @Inject
    private GroupDatasource<City, UUID> citiesDs;

    @Override
    public void init(Map<String, Object> params) {
        citiesTableCreate.setAfterCommitHandler(entity -> citiesDs.refresh());
        citiesTableEdit.setAfterCommitHandler(entity -> citiesDs.refresh());
    }
}