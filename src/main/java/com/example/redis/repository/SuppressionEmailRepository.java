package com.example.redis.repository;

import com.example.redis.model.SuppressionEmail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppressionEmailRepository extends CrudRepository<SuppressionEmail, String> {
}
