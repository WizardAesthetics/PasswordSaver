package com.example.question7;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import java.util.LinkedList;

public class DeleteActivity extends AppCompatActivity
{
    private DatabaseManager dbManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        dbManager = new DatabaseManager(this);

        updateView();
    }

    private void updateView()
    {
        LinkedList<Password> list = dbManager.all();

        if (list.size() > 0)
        {
            RadioGroup group = new RadioGroup(this);
            ScrollView.LayoutParams params = new ScrollView.LayoutParams(0, 0);
            params.width = ScrollView.LayoutParams.WRAP_CONTENT;
            params.height = ScrollView.LayoutParams.WRAP_CONTENT;
            group.setLayoutParams(params);

            RadioButton[] buttons = new RadioButton[list.size()];

            for (int i = 0; i < list.size(); i++)
            {
                Password password = list.get(i);

                buttons[i] = new RadioButton(this);
                buttons[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                buttons[i].setText(password.getName().toUpperCase() + " " + password.getPassword());

                RadioButtonHandler temp = new RadioButtonHandler(password.getName());
                buttons[i].setOnClickListener(temp);

                group.addView(buttons[i]);
            }

            ScrollView scroll = (ScrollView) findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
            scroll.addView(group);
        }
        else
        {
            ScrollView scroll = (ScrollView) findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
        }
    }

    private class RadioButtonHandler implements View.OnClickListener
    {
        private String name;

        public RadioButtonHandler(String name)
        {
            this.name = name;
        }

        public void onClick(View view)
        {
            dbManager.delete(name);

            updateView();
        }
    }

    public void back(View view)
    {
        finish();
    }
}
