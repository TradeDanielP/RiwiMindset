package com.riwi.test.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.test.domain.entities.TestScore;

@Repository
public interface TestScoreRepository extends JpaRepository<TestScore, Integer> {

}
