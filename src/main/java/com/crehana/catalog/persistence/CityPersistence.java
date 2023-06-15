package com.crehana.catalog.persistence;

import com.crehana.catalog.constants.PersistenceConstants;
import com.crehana.catalog.dto.CityDTO;
import com.crehana.catalog.exception.CrehanaException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CityPersistence {

    public void create(CityDTO city) {

        if(Objects.isNull(city)) {
            throw new CrehanaException(PersistenceConstants.CITY_IS_NULL);
        }

        List<CityDTO> cities = readFile();
        final Optional<CityDTO> existCity = existCity(city.getCode(), cities);

        if(existCity.isPresent()) {
            throw new CrehanaException(PersistenceConstants.CITY_WITH_THE_SAME_INFORMATION);
        } else {
            cities.add(city);
            saveFile(cities);
        }
    }

    public void update(CityDTO city) {

        if(Objects.isNull(city)) {
            throw new CrehanaException(PersistenceConstants.CITY_IS_NULL);
        }

        List<CityDTO> cities = readFile();
        final Optional<CityDTO> existCity = existCity(city.getCode(), cities);

        if(existCity.isPresent()) {
            existCity.get().setName(city.getName());
            saveFile(cities);
        } else {
            throw new CrehanaException(PersistenceConstants.CITY_NOT_EXIST);
        }
    }

    public List<CityDTO> getAll() {
        return readFile();
    }

    public Optional<CityDTO> existCity(String code) {
        return existCity(code, null);
    }

    private Optional<CityDTO> existCity(String code, List<CityDTO> cities) {

        if(StringUtils.isBlank(code)) {
            throw new CrehanaException(PersistenceConstants.CODE_IS_NULL);
        }

        if(Objects.isNull(cities)) {
            cities = readFile();
        }

        return cities
                .stream()
                .filter(cityDTO -> cityDTO.getCode().equals(code))
                .findAny();
    }

    private void saveFile(List<CityDTO> cities) {

        if(Objects.isNull(cities)) {
            throw new CrehanaException(PersistenceConstants.LIST_OF_CITIES_IS_NULL);
        }

        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a writer
            URL resource = getClass().getClassLoader().getResource("cities.json");
            if(Objects.isNull(resource)) {
                throw new CrehanaException(PersistenceConstants.FILE_NOT_EXISTS);
            }

            Writer writer = Files.newBufferedWriter(Paths.get(resource.getPath()), StandardOpenOption.TRUNCATE_EXISTING);

            // create a converter
            Type cityListType = new TypeToken<List<CityDTO>>(){}.getType();

            // convert JSON string to User object
            String jsonToSave = gson.toJson(cities, cityListType);

            writer.write(jsonToSave);

            // close writer
            writer.close();
        } catch (Exception ex) {
            throw new CrehanaException(ex.getMessage());
        }
    }


    private List<CityDTO> readFile() {
        List<CityDTO> cities = null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            URL resource = getClass().getClassLoader().getResource("cities.json");
            if(Objects.isNull(resource)) {
                throw new CrehanaException(PersistenceConstants.FILE_NOT_EXISTS);
            }

            Reader reader = Files.newBufferedReader(Paths.get(resource.getPath()));

            // create a converter
            Type cityListType = new TypeToken<List<CityDTO>>(){}.getType();

            // convert JSON string to User object
            cities = gson.fromJson(reader, cityListType);

            // close reader
            reader.close();
        } catch (Exception ex) {
            throw new CrehanaException(ex.getMessage());
        }

        return cities;
    }
}
