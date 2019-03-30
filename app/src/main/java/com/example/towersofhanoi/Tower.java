package com.example.towersofhanoi;

public class Tower
{
    private int size;

    public Tower(int size)
    {
        this.size = size;
    }

    public int getSize()
    {
        return size;
    }

    public boolean isSmaller(Tower comparisonTower)
    {
        if(comparisonTower.getSize() > this.size)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
