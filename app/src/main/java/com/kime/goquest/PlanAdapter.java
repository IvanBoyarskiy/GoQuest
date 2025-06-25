package com.kime.goquest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {

    private List<PlanItem> planItems;
    private OnEditClickListener onEditClickListener;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnEditClickListener {
        void onEditClick(PlanItem item);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(PlanItem item);
    }

    public PlanAdapter(List<PlanItem> planItems, OnEditClickListener onEditClickListener, OnDeleteClickListener onDeleteClickListener) {
        this.planItems = planItems;
        this.onEditClickListener = onEditClickListener;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        PlanItem item = planItems.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewTime.setText("Дата: " + Utils.formatTime(item.getStartTime())); // только startTime

        holder.btnEdit.setOnClickListener(v -> onEditClickListener.onEditClick(item));
        holder.btnDelete.setOnClickListener(v -> onDeleteClickListener.onDeleteClick(item));
    }

    public void updateList(List<PlanItem> newList) {
        this.planItems.clear();
        this.planItems.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return planItems.size();
    }

    static class PlanViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewTime;
        Button btnEdit, btnDelete;

        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}