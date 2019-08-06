package com.bee.springboot.mapper;

import com.bee.springboot.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Select("select * from person")
    List<Person> queryAll();

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.DEFAULT		)
    public int addPersonInfo(Person user);
}
