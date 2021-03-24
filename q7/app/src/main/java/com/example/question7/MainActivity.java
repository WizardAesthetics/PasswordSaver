package com.example.question7;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.graphics.Point;
import android.view.Gravity;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.Toast;
import android.widget.Button;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{
    private DatabaseManager dbManager;
    private float total;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbManager = new DatabaseManager(this);

        total = 0;

        updateView();
    }

    protected void onStart()
    {
        super.onStart();

        updateView();
    }

    private void updateView()
    {
        LinkedList<Password> list = dbManager.all();

        if (list.size() > 0)
        {
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int buttonWidth = size.x/2;
            int DP = (int)(getResources().getDisplayMetrics().density);
            int rows = (list.size() + 1)/2;
            int columns = 2;

            GridLayout grid = new GridLayout(this);
            grid.setRowCount(rows);
            grid.setColumnCount(columns);
            ScrollView.LayoutParams params = new ScrollView.LayoutParams(0, 0);
            params.width = columns*buttonWidth;
            params.height = rows*buttonWidth;
            grid.setLayoutParams(params);

            Button[] buttons = new Button[list.size()];
            for (int i = 0; i < list.size(); i++)
            {
                Password password = list.get(i);

                buttons[i] = new Button(this);
                buttons[i].setText(password.getName() + "\n" + password.getPassword());
                buttons[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                buttons[i].setTextColor(Color.parseColor("#000000"));
                buttons[i].setBackgroundColor(Color.parseColor("#009900"));
                buttons[i].setPadding(10*DP, 10*DP, 10*DP, 10*DP);
                buttons[i].setGravity(Gravity.CENTER);

                GridLayout.LayoutParams params1 = new GridLayout.LayoutParams();
                params1.width = buttonWidth;
                params1.height = buttonWidth;
                params1.rowSpec = GridLayout.spec(i/columns, 1);
                params1.columnSpec = GridLayout.spec(i%columns, 1);
                params1.topMargin = params1.bottomMargin = 1;
                params1.leftMargin = params1.rightMargin = 1;
                buttons[i].setLayoutParams(params1);


                grid.addView(buttons[i]);
            }

            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
            scroll.addView(grid);
        }
        else
        {
            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
        }

    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.add)
        {
            Intent insertActivity = new Intent(this, InsertActivity.class);
            startActivity(insertActivity);
        }
        else if (id == R.id.delete)
        {
            Intent deleteActivity = new Intent(this, DeleteActivity.class);
            startActivity(deleteActivity);
        }
        else if (id == R.id.update)
        {
            Intent updateActivity = new Intent(this, UpdateActivity.class);
            startActivity(updateActivity);
        }


        return super.onOptionsItemSelected(item);
    }
}
