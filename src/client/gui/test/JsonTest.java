package client.gui.test;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    public static void main(String[] arg)throws Exception{
//        List<Node> list=new ArrayList<>();
//        Node n1=new Node(1);
//        Node n2=new Node(2);
//        Node n3=new Node(3);
//        Node n4=new Node(4);
//        Node n5=new Node(5);
//        n1.add(n2);
//        list.add(n1);
//
//        String jsonstr= JSON.toJSONString(list);
//        System.out.println(jsonstr);
//        List<Node> nlist=JSON.parseArray(jsonstr,Node.class);
//          Node t1=nlist.get(0);
//          System.out.println(t1.a+" "+t1.children);
//          Constructor c=Node.class.getConstructor(int.class);
//          Node t=(Node)c.newInstance(3);
//          System.out.println(c);
//        Node t2=t1.children.get(0);
//        Node t3=t1.children.get(1);
//        Node t4=t2.children.get(0);
//        Node t5=t2.children.get(1);
//        System.out.println(""+t1.a+t2.a+t3.a+t4.a+t5.a);

          TNode n1=new TNode(1);
          TNode n2=new TNode(2);
          TNode n3=new TNode(3);
          TNode n4=new TNode(4);
          n1.addLeft(n2);
          n1.addRight(n3);
          n2.addRight(n4);
          String jsonstr=JSON.toJSONString(n1);
          System.out.println(jsonstr);
          TNode t1=JSON.parseObject(jsonstr,TNode.class);
          TNode t2=t1.left;
          TNode t3=t1.right;
          TNode t4=t2.right;
          System.out.println(""+t1.a+t2.a+t3.a+t4.a);
    }

}
