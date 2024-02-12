import java.util.*;

public class TextCompression {
     HashMap<Character, String> encoder;
  HashMap<String, Character> decoder;
  private class Node implements Comparable<Node>{
    Character ch;
    int cost;
    Node left;
    Node right;

    public Node(Character ch,int cost){
      this.ch=ch;
      this.cost=cost;
      this.left=null;
      this.right=null;
    }
    @Override
    public int compareTo(Node n){
      return this.cost-n.cost;
    }
  }
  
  public TextCompression(String str) throws Exception{
    HashMap<Character,Integer> fmap=new HashMap<>();
    for(int i =0;i<str.length();i++){
      char currchar=str.charAt(i);
      if(fmap.containsKey(currchar)){
        int f=fmap.get(currchar);
        f+=1;
        fmap.put(currchar, f);
      }else{
        fmap.put(currchar, 1);
      }
    }

    PriorityQueue<Node>pq=new PriorityQueue<>();
    Set<Map.Entry<Character,Integer>> entryset=fmap.entrySet();
    for(Map.Entry<Character,Integer> entry:entryset){
      Node n=new Node(entry.getKey(),entry.getValue());
      pq.add(n);
    }
    while(pq.size()!=1){
      Node first=pq.remove();
      Node second=pq.remove();
      Node third=new Node('\0',first.cost+second.cost);
      third.left=first;
      third.right=second;
      pq.add(third);
    }
    Node ft=pq.remove();
    this.encoder=new HashMap();
    this.decoder=new HashMap();
    encoderdecorder(ft,"");
  }
  public void encoderdecorder(Node node,String str){
    if(node==null){
      return;
    }
    if(node.left==null && node.right==null){
      this.encoder.put(node.ch, str);
      this.decoder.put(str, node.ch);
      return;
    }
    encoderdecorder(node.left, str+"0");
    encoderdecorder(node.right, str+"1");
  }
  
  public String encode(String str){
    String ans="";
    for(int i=0;i<str.length();i++){
      ans=ans+encoder.get(str.charAt(i));
    }
    return ans;
  }

  public String decode(String str){
    String key="";
    String ans="";
    for(int i=0;i<str.length();i++){
      key=key+str.charAt(i);
      if(decoder.containsKey(key)){
        ans=ans+decoder.get(key);
        key="";
      }
    }
    return ans;
  }

  public static void main(String[] args) throws Exception {
    String str="abbccda";
    TextCompression hf=new TextCompression(str);
    String e=hf.encode(str);
    System.out.println(e);
    String d=hf.decode(e);
    System.out.println(d);
  }
}
