package com.kime.goquest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Comic1_pr extends AppCompatActivity {

    private int[] comicImages = {
            R.drawable.c1_pr
    };

    private ImageView imageView;
    private Button btnPrev, btnNext, btnStart;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic1);

        imageView = findViewById(R.id.imageViewComic);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnStart = findViewById(R.id.btnStart);

        updateImage();

        btnPrev.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateImage();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (currentIndex < comicImages.length - 1) {
                currentIndex++;
                updateImage();
            }
        });

        btnStart.setOnClickListener(v -> {
            toTask(13);
        });

    }
    public void toTask(int num){
        Intent intent = new Intent(this, Task.class);
        intent.putExtra("task_number", num);
        startActivity(intent);
    }

    private void updateImage() {
        imageView.setImageResource(comicImages[currentIndex]);

        // Показываем/скрываем кнопки
        btnPrev.setVisibility(currentIndex == 0 ? View.GONE : View.VISIBLE);
        btnNext.setVisibility(currentIndex == comicImages.length - 1 ? View.GONE : View.VISIBLE);
        btnStart.setVisibility(currentIndex == comicImages.length - 1 ? View.VISIBLE : View.GONE);
    }
}