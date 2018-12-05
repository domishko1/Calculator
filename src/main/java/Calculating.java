import java.util.Stack;

public class Calculating {
    private  Stack stackForDigits;
    private  Stack stackForSigns;


    /**
     * Конструктор для класса Calculating. Инициализация двух стеков: стек для чисел и стек для знаков в выражении.
     */

    Calculating(){

        stackForDigits = new Stack<Integer>();
        stackForSigns = new Stack<Character>();
    }

    /**
     * Функция возвращает приоритет операции.
     * @param sign
     * @return
     */

    public  int prior(char sign) {
        switch (sign) {
            case '(':
                return 1;
            case '-':
            case '+':
                return 2;
            case '*':
            case '/':
                return 3;
        }
        return 0;
    }

    /** Функция для вычисления результата для двух аргументов. С вершины стека для чисел берется первый аргумент,
     * проверяется на пустоту стек для знаков, если он не пуст, то берется второй аргумент из стека для чисел
     * и операция из стека для знаков в соответствии с извлеченным знаком производятся расчеты. Результат заносится
     * обратно в стек для чисел.
     *
     */

    public  void result() {
        Double FirstArg = (Double) stackForDigits.pop();
        if (!stackForDigits.empty()) {

            Double SecondArg = (Double) stackForDigits.pop();
            double result = 0;
            switch ((Character) stackForSigns.pop()) {
                case '+': {
                    result = FirstArg + SecondArg;
                }
                break;
                case '-': {
                    result = SecondArg - FirstArg;
                }
                break;
                case '*': {
                    result = FirstArg * SecondArg;
                }
                break;
                case '/': {
                    result = SecondArg / FirstArg;
                }
                break;
            }
            pushStack(result);
        }

    }

    /**
     Функция проверяет поступивший символ на корректность и допустимость при расчетах.
     *Допустимые символы: цифры от 0до 9,'+','-','*','/','('и ')'.
     @param
     symbol
     *@return
     */

    public  boolean goodSymbol(char symbol) {
        return ((symbol >= '0') && (symbol <= '9') || symbol == '+' || symbol == '-'
                || symbol == '*' || symbol == '/' || symbol == ')' || symbol == '(');
    }

    /**
     Функция проверяет, являетсяли поступившийсимвол арифметическимзнаком.
     *
     @param operation
     *@return
     */

    public  boolean isOperation(char operation) {
        return (operation == '+' || operation == '-' || operation == '*' || operation == '/');
    }

    /**
     * Функция проверяет, является ли поступивший символ цифрой.
     * @param symbol
     * @return
     */

    public  boolean isDigit(char symbol) {
        return ((symbol - '0' >= 0) && (symbol - '0' <= 9));
    }

    /**
     * Заносим element в стек для чисел.
     * @param element
     */

    public  void pushStack(double element) {
        stackForDigits.push(element);
    }

    /**
     * Заносим element в стек для символов.
     * @param element
     */

    public  void pushStack(char element) {
        stackForSigns.push(element);
    }

    /**
     * Функция возвращает элемент, лежащий на вершине стека.
     *
     */

    public  double popStackForDigits() {
        return (Double) stackForDigits.pop();
    }

    /**
     *   Функция возвращает элемент, лежащий на вершине стека.
     * @return
     */

    public  char popStackForSigns() {
        return (Character) stackForSigns.pop();
    }

    /**
     * Проверка стека на пустоту.
     * @return
     */

    public  boolean stackForDigitsIsEmpty() {
        return stackForDigits.empty();
    }

    /**
     * Проверка стека на пустоту.
     * @return
     */

    public  boolean stackForSignsIsEmpty() {
        return stackForSigns.empty();
    }

    /**
     * Функция показывает, что лежит на вершине стека со знаками.
     * @return
     */

    public  char peekStackForSigns() {
        return (Character) stackForSigns.peek();
    }
}
