package com.riwi.test.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import com.riwi.test.domain.entities.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer>{

}
