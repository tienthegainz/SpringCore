package app.dao;

import app.model.Developer;

import java.util.List;

public interface DeveloperDAO {

    public List<Developer> findByPosition(String position);

    public List<Developer> loadDevelopers();

    public void addDeveloper(String name, Integer age, String position) throws Exception;

    public void throwException() throws Exception;

}
