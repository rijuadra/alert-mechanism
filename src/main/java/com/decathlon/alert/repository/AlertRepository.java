package com.decathlon.alert.repository;

import com.decathlon.alert.bo.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends CrudRepository<Team,Integer> {
}
