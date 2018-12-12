package com.bee.springboot.dao;

import com.bee.springboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PersonRepository extends JpaRepository<Person,Long> {

}
