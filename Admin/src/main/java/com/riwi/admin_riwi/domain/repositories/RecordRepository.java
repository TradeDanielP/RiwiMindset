package com.riwi.admin_riwi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.riwi.admin_riwi.domain.entities.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{

}
