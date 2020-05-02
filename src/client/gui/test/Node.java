package client.gui.test;

import java.util.ArrayList;

public class Node{
    public int a;
    public ArrayList<Node> children;
    public Node(){

    }
    public Node(int a){
        this.a=a;
        children=new ArrayList<>();
    }
    public void add(Node n){
        children.add(n);
    }

}