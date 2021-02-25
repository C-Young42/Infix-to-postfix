import java.io.*;
public class InfixToPostfix extends Stack
{

    static Stack operators = new Stack();

    public static void main(String argv[]) throws IOException
    {
        String infix;


        BufferedReader keyboard = new BufferedReader (new InputStreamReader(System.in));


        System.out.print("\nEnter the algebraic expression in infix: ");
        infix = keyboard.readLine();

        System.out.println("The expression in postfix is:" + toPostfix(infix));

    }

    public static String toPostfix(String infix)

    {
        char symbol;
        String postfix = "";

        for(int i=0;i<infix.length();++i)

        {
            symbol = infix.charAt(i);

            if (Character.isLetter(symbol))
                postfix = postfix + symbol;
            else if (symbol=='(')

            {

                operators.push(symbol);
            }
            else if (symbol==')')

            {
                while (operators.peek() != '(')
                {
                    postfix = postfix + operators.pop();
                }
                operators.pop();
            }
            else

            {
                while (!operators.isEmpty() && !(operators.peek()=='(') && prec(symbol) <= prec(operators.peek()))
                    postfix = postfix + operators.pop();

                operators.push(symbol);
            }
        }
        while (!operators.isEmpty())
            postfix = postfix + operators.pop();
        return postfix;
    }

    static int prec(char x)
    {
        if (x == '+' || x == '-')
            return 1;
        if (x == '*' || x == '/' || x == '%')
            return 2;
        return 0;
    }
}