package com.example.smartwallet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwallet.models.Vault;
import com.example.smartwallet.services.BankAPIService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView tvTotalBalance, tvAiInsight, tvAiStatus;
    private RecyclerView rvVaults;
    private VaultAdapter vaultAdapter;
    private List<Vault> vaultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();

        initializeViews();
        setupRecyclerView();
        loadSampleData();
        setupClickListeners();

        // Automatically fetch transactions on app start
        autoFetchTransactions();
    }

    private void initializeViews() {
        tvTotalBalance = findViewById(R.id.tvTotalBalance);
        tvAiInsight = findViewById(R.id.tvAiInsight);
        tvAiStatus = findViewById(R.id.tvAiStatus);
        rvVaults = findViewById(R.id.rvVaults);

        // Set initial AI status
        tvAiStatus.setText("🤖 AI is analyzing your financial patterns...");
        tvAiInsight.setText("• Loading your financial data...\n• AI will categorize transactions\n• Automatic vault allocation");
    }

    private void setupRecyclerView() {
        vaultAdapter = new VaultAdapter(vaultList);
        rvVaults.setLayoutManager(new LinearLayoutManager(this));
        rvVaults.setAdapter(vaultAdapter);
    }

    private void loadSampleData() {
        // Sample vault data
        vaultList.clear();
        vaultList.add(new Vault("1", "Emergency", 25000, 50000));
        vaultList.add(new Vault("2", "Rent", 15000, 15000));
        vaultList.add(new Vault("3", "Food", 6000, 10000));
        vaultList.add(new Vault("4", "Savings", 20000, 50000));
        vaultList.add(new Vault("5", "Lifestyle", 3000, 8000));

        vaultAdapter.notifyDataSetChanged();

        // Set sample balance
        tvTotalBalance.setText("₹85,420");
    }

    private void setupClickListeners() {
        Button btnAutoPay = findViewById(R.id.btnAutoPay);
        Button btnFetchTransactions = findViewById(R.id.btnFetchTransactions);

        btnAutoPay.setOnClickListener(v -> triggerAutoPayment());
        btnFetchTransactions.setOnClickListener(v -> fetchLatestTransactions());
    }

    private void autoFetchTransactions() {
        tvAiStatus.setText("🔄 Auto-fetching transactions...");

        // Simulate automatic transaction fetch
        new android.os.Handler().postDelayed(() -> {
            tvAiStatus.setText("✅ Transactions processed by AI!");
            tvAiInsight.setText("• 5 transactions categorized automatically\n• ₹2,500 allocated to Emergency vault\n• Rent payment scheduled for tomorrow");
        }, 2000);
    }

    private void fetchLatestTransactions() {
        autoFetchTransactions();
    }

    private void triggerAutoPayment() {
        // Create UPI Intent for Google Pay
        String upiId = "test@upi"; // Test UPI ID
        String name = "Smart Merchant";
        String amount = "1500";
        String note = "AI-validated payment";

        Uri uri = Uri.parse("upi://pay?pa=" + upiId +
                "&pn=" + name +
                "&am=" + amount +
                "&tn=" + note +
                "&cu=INR");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);

        // Check if any UPI app is available
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Show message that no UPI app is installed
            tvAiStatus.setText("⚠️ Install a UPI app (GPay, PhonePe, etc.)");
        }
    }
}