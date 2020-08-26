package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.models.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonService{
    //查询全部
    public List<Person> selectPersonByName();
    //删除
    int deleteById(@Param("id") int id);
    //添加
    int add(Person person);
}

