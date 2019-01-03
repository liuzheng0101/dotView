package com.example.mature.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SearchView.SearchViewCallback {
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.searchView);
        searchView.setSearchViewCallback(this);
    }

    @Override
    public void onClickListener(View v) {
        this.finish();
    }

    @Override
    public void searchClick(String keywords) {
        String result=keywords;
    }
    @Override
    public void keywoidsEmpty() {
        Toast.makeText(this,"关键词不能为空",Toast.LENGTH_SHORT).show();
    }
}
