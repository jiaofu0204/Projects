package edu.northeastern.cs5200.mapper;



import edu.northeastern.cs5200.models.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


import java.util.List;

@Mapper
@Component(value = "personMapper")
public interface PersonMapper{
    //查询全部
    //在这里我利用了sql优化，之查出相应的字段，避免出现*号
    @Select("select * from person")
    List<Person> selectPersonByName();

    //删除 按id删除
    @Delete("delete from person where id = #{id}")
    int deleteById(@Param("id") int id);
    //添加
    //注意字段的对应
    @Insert({ "insert into person(id, username, password, address, phone, dob, type) values(#{id}, #{username}, #{password}, #{address}, #{phone}, #{dob}, #{type})" })

    int add(Person person);


}

