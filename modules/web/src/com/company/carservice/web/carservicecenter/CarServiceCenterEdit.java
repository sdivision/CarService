package com.company.carservice.web.carservicecenter;

import com.company.carservice.entity.CarServiceCenter;
import com.company.carservice.entity.Customer;
import com.company.carservice.service.CityService;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.global.MessageTools;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.util.UUID;

public class CarServiceCenterEdit extends AbstractEditor<CarServiceCenter> {

    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private MessageTools messageTools;
    @Inject
    private Metadata metadata;
    @Inject
    private CityService cityService;
    @Inject
    private CollectionDatasource<Customer, UUID> customerDs;
    @Inject
    private TabSheet tabSheet;
    @Inject
    private UserSession userSession;

    @Override
    protected void initNewItem(CarServiceCenter item) {
        item.setCity(cityService.getDefaultCity());
        item.setCreator(userSession.getUser());
    }

    @Override
    protected void postInit() {
        String defaultTabName = tabSheet.getTab("customersTab").getCaption();
        customerDs.addCollectionChangeListener(e ->
                tabSheet.getTab("customersTab").setCaption(defaultTabName + " (" + customerDs.size() + ")"));
    }

    public Component generateDtypeCell(Customer entity) {
        if (entity == null){
            return null;
        }
        MetaClass metaClass = metadata.getClass(entity.getClass());
        String className = messageTools.getEntityCaption(metaClass);

        Label label = componentsFactory.createComponent(Label.class);
        label.setValue(className);

        return label;
    }
}