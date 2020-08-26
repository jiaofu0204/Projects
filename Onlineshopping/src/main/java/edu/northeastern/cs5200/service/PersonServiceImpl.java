package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.mapper.*;
import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.service.PersonService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonMapper personMapper;


    @Override
    public List<Person> selectPersonByName() {
        return personMapper.selectPersonByName();
    }

    @Override
    public int deleteById(int id) {
        return personMapper.deleteById(id);
    }

    @Override
    public int add(Person person) {
        return personMapper.add(person);
    }
}

