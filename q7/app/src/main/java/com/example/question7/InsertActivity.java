package com.example.question7;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity
{
    private DatabaseManager dbManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        dbManager = new DatabaseManager(this);
    }

    public void add(View v)
    {
        EditText nameEditText = (EditText)findViewById(R.id.inputName);
        String name = nameEditText.getText().toString();

        EditText priceEditText = (EditText)findViewById(R.id.inputPrice);
        String priceString = priceEditText.getText().toString();

        Password password = new Password(name, priceString);
        dbManager.insert(password);
    }

    public void back(View v)
    {
        finish();
    }
}
