package com.bee.springboot.mapper;

import com.bee.springboot.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Select("select * from person")
    List<Person> queryAll();
}
