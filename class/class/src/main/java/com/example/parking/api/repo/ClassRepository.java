package com.example.parking.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClassRepository extends JpaRepository<Class , Long> {
}
