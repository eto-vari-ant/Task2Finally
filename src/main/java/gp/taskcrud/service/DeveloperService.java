package gp.taskcrud.service;

import gp.taskcrud.exception.DeveloperNotFoundException;
import gp.taskcrud.model.Developer;

import java.util.List;

public interface DeveloperService {
    Developer saveOrUpdate(Developer developer) throws DeveloperNotFoundException;
    List<Developer> findAll();
    boolean deleteById(Long id) throws DeveloperNotFoundException;
    Developer findDeveloperById(Long id) throws DeveloperNotFoundException;
}
