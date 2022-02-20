package gp.taskcrud.service;

import gp.taskcrud.exception.DeveloperNotFoundException;
import gp.taskcrud.model.Developer;
import gp.taskcrud.repository.DeveloperRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService{

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Transactional
    @Override
    public Developer saveOrUpdate(Developer developer) {
        if (developer.getId() != null) {
            Developer updatedDeveloper = findDeveloperById(developer.getId());
            BeanUtils.copyProperties(developer, updatedDeveloper);
            return developerRepository.save(updatedDeveloper);
        } else {
            return developerRepository.save(developer);
        }
    }

    @Override
    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (developerRepository.existsById(id)) {
            developerRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }


    @Override
    public Developer findDeveloperById(Long id)  {
        return developerRepository.findDeveloperById(id).orElseThrow(()-> new DeveloperNotFoundException());
    }
}
