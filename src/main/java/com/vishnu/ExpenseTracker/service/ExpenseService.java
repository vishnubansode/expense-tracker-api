package com.vishnu.ExpenseTracker.service;

import com.vishnu.ExpenseTracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface ExpenseService {
    Page<Expense> getAllExpenses(Long userId, Pageable pageable);


    List<Expense> readByCategory(String category, Long userId, Pageable pageable);  // Added userId

    List<Expense> readByName(String keyword, Long userId, Pageable pageable);  // Added userId

    List<Expense> readByDate(Date startDate, Date endDate, Long userId, Pageable pageable);  // Added userId

    Expense getExpenseById(Long id);

    String deleteExpenseById(Long id);

    Expense createExpense(Long userId, Expense expense);

    Expense updateExpenseDetails(Long id, Expense expense);
}
