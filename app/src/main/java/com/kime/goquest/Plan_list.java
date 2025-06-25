package com.kime.goquest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Plan_list extends AppCompatActivity implements PlanAdapter.OnEditClickListener, PlanAdapter.OnDeleteClickListener {

    private CalendarManager calendarManager;
    private PlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);

        calendarManager = new CalendarManager(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPlans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<PlanItem> items = calendarManager.getAllEventsOnlyGo();
        adapter = new PlanAdapter(items, this, this);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAddPlan).setOnClickListener(v ->
                startActivity(new Intent(Plan_list.this, AddPlan.class)));
    }

    @Override
    public void onEditClick(PlanItem item) {
        Intent intent = new Intent(this, AddPlan.class);
        intent.putExtra("event_id", item.getId());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("start_time", item.getStartTime());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(PlanItem item) {
        calendarManager.deleteEvent(item.getId());
        refreshList();
    }

    private void refreshList() {
        List<PlanItem> items = calendarManager.getAllEventsOnlyGo();
        adapter.updateList(items);
    }
    public void toBack(View view){
        startActivity(new Intent(this, Mainlayout.class));
        finish();
    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshList(); // обновляем список при возврате
    }
}