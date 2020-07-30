package ru.lubich.bapi.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;
import ru.lubich.bapi.model.Office;
import ru.lubich.bapi.model.User;
import ru.lubich.bapi.view.UserView;
import ru.lubich.bapi.view.filter.OfficeFilter;
import ru.lubich.bapi.view.filter.UserFilter;

/**
 * Фабрика MapperFactory для создания кастомных маперов
 */
@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    /**
     * Создание маппера
     * @return  маппера
     */
    @Override
    public MapperFactory getObject() throws Exception {
        MapperFactory mapperFactory =
                new DefaultMapperFactory.
                        Builder()
                        .constructorResolverStrategy(null)
                        .build();

        mapperFactory.classMap(OfficeFilter.class, Office.class)
                .field("orgId", "organization.id")
                .byDefault()
                .register();

        mapperFactory.classMap(UserFilter.class, User.class)
                .mapNulls(true).mapNullsInReverse(true)
                .field("officeId", "office.id")
                .field("docCode", "doc.docType.code")
                .field("docName", "doc.docType.name")
                .field("docNumber", "doc.number")
                .field("docDate", "doc.date")
                .field("citizenshipCode", "country.code")
                .field("citizenshipName", "country.name")
                .byDefault()
                .register();

        mapperFactory.classMap(UserView.class, User.class)
                .field("officeId", "office.id")
                .field("docCode", "doc.docType.code")
                .field("docName", "doc.docType.name")
                .field("docNumber", "doc.number")
                .field("docDate", "doc.date")
                .field("citizenshipCode", "country.code")
                .field("citizenshipName", "country.name")
                .byDefault()
                .register();

        return mapperFactory;
    }

    /**
     * Получить типа объекта
     * @return тип объекта
     */
    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    /**
     * Проверка, является ли значение Singleton
     * @return логическое значение
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}