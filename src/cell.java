public class cell {
    public boolean isNumber(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isText(String text) {
        if (text == null) {
            return false;
        }
        if (text.charAt(0) == '=') {
            return false;

        }
        try {
            Double.parseDouble(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean isForm(String text) {
        if (countBracket(text)) {
            return true;
        }
        return isForm(text, 0);
    }

    private boolean isForm(String text, int index) {
        if (isNumber(text) && text.charAt(0) == '=') {
            return true;
        }
        if (index >= text.length() - 1) {
            return false;
        }

        if (index > 0 && index < text.length() - 1) {
            char currentChar = text.charAt(index);
            if (currentChar == '*' || currentChar == '+' || currentChar == '/') {

                if (Character.isLetterOrDigit(text.charAt(index - 1)) && Character.isLetterOrDigit(text.charAt(index + 1))) {
                    return true;
                }
            }
        }
        return isForm(text, index + 1);
    }

    public boolean countBracket(String brackets) {
        return countBracket(brackets, 0, 0, 0);
    }

    private boolean countBracket(String brackets, int index, int count1, int count2) {
        if (index == brackets.length()) {
            return count1 == count2;
        }

        char ch = brackets.charAt(index);
        if (ch == '(') {
            count1++;
        } else if (ch == ')') {
            count2++;
        }

        return countBracket(brackets, index + 1, count1, count2);
    }

    public boolean isOperation(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/';
    }

    public static double computeForm(String form) {
        if (form.startsWith("=")) {
            form = form.substring(1);
        }

        while (true) {
            int openIndex = form.lastIndexOf('(');
            if (openIndex == -1) {
                break;
            }

            int closeIndex = form.indexOf(')', openIndex);
            if (closeIndex == -1) {
                throw new IllegalArgumentException("Mismatched parentheses: " + form);
            }

            String inside = form.substring(openIndex + 1, closeIndex);
            double valueInside = calculateSimple(inside);
            form = form.substring(0, openIndex) + valueInside + form.substring(closeIndex + 1);
        }

        return calculateSimple(form);
    }

    private static double calculateSimple(String expr) {
        if (expr.isEmpty()) {
            return 0;
        }

        double result = 0;
        double lastNumber = 0;
        double currentNumber = 0;
        char operator = '+';

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                int j = i;
                while (j < expr.length() && (Character.isDigit(expr.charAt(j)) || expr.charAt(j) == '.')) {
                    j++;
                }
                currentNumber = Double.parseDouble(expr.substring(i, j));
                i = j - 1;
            }

            if ((!Character.isDigit(ch) && ch != ' ' && ch != '.') || i == expr.length() - 1) {
                switch (operator) {
                    case '+':
                        result += lastNumber;
                        lastNumber = currentNumber;
                        break;
                    case '-':
                        result += lastNumber;
                        lastNumber = -currentNumber;
                        break;
                    case '*':
                        lastNumber *= currentNumber;
                        break;
                    case '/':
                        if (currentNumber == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        lastNumber /= currentNumber;
                        break;
                }
                operator = ch;
                currentNumber = 0;
            }
        }

        result += lastNumber;
        return result;
    }

    public static void main(String[] args) {
        String input = "=4+2*9*7+(8+9)";
        double result = computeForm(input);
        System.out.println("Result: " + result); // Expected output: Result: 32.0
    }
}