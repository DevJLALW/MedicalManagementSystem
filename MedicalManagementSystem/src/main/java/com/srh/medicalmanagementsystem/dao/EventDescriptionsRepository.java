package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.EventDescriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDescriptionsRepository extends JpaRepository<EventDescriptions, Integer> {
}
