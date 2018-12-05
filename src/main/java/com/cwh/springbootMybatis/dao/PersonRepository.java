package com.cwh.springbootMybatis.dao;

import com.cwh.springbootMybatis.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PersonRepository extends JpaRepository<Person,Long> {

}
