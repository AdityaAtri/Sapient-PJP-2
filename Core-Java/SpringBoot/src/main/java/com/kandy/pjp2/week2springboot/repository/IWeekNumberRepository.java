package com.kandy.pjp2.week2springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kandy.pjp2.week2springboot.model.WeekNumber;

@Repository
public interface IWeekNumberRepository extends JpaRepository<WeekNumber, Long> {

}
