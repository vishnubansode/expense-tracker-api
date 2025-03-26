package com.vishnu.ExpenseTracker.repository;

import com.vishnu.ExpenseTracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    //Select * from tbl where category=?
    Page<Expense> findByCategory(String category, Pageable pageable);
    //select * from tbl where name like=%keyword%
    Page<Expense> findByNameContaining(String keyword,Pageable pageable);

    //select * from tbl where date between startdate and enddate
    List<Expense> findByDateBetween(Date startDate, Date endDate, Pageable pageable);

    List<Expense> findByCategoryAndUserId(String category, Long userId, Pageable pageable);

    List<Expense> findByNameContainingAndUserId(String keyword, Long userId, Pageable pageable);

    List<Expense> findByDateBetweenAndUserId(Date startDate, Date endDate, Long userId, Pageable pageable);
    Page<Expense> findByUserId(Long userId, Pageable pageable);



}
