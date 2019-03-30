package com.example.towersofhanoi;

public class Node<Type>
{
    private Type payload;
    private Node nextNode;

    public Node(Type payload)
    {
        this.payload = payload;
        this.nextNode = null;
    }

    public Type getPayload()
    {
        return payload;
    }

    public Node getNextNode()
    {
        return nextNode;
    }

    public void setNextNode(Node nextNode)
    {
        this.nextNode = nextNode;
    }
}
