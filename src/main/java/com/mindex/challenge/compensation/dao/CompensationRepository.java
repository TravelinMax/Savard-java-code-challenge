package com.mindex.challenge.compensation.dao;

import com.mindex.challenge.compensation.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    List<Compensation> findByEmployeeEmployeeId(String employeeId);
}
