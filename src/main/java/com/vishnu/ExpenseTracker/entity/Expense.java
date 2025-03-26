package com.vishnu.ExpenseTracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_name")
    @NotBlank(message = "Expense Name must not be null")
    @Size(min = 3 ,message = "Expense name must be at least 3 character")
    private String name;

    private String description;

    @Column(name = "expense_amount")
    @NotNull(message = "Expense amount should not to be null")
    private BigDecimal amount;

    @NotBlank(message = "Category should not be null")
    private String category;

    @NotNull(message = "Date should not to be null")
    private Date date;

    @Column(name = "created_at", nullable = false ,updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // This ensures user_id is always set
    private User user;
}