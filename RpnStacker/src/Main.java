import java.util.Scanner;
import java.util.Stack;
import java.io.*;



public class Main{

       
    private static Scanner scanner = new Scanner(System.in);

    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {

        String file = "../RpnStacker/src/file.txt";
        String input;
        int saida = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
                input = line;
                if (isInteger(input)) {
                    Token t = new Token(TokenType.NUM , input);
                    System.out.println(t.toString());
                    stack.push(Integer.parseInt(t.getLexeme()));
                } else if (isOperation(input)) {
                    int current = prepOperation(input, stack);
                    stack.push(current);
                    saida = current;
                }
                else{
                    System.out.println("Error: Unexpected character:" + input);
                    break;
                    
                }
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(saida);
    }


    public static int prepOperation(String operation, Stack<Integer> stack) {
        int result;
        if(stack.empty()){
            result = 0;
        }
        else{
            int aux = stack.pop();
            result = operate(operation, stack.pop(), aux);
        }

        return result;
    }

    public static int operate(String operation, int a, int b) {     // Realiza a operação que é passada como parametro.
        switch (operation) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": {
                if (b == 0) {
                    System.out.println("Divisão por 0");
                    return a;
                }
                return a / b;
            }
            default: return a;
        }
    }

    public static boolean isInteger(String input) {
        if (input == null) return false;

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isOperation(String input){
        
        if(input.equals("+")){
            Token t = new Token(TokenType.PLUS , input);
            System.out.println(t.toString());
            return true;
        }
        if(input.equals("-")){
            Token t = new Token(TokenType.MINUS , input);
            System.out.println(t.toString());
            return true;
        }
        if( input.equals("*") ){
            Token t = new Token(TokenType.STAR , input);
            System.out.println(t.toString());
            return true;
        }
        if(input.equals("/") ){
            Token t = new Token(TokenType.SLASH , input);
            System.out.println(t.toString());
            return true;
        }
        else{
            return false;
        }
    }
}
