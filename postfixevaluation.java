// postfix evaluation
//----------------------
import java.util.Scanner;
/*import java.io.*;*/
public class postfixevaluation{
  class Stack{
	int top;
	int arr[];
	int cap;
	Stack(){
      top=-1;
      cap=20;
      arr=new int[cap];
	}
	void push(int ele){
		top++;
		arr[top]=ele;
	}
	int pop(){
		int item=arr[top];
		arr[top]=0;
		top=top-1;
		return item;
	}
}
class Post_Evaluate{

	Stack st=new Stack();
	int postEval(String postfix){
       int i;
       int item1,item2,r=0;
       for (i=0;i<postfix.length() ;i++ ) {
       	char ch;
       	ch=postfix.charAt(i);
        if(ch==' ')
        {
          continue;
        }
       	   if(isOperand(ch))
            {
              if(ch>='0'&&ch<='9')
              {
                    int su=0;
                    while(isOperand(ch))
                    {
                        if (ch>='0'&&ch<='9') 
                        {
                               su=su*10+(int)ch-48;
                          }
                          i++;
                          ch=postfix.charAt(i);  
                    }

                    i--;
                    st.push(su);
              }
          
            else{
            Scanner sc=new Scanner(System.in);
            System.out.println("enter the value of "+ch);
            //int r=(int)ch;
            r=sc.nextInt();
            st.push(r);
             }

       	   }
       	   if (isOperator(ch)) {
       	   	  item2=st.pop();
       	   	  item1=st.pop();
       	   	  if (ch=='+') {
       	   	  	st.push(item1+item2);
       	   	  }
       	   	  else if (ch=='-') {
       	   	    st.push(item1-item2);	
       	   	  }
       	   	  else if (ch=='*') {
       	   	  	st.push(item1*item2);
       	   	  }
       	   	  else if (ch=='/') {
       	   	  	st.push(item1/item2);
       	   	  }
       	   	 else{
       	   	 	System.out.println("invalid operand");
       	   	    System.exit(0);
                }
       	   }
       }
      return st.pop();
	}
  boolean isOperand(char ch){
      if ((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')||(ch>='0'&&ch<='9')) {
        return true;
      }
      else
        return false;
    }
    boolean isOperator(char ch){
      if (ch=='+' || ch=='-' || ch=='*' || ch=='/') {
        return true;
      }
      else
        return false;
    }
  }

	public static void main(String[] args) throws Exception{
		Post_Evaluate ob= new Post_Evaluate();
		Scanner br = new Scanner(System.in);
		/*BufferedReader br= new BufferedReader(new InputStreamReader (System.in));*/
        System.out.println("enter the postfix expression");
       /* String infix= br.readLine();*/
        String postfix= br.nextLine();
        System.out.println("postfix evaluated result = "+ob.postEval(postfix));
	}
}

