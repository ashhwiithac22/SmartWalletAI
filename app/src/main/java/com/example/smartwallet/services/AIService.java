package com.example.smartwallet.services;

import android.content.Context;

import com.example.smartwallet.models.Transaction;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AIService {

    public interface AICallback {
        void onSuccess(Map<String, Object> result);
        void onError(String error);
    }

    public interface PaymentCallback {
        void onPaymentDue(String payee, double amount, String description);
        void onNoPaymentsDue();
    }

    public void processTransactionsWithAI(List<Transaction> transactions, AICallback callback) {
        // Simulate AI processing
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate API call delay

                Map<String, Object> result = simulateAIAnalysis(transactions);

                // Run on UI thread
                android.os.Handler mainHandler = new android.os.Handler(android.os.Looper.getMainLooper());
                mainHandler.post(() -> callback.onSuccess(result));

            } catch (Exception e) {
                android.os.Handler mainHandler = new android.os.Handler(android.os.Looper.getMainLooper());
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        }).start();
    }

    private Map<String, Object> simulateAIAnalysis(List<Transaction> transactions) {
        Map<String, Object> result = new HashMap<>();
        Map<String, String> categories = new HashMap<>();
        Map<String, Double> allocations = new HashMap<>();

        // Simulate AI categorization
        for (Transaction t : transactions) {
            String desc = t.getDescription().toLowerCase();
            if (desc.contains("rent") || desc.contains("bill") || desc.contains("grocery")) {
                categories.put(t.getId(), "Necessary");
            } else if (desc.contains("movie") || desc.contains("restaurant") || desc.contains("shopping")) {
                categories.put(t.getId(), "Discretionary");
            } else {
                categories.put(t.getId(), "Review");
            }
        }

        // Simulate vault allocations
        allocations.put("Emergency", 2500.0);
        allocations.put("Rent", 15000.0);
        allocations.put("Food", 6000.0);
        allocations.put("Savings", 5000.0);
        allocations.put("Lifestyle", 3000.0);

        result.put("categories", categories);
        result.put("allocations", allocations);
        result.put("insight", "• " + transactions.size() + " transactions categorized automatically\n• ₹2,500 allocated to Emergency vault\n• Rent payment scheduled for tomorrow");
        result.put("predictions", "Upcoming: Rent (₹15,000), Electricity (₹1,200)");
        result.put("total_allocated", 31500.0);

        return result;
    }

    public void checkScheduledPayments(PaymentCallback callback) {
        // Simulate payment check
        new android.os.Handler().postDelayed(() -> {
            // No payments due for now
            callback.onNoPaymentsDue();
        }, 1000);
    }
}