package com.example.Java_Jpa.repository;

import com.example.Java_Jpa.domain.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SpringDataJpaHardwareRepository extends JpaRepository<Hardware, Integer>, JpaSpecificationExecutor<Hardware> {
    List<Hardware> findByName(String name);
//    getAllArticles()
}
