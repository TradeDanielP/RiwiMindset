package com.riwi.admin_riwi.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.riwi.admin_riwi.domain.entities.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{
            @Query("SELECT p FROM record p WHERE p.coder._id = :coder_id")
            List<Record> findRecordbyCoder(@Param("coder_id")String coder_id);
}
