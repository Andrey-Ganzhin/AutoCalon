package com.example.avtocalon;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;




public class MainActivity extends AppCompatActivity {

    private ArrayList<String> cars = new ArrayList<>();
    private ArrayList<String> selectedCars = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView carListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carListView = findViewById(R.id.carsList);
        boolean b = Collections.addAll(cars, "vas2107", "vas2109", "vas2114");
        carListView = findViewById(R.id.carsList);
        adapter = new ArrayAdapter<String>(this, R.layout.car_item, R.id.textView, cars) {
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ImageView imageView = view.findViewById(R.id.imageView);
                final TextView textView3 = view.findViewById(R.id.textView3);
                Button plusButton = view.findViewById(R.id.plusButton);
                Button minusButton = view.findViewById(R.id.minusButton);
                imageView.setImageResource(R.drawable.vas2107);
                CheckBox checkBox = view.findViewById(R.id.checkBox);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String user = adapter.getItem(position);
                        if (isChecked) {
                            selectedCars.add(user);
                            Log.d("ItemChecked", "Выбран: " + user);
                        } else {
                            selectedCars.remove(user);
                            Log.d("ItemUnchecked", "Снят выбор: " + user);
                        }
                    }
                });
                checkBox.setChecked(carListView.isItemChecked(position));
                plusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentValue = Integer.parseInt(textView3.getText().toString());
                        currentValue++;
                        textView3.setText(String.valueOf(currentValue));
                    }
                });
                minusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentValue = Integer.parseInt(textView3.getText().toString());
                        if (currentValue > 0) {
                            currentValue--;
                            textView3.setText(String.valueOf(currentValue));
                        }
                    }
                });
                return view;
            }
        };
        carListView.setAdapter(adapter);
    }
    public void add(View view) {
        EditText userName = findViewById(R.id.userName);
        String user = userName.getText().toString();
        if(!user.isEmpty()){
            adapter.add(user);
            userName.setText("");
            adapter.notifyDataSetChanged();
        }

    }
    public void remove(View view) {

        for (int i = 0; i < selectedCars.size(); i++) {
            adapter.remove(selectedCars.get(i));
        }
        selectedCars.clear();

        for (int i = 0; i < carListView.getCount(); i++) {
            carListView.setItemChecked(i, false);
        }
        adapter.notifyDataSetChanged();
    }
}