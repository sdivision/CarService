package com.company.carservice.web.repair;

import com.company.carservice.entity.CarServiceCenter;
import com.company.carservice.entity.Employee;
import com.company.carservice.entity.Repair;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.UUID;

public class RepairEdit extends AbstractEditor<Repair> {
    @Inject
    private CollectionDatasource<CarServiceCenter, UUID> carServiceCentersDs;
    @Inject
    private CollectionDatasource<Employee, UUID> employeesDs;
    @Named("fieldGroup.employee")
    private LookupPickerField employeeField;

    @Override
    public void init(Map<String, Object> params) {
        carServiceCentersDs.addItemChangeListener(e -> employeeField.setValue(employeesDs.getItem()));
    }
}