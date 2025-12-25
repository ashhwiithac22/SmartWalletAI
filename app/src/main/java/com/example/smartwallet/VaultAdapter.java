package com.example.smartwallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwallet.models.Vault;

import java.util.List;
import java.util.Locale;

public class VaultAdapter extends RecyclerView.Adapter<VaultAdapter.ViewHolder> {

    private List<Vault> vaultList;

    public VaultAdapter(List<Vault> vaultList) {
        this.vaultList = vaultList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vault, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vault vault = vaultList.get(position);

        holder.tvVaultName.setText(vault.getName());
        holder.tvVaultBalance.setText(String.format(Locale.getDefault(), "₹%,.0f", vault.getBalance()));

        if (vault.getTarget() > 0) {
            int progress = (int) ((vault.getBalance() / vault.getTarget()) * 100);
            holder.progressBar.setProgress(Math.min(progress, 100));
            holder.tvProgress.setText(progress + "%");
        } else {
            holder.progressBar.setProgress(0);
            holder.tvProgress.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        return vaultList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVaultName, tvVaultBalance, tvProgress;
        ProgressBar progressBar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVaultName = itemView.findViewById(R.id.tvVaultName);
            tvVaultBalance = itemView.findViewById(R.id.tvVaultBalance);
            tvProgress = itemView.findViewById(R.id.tvProgress);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}