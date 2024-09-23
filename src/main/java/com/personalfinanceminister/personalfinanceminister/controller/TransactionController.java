package com.personalfinanceminister.personalfinanceminister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.personalfinanceminister.personalfinanceminister.modal.Transaction;
import com.personalfinanceminister.personalfinanceminister.service.TransactionService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);

        double totalIncome = transactions.stream()
                .filter(t -> "income".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
        
        double totalExpenses = transactions.stream()
                .filter(t -> "expense".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = totalIncome - totalExpenses;

        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("balance", balance);

        return "transactions";
    }


    @GetMapping("/transaction/new")
    public String showAddTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "add-transaction";
    }

    @PostMapping("/transaction/add")
    public String addTransaction(@ModelAttribute Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/transaction/edit/{id}")
    public String editTransaction(@PathVariable("id") String id, Model model) {
        Transaction transaction = transactionService.findById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = transaction.getDate().format(formatter);
        transaction.setFormattedDate(formattedDate); // Assuming you have a setter for this in Transaction model
        model.addAttribute("transaction", transaction);
        return "edit-transaction";
    }

    @PostMapping("/transaction/update/{id}")
    public String updateTransaction(@PathVariable("id") String id, @ModelAttribute Transaction transaction) {
        transaction.setId(id); // Set the ID for updating
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/transaction/delete/{id}")
    public String deleteTransaction(@PathVariable("id") String id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }
}