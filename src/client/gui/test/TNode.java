package client.gui.test;

public class TNode {
    public TNode left,right;
    public int a;
    public TNode(){
    }
    public TNode(int a){
        this.a=a;
    }
    public void addLeft(TNode n){
        left=n;
    }
    public void addRight(TNode n){
        right=n;
    }
}
