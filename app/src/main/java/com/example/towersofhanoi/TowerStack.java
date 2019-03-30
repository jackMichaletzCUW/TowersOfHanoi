package com.example.towersofhanoi;

public class TowerStack
{
    private Node<Tower> top;
    private int count;

    public TowerStack()
    {
        this.top = null;
        this.count = 0;
    }

    public int getCount()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return (this.count == 0);
    }

    public void push(Tower payload)
    {
        Node newNode = new Node<Tower>(payload);
        newNode.setNextNode(top);
        top = newNode;
        count++;
    }

    public Tower pop()
    {
        Tower returnVal = top.getPayload();
        top = top.getNextNode();
        count--;
        return returnVal;
    }

    public Tower peek()
    {
        return top.getPayload();
    }

    @Override
    public String toString()
    {
        if(this.top == null)
        {
            return "EMPTY STACK";
        }
        else
        {
            String output = "";
            Node currentNode = top;
            do
            {
                output += (currentNode.getPayload() + "\n");
                currentNode = currentNode.getNextNode();
            } while(currentNode != null);

            return output;
        }
    }

    public void display()
    {
        System.out.printf(this.toString());
    }

}
