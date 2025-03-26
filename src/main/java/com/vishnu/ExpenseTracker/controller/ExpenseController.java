package com.vishnu.ExpenseTracker.controller;

import com.vishnu.ExpenseTracker.entity.Expense;
import com.vishnu.ExpenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Expense createExpense(@PathVariable("userId") Long userId, @Valid @RequestBody Expense expense) {
        return expenseService.createExpense(userId, expense);
    }

    @GetMapping
    public Page<Expense> getAllExpenses(@PathVariable("userId") Long userId,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return expenseService.getAllExpenses(userId, pageable);
    }


    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpenseById(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpenseDetails(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpenseDetails(id, expense);
    }

    @GetMapping("/category")
    public List<Expense> getAllExpenseByCategory(@PathVariable("userId") Long userId,
                                                 @RequestParam String category,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return expenseService.readByCategory(category, userId, pageable);
    }

    @GetMapping("/name")
    public List<Expense> getAllExpenseByName(@PathVariable("userId") Long userId,
                                             @RequestParam String keyword,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return expenseService.readByName(keyword, userId, pageable);
    }

    @GetMapping("/date")
    public List<Expense> getAllExpenseByDate(@PathVariable("userId") Long userId,
                                             @RequestParam(required = false) Date startDate,
                                             @RequestParam(required = false) Date endDate,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return expenseService.readByDate(startDate, endDate, userId, pageable);
    }
}
