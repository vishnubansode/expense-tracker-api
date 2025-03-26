package com.vishnu.ExpenseTracker.service;

import com.vishnu.ExpenseTracker.entity.Expense;
import com.vishnu.ExpenseTracker.entity.User;
import com.vishnu.ExpenseTracker.exceptions.ResourceNotFoundException;
import com.vishnu.ExpenseTracker.repository.ExpenseRepository;
import com.vishnu.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Expense> getAllExpenses(Long userId, Pageable pageable) {
        return expenseRepository.findByUserId(userId, pageable);
    }


    @Override
    public List<Expense> readByCategory(String category, Long userId, Pageable pageable) {
        validateUser(userId);
        return expenseRepository.findByCategoryAndUserId(category, userId, pageable);
    }

    @Override
    public List<Expense> readByName(String keyword, Long userId, Pageable pageable) {
        validateUser(userId);
        return expenseRepository.findByNameContainingAndUserId(keyword, userId, pageable);
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Long userId, Pageable pageable) {
        validateUser(userId);

        if (startDate == null) {
            startDate = Date.valueOf("2000-01-01"); // Default past date
        }
        if (endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }

        return expenseRepository.findByDateBetweenAndUserId(startDate, endDate, userId, pageable);
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found for id: " + id));
    }

    @Override
    public String deleteExpenseById(Long id) {
        getExpenseById(id); // Ensures existence
        expenseRepository.deleteById(id);
        return "Expense Deleted Successfully!";
    }

    @Override
    public Expense createExpense(Long userId, Expense expense) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + userId));

        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        return expenseRepository.save(existingExpense);
    }

    private void validateUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found for id: " + userId));
    }
}
