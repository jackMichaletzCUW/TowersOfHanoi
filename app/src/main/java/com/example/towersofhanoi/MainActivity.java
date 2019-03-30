package com.example.towersofhanoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private TextView disk1, disk2, disk3;
    private ViewGroup t1vg, t2vg, t3vg, placeholderVG;
    private Button t1btn, t2btn, t3btn;

    private TowerStack t1stack, t2stack, t3stack, placeholderStack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all GUI elements

        this.disk1 = this.findViewById(R.id.disk1);
        this.disk2 = this.findViewById(R.id.disk2);
        this.disk3 = this.findViewById(R.id.disk3);

        this.t1vg = this.findViewById(R.id.t1vg);
        this.t2vg = this.findViewById(R.id.t2vg);
        this.t3vg = this.findViewById(R.id.t3vg);
        this.placeholderVG = this.findViewById(R.id.placeholderVG);

        this.t1btn = this.findViewById(R.id.t1btn);
        this.t2btn = this.findViewById(R.id.t2btn);
        this.t3btn = this.findViewById(R.id.t3btn);

        // Set up the stacks that will actually hold tower information and correspond to the GUI columns
        t1stack = new TowerStack();
        t2stack = new TowerStack();
        t3stack = new TowerStack();

        placeholderStack = new TowerStack();

        // Set up the starting position of the game
        t1stack.push(new Tower(3));
        t1stack.push(new Tower(2));
        t1stack.push(new Tower(1));
    }

    public void t1btnPressed(View v)
    {

        if(this.moveTower(t1stack, t1vg) == 1)
        {
            this.checkWin();
        }
    }

    public void t2btnPressed(View v)
    {
        if(this.moveTower(t2stack, t2vg) == 1)
        {
            this.checkWin();
        }
    }

    public void t3btnPressed(View v)
    {
        if(this.moveTower(t3stack, t3vg) == 1)
        {
            this.checkWin();
        }
    }

    public void checkWin()
    {
        if(t3stack.getCount() == 3)
        {
            // The player has won the game
            Toast.makeText(this, "Great Success", Toast.LENGTH_LONG).show();
        }
    }

    public int moveTower(TowerStack ts, ViewGroup vg)
    {
        // Returns:
        //  0:  If placeholder was empty and column to pull from was not (tower moved to placeholder)
        //  1:  If placeholder was full and the tower inside it was able to be moved (tower moved from placeholder)
        //  2:  If placeholder was empty but the column we are pulling from was as well (no tower to move)
        //  3:  If placeholder was full but the tower inside it was unable to be moved (tower not moved)

        if(placeholderStack.isEmpty())
        {
            // If anything, we will be moving a tower INTO the placeholder

            if(!ts.isEmpty())
            {
                // Move tower from the selected column to the placeholder
                this.placeholderStack.push(ts.pop());

                // Modify GUI to show correct state
                View temp = vg.getChildAt(0);
                vg.removeViewAt(0);
                placeholderVG.addView(temp);
                return 0;
            }
            else
            {
                // Do nothing
                return 2;
            }
        }
        else
        {
            // If anything, we will be moving a tower OUT of the placeholder

            if(ts.isEmpty() || placeholderStack.peek().isSmaller(ts.peek()))
            {
                // Valid to move

                // Move tower from the placeholder to the selected column
                ts.push(placeholderStack.pop());

                // Modify GUI to show correct state
                View temp = placeholderVG.getChildAt(0);
                placeholderVG.removeViewAt(0);
                vg.addView(temp, 0);
                return 1;
            }
            else
            {
                // Not valid to move; do nothing
                return 3;
            }
        }
    }
}
