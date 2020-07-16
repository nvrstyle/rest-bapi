package ru.lubich.bapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lubich.bapi.dao.CountryDao;
import ru.lubich.bapi.model.Country;
import ru.lubich.bapi.service.CountryService;
import ru.lubich.bapi.view.CountryView;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис, предоставляющий методы для получения справочной информации о странах
 */
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countriesDao;

    /**
     * Конструктор
     *
     * @param countryDao DAO
     */
    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countriesDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryView> list() {
        List<Country> list = countriesDao.list();
        return list.stream().map(mapCountry()).collect(Collectors.toList());
    }

    private Function<Country, CountryView> mapCountry() {
        return c -> {
            CountryView view = new CountryView();
            view.code = c.getCode();
            view.name = c.getName();
            return view;
        };
    }
}
