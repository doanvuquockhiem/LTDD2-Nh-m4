package tdc.edu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<Sach> data = new ArrayList<>();
    EditText txtMa, txtTen;
    Button btnNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAdapter();
        setEvent();
    }

    void setAdapter() {
        txtMa = (EditText) findViewById(R.id.txtMa);
        txtTen = (EditText) findViewById(R.id.txtTen);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        recyclerView = findViewById(R.id.recycleView);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, data, R.layout.listview_item_row);
        recyclerView.setAdapter(adapter);
    }

    void setEvent() {
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data.add(new Sach(R.drawable.colorbook, txtMa.getText().toString(), txtTen.getText().toString()));
                adapter.notifyDataSetChanged();
                txtMa.setText("");
                txtTen.setText("");
                txtMa.requestFocus();
            }
        });
    }
}
