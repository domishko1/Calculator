import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Calculator calc = new Calculator();
        System.out.println("Enter an expression without spaces that ends '=': ");
        Scanner scan = new Scanner(System.in);
        String expression = scan.nextLine();
        System.out.println("Result" + calc.calculate(expression));
    }
}
