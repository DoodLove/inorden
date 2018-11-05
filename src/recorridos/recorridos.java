package recorridos;

	import java.util.*;
	class nodo
	{
	    public char dato;
	    public nodo izq;
	    public nodo der;
	 
	    public nodo(char x)
	    {
	        dato = x;
	    }
	 
	    public void displayNode()
	    {
	        System.out.print(dato);
	    }
	}
	 
	class pilaNodo
	{
	    private nodo[] a;
	    private int    top, m;
	 
	    public pilaNodo(int max)
	    {
	        m = max;
	        a = new nodo[m];
	        top = -1;
	    }
	 
	    public void push(nodo key)
	    {
	        a[++top] = key;
	    }
	 
	    public nodo pop()
	    {
	        return (a[top--]);
	    }
	 
	    public boolean isEmpty()
	    {
	        return (top == -1);
	    }
	}
	 
	class pilaChar
	{
	    private char[] a;
	    private int    top, m;
	 
	    public pilaChar(int max)
	    {
	        m = max;
	        a = new char[m];
	        top = -1;
	    }
	 
	    public void push(char key)
	    {
	        a[++top] = key;
	    }
	 
	    public char pop()
	    {
	        return (a[top--]);
	    }
	 
	    public boolean isEmpty()
	    {
	        return (top == -1);
	    }
	}
	 
	class Conversion
	{
	    private pilaChar s;
	    private String input;
	    private String output = "";
	 
	    public Conversion(String str)
	    {
	        input = str;
	        s = new pilaChar(str.length());
	    }
	 
	    public String inToPost()
	    {
	        for (int i = 0; i < input.length(); i++)
	        {
	            char ch = input.charAt(i);
	            switch (ch)
	            {
	                case '+':
	                case '-':
	                    gotOperator(ch, 1);
	                    break;
	                case '*':
	                case '/':
	                    gotOperator(ch, 2);
	                    break;
	                case '(':
	                    s.push(ch);
	                    break;
	                case ')':
	                    gotParenthesis();
	                    break;
	                default:
	                    output = output + ch;
	            }
	        }
	        while (!s.isEmpty())
	            output = output + s.pop();
	        return output;
	    }
	 
	    private void gotOperator(char opThis, int prec1)
	    {
	        while (!s.isEmpty())
	        {
	            char opTop = s.pop();
	            if (opTop == '(')
	            {
	                s.push(opTop);
	                break;
	            } else
	            {
	                int prec2;
	                if (opTop == '+' || opTop == '-')
	                    prec2 = 1;
	                else
	                    prec2 = 2;
	                if (prec2 < prec1)
	                {
	                    s.push(opTop);
	                    break;
	                } else
	                    output = output + opTop;
	            }
	        }
	        s.push(opThis);
	    }
	 
	    private void gotParenthesis()
	    {
	        while (!s.isEmpty())
	        {
	            char ch = s.pop();
	            if (ch == '(')
	                break;
	            else
	                output = output + ch;
	        }
	    }
	}
	 
	class Tree
	{
	    private nodo root;
	 
	    public Tree()
	    {
	        root = null;
	    }
	 
	    public void insert(String s)
	    {
	        Conversion c = new Conversion(s);
	        s = c.inToPost();
	        pilaNodo stk = new pilaNodo(s.length());
	        s = s + "#";
	        int i = 0;
	        char symbol = s.charAt(i);
	        nodo newNode;
	        while (symbol != '#')
	        {
	            if (symbol >= '0' && symbol <= '9' || symbol >= 'A'
	                    && symbol <= 'Z' || symbol >= 'a' && symbol <= 'z')
	            {
	                newNode = new nodo(symbol);
	                stk.push(newNode);
	            } else if (symbol == '+' || symbol == '-' || symbol == '/'
	                    || symbol == '*')
	            {
	            	nodo ptr1 = stk.pop();
	            	nodo ptr2 = stk.pop();
	                newNode = new nodo(symbol);
	                newNode.izq = ptr2;
	                newNode.der = ptr1;
	                stk.push(newNode);
	            }
	            symbol = s.charAt(++i);
	        }
	        root = stk.pop();
	    }
	 
	    public void recorridos(int type)
	    {
	        switch (type)
	        {
	            case 1:
	                System.out.print("Preorden:-    ");
	                preOrder(root);
	                break;
	            case 2:
	                System.out.print("Inorden:-     ");
	                inOrder(root);
	                break;
	            case 3:
	                System.out.print("Postorden:-   ");
	                postOrder(root);
	                break;
	            default:
	                System.out.println("opcion invalida:");
	        }
	    }
	 
	    private void preOrder(nodo localRoot)
	    {
	        if (localRoot != null)
	        {
	            localRoot.displayNode();
	            preOrder(localRoot.izq);
	            preOrder(localRoot.der);
	        }
	    }
	 
	    private void inOrder(nodo localRoot)
	    {
	        if (localRoot != null)
	        {
	            inOrder(localRoot.izq);
	            localRoot.displayNode();
	            inOrder(localRoot.der);
	        }
	    }
	 
	    private void postOrder(nodo localRoot)
	    {
	        if (localRoot != null)
	        {
	            postOrder(localRoot.izq);
	            postOrder(localRoot.der);
	            localRoot.displayNode();
	        }
	    }
	}
	 
	public class recorridos
	{
	    public static void main(String args[]) 
	    {
	    	Scanner Leer =  new Scanner(System.in);
	        String ch = "y";
	        while (ch.equals("y"))
	        {
	            Tree t1 = new Tree();
	            System.out.println("Insertar Expresion");
	            String a = Leer.nextLine();
	            t1.insert(a);
	            t1.recorridos(1);
	            System.out.println("");
	            t1.recorridos(2);
	            System.out.println("");
	            t1.recorridos(3);
	            System.out.println("");
	            System.out.print("continuar? y/n");
	            ch = Leer.nextLine();
	        }
	    }
	}
