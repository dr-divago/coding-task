package com.matteo.coding.task.codingtask.repository;

import com.matteo.coding.task.codingtask.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
}
