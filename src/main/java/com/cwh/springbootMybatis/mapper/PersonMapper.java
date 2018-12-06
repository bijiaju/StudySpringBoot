package com.cwh.springbootMybatis.mapper;

import com.cwh.springbootMybatis.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Select("select * from person")
    List<Person> queryAll();
}
