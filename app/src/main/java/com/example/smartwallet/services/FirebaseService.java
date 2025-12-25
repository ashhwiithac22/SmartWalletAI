package com.example.smartwallet.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseService {

    public static String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user != null ? user.getUid() : null;
    }

    public static void logPayment(String payee, double amount, String note, String type) {
        // For now, just log to console
        System.out.println("Payment logged: " + payee + " - " + amount);
    }

    public static void updateVaultBalance(String vaultName, double amount) {
        // For now, just log to console
        System.out.println("Vault updated: " + vaultName + " - " + amount);
    }
}