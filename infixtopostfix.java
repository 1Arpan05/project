//convert infix to postfix
import java.io.*;
class  Stack
{
	int top;
	char arr[];
	int cap;
	Stack()
	{
		top=-1;
		cap=10;
		arr=new char[cap];
	}
   void push(char ele)
	{
		top++;
		arr[top]=ele;
	}
	char pop()
	{
		char item=arr[top];
		//arr[top]=0;
		top=top-1;
		return item;
	}	
}
class IP
{
 Stack  st=new Stack();
  String toPost(String infix)
	{
	  infix="("+infix+")";
	  String postfix="";
	  for(int i=0;i<infix.length();i++)
		{
		  char ch,item;
		  ch=infix.charAt(i);
			if(isOperand(ch))
			{
                postfix=postfix+ch;
			}
			if(ch=='(')
			{
				st.push(ch);
			}
			item=st.pop();

			if(isOperator(ch))
			{
				 
					while((isOperator(item))&&(precdenece(item)>=precdenece(ch)))
					{
							postfix=postfix+item;
							item=st.pop();
					}
	                    st.push(item);
						st.push(ch);
				}
				else
				{
					st.push(item);
				}

			if(ch==')')
			{
				item=st.pop();
				while(item!='(')
				{
					postfix=postfix+item;
					item=st.pop();

				}
			}
			
		}
			return postfix;
		}
	public boolean isOperand(char c)
  {  
	if (c >= 'A' && c <= 'Z')
	  {
		return true;
	  }
	 else  if (c >= 'a' && c <= 'z')
	  {
		return true;
	  }
	   else  if (c >= '0' && c <= '9')
	  {
		return true;
	  }
	  else
	  {
		  return false;
	  }
  }
    	boolean isOperator(char ch)
		{
		    return (ch=='+' ||ch=='-' ||ch=='*' ||ch=='^' ||ch=='/');

		}
      int precdenece(char ch)
		{
                  if(ch=='^')
					  return 3;
				  else if(ch=='*'||ch=='/')
					  return 2;
				  else if(ch=='+'||ch=='-')
					  return 1;
				  else
					  return 0;
		}

	}

class Test
{
	public static void main(String arg[])throws Exception
	{
		IP obj=new IP();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter an infix expression ");
		String infix=br.readLine();
		System.out.println("infix :"+infix);
		System.out.println("postfix :"+obj.toPost(infix));
	
	}
}