package ru.lubich.bapi.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

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
    public MapperFactory getObject() {
        return new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
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