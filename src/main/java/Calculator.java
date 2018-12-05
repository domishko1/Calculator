public class Calculator {
    Calculating calculator;

    Calculator() {
        calculator = new Calculating();
    }

    String calculate(String expression)
    {
        int positionOnExpression = 0;
        int lengthOfExpression = expression.length();
        boolean Error = false;
        char currentCharacter = expression.charAt(positionOnExpression);
        if (calculator.isOperation(currentCharacter))
            Error = true;
        while ((currentCharacter != '=') && !Error) {
            if (calculator.goodSymbol(currentCharacter)) {
                if (calculator.isOperation(currentCharacter) && calculator.isOperation(expression.charAt(positionOnExpression - 1))) {
                    Error = true;
                } else {
                    if (currentCharacter == ')') {
                        while (!calculator.stackForSignsIsEmpty() && (calculator.peekStackForSigns() != '(')) {
                            calculator.result();
                        }
                        /* все знаки операций до ближайшей открывающей скобки и удаляем отк. скобку*/
                        if (!calculator.stackForSignsIsEmpty()) {
                            calculator.popStackForSigns();
                        } else {
                            Error = true;
                        }
                    }
                    if (calculator.isDigit(currentCharacter)) {
                        int number = 0;
                        while ((positionOnExpression < lengthOfExpression) && calculator.isDigit(expression.charAt(positionOnExpression))) {
                            number = number * 10 + (expression.charAt(positionOnExpression) - '0');
                            positionOnExpression++;
                        }
                        positionOnExpression--;
                        calculator.pushStack(number);
                    }
                    if (currentCharacter == '(')
                        calculator.pushStack('(');
                    if (calculator.isOperation(expression.charAt(positionOnExpression))) {
                        /* если стек пуст */
                        if (calculator.stackForSignsIsEmpty() && !calculator.stackForDigitsIsEmpty()) {
                            /* записываем в него операцию */
                            calculator.pushStack(currentCharacter);
                        } else {
                            if (calculator.stackForDigitsIsEmpty()) {
                                Error = true;
                            } else {
						/* если приоритет поступившей операции больше приоритета операции на вершине стека
						   заталкиваем поступившую операцию в стек */
                                if (calculator.prior(calculator.peekStackForSigns()) < calculator.prior(currentCharacter)) {
                                    calculator.pushStack(currentCharacter);
                                } else /*приоритет меньше */ {
                                    /* переписываем в выходную строку все операции с большим или равным приоритетом */
                                    while (!calculator.stackForSignsIsEmpty() && (calculator.prior(calculator.peekStackForSigns()) >= calculator.prior(currentCharacter))) {

                                        calculator.result();
                                    }
                                    calculator.pushStack(currentCharacter);/* записываем в стек поступившую  опеpацию */
                                }
                            }
                        }
                    }
                    positionOnExpression++;
                    if (positionOnExpression < lengthOfExpression) {
                        currentCharacter = expression.charAt(positionOnExpression);
                    } else
                        Error = true;
                }
            } else {
                Error = true;
            }
        }
        /* после рассмотрения всего выражения вычисляем выражение*/
        while (!Error && !calculator.stackForSignsIsEmpty() && !calculator.stackForDigitsIsEmpty()) {
            calculator.result();
        }
        String result = "";
        if (!Error && calculator.stackForSignsIsEmpty() && !calculator.stackForDigitsIsEmpty()) {
            result += calculator.popStackForDigits();
            if (calculator.stackForDigitsIsEmpty()) {
                return result;
            }
            else {
                return "Error!";
            }
        }
        else {
            return "Error!";
        }
    }
}
