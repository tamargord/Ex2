
public class Cell {
    String value;

    public static double computeForm(String form) {
        if (!isForm(form)) {
            throw new IllegalArgumentException("Invalid formula: " + form);
        }


        if (form.startsWith("=")) {
            form = form.substring(1);
        }

           return evaluateExpression(form);
    }

    public static boolean isText(String text) {
        if (text == null || text.isEmpty()) return false;
        return !isNumber(text) && !isForm(text);
    }

    public static boolean isForm(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }


        if (text.startsWith("=")) {
            text = text.substring(1);
        }


        if (text.contains("()")) {
            return false;
        }

        String[] tokens = text.split("[+\\-*/()]");
        for (String token : tokens) {
            if (!token.isEmpty() && !isNumber(token)) {
                return false;
            }
        }

        return countBracket(text);
    }


    private static boolean countBracket(String brackets) {
        return countBracket(brackets, 0, 0, 0);
    }

    // recursive parentheses count method
    private static boolean countBracket(String brackets, int index, int count1, int count2) {
        if (index == brackets.length()) {
            return count1 == count2;
        }

        char ch = brackets.charAt(index);
        if (ch == '(') {
            count1++;
        } else if (ch == ')') {
            count2++;
            if (count2 > count1) {
                return false;
            }
        }

        return countBracket(brackets, index + 1, count1, count2);
    }

    public static boolean isNumber(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double evaluateExpression(String expr) {
        if (!expr.contains("(")) {
            return evaluateSimpleExpression(expr);
        }

        int openIndex = expr.lastIndexOf('(');
        int closeIndex = expr.indexOf(')', openIndex);

        if (closeIndex == -1) {
            throw new IllegalArgumentException("Mismatched parentheses in expression: " + expr);
        }

        String inside = expr.substring(openIndex + 1, closeIndex);
        double valueInside = evaluateExpression(inside);

        String newExpr = expr.substring(0, openIndex) + valueInside + expr.substring(closeIndex + 1);

        return evaluateExpression(newExpr);
    }

    private static double evaluateSimpleExpression(String expr) {
        while (expr.contains("+") || expr.contains("-") || expr.contains("*") || expr.contains("/")) {
            if (expr.contains("*") || expr.contains("/")) {
                expr = processMultiplicationAndDivision(expr);
            } else {
                expr = processAdditionAndSubtraction(expr);
            }
        }
        return Double.parseDouble(expr);
    }

    private static double calculateResult(double left, double right, char operator) {
        return switch (operator) {
            case '*' -> left * right;
            case '/' -> left / right;
            case '+' -> left + right;
            case '-' -> left - right;
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }

    private static String processMultiplicationAndDivision(String expr) {
        int operatorIndex = findFirstOperatorIndex(expr, "*/");
        if (operatorIndex == -1) return expr;

        int leftStart = operatorIndex - 1;
        while (leftStart >= 0 && (Character.isDigit(expr.charAt(leftStart)) || expr.charAt(leftStart) == '.')) {
            leftStart--;
        }
        String leftStr = expr.substring(leftStart + 1, operatorIndex);
        if (!isNumber(leftStr)) {
            throw new IllegalArgumentException("Invalid number: " + leftStr);
        }
        double left = Double.parseDouble(leftStr);

        int rightEnd = operatorIndex + 1;
        while (rightEnd < expr.length() && (Character.isDigit(expr.charAt(rightEnd)) || expr.charAt(rightEnd) == '.')) {
            rightEnd++;
        }
        String rightStr = expr.substring(operatorIndex + 1, rightEnd);
        if (!isNumber(rightStr)) {
            throw new IllegalArgumentException("Invalid number: " + rightStr);
        }
        double right = Double.parseDouble(rightStr);

        char operator = expr.charAt(operatorIndex);
        double result = calculateResult(left, right, operator);

        return expr.substring(0, leftStart + 1) + result + expr.substring(rightEnd);
    }


    private static String processAdditionAndSubtraction(String expr) {
        int operatorIndex = findFirstOperatorIndex(expr, "+-");
        if (operatorIndex == -1) return expr;

        int leftStart = operatorIndex - 1;
        while (leftStart >= 0 && (Character.isDigit(expr.charAt(leftStart)) || expr.charAt(leftStart) == '.')) {
            leftStart--;
        }
        String leftStr = expr.substring(leftStart + 1, operatorIndex);
        if (!isNumber(leftStr)) {
            throw new IllegalArgumentException("Invalid number: " + leftStr);
        }
        double left = Double.parseDouble(leftStr);

        int rightEnd = operatorIndex + 1;
        while (rightEnd < expr.length() && (Character.isDigit(expr.charAt(rightEnd)) || expr.charAt(rightEnd) == '.')) {
            rightEnd++;
        }
        String rightStr = expr.substring(operatorIndex + 1, rightEnd);
        if (!isNumber(rightStr)) {
            throw new IllegalArgumentException("Invalid number: " + rightStr);
        }
        double right = Double.parseDouble(rightStr);

        char operator = expr.charAt(operatorIndex);
        double result = calculateResult(left, right, operator);

        return expr.substring(0, leftStart + 1) + result + expr.substring(rightEnd);
    }

    private static int findFirstOperatorIndex(String expr, String operators) {
        int minIndex = -1;
        for (int i = 0; i < operators.length(); i++) {
            char operator = operators.charAt(i);
            int index = expr.indexOf(operator);
            if (index != -1 && (minIndex == -1 || index < minIndex)) {
                minIndex = index;
            }
        }
        return minIndex;
    }
    public void setValue(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty.");
        }

        if (isNumber(s)) {
            // Handle as a number
            this.value = s;
        } else if (isForm(s)) {
            // Handle as a formula
            this.value = s;
        } else if (isText(s)) {
            // Handle as text
            this.value = s;
        } else {
            throw new IllegalArgumentException("Invalid value: " + s);
        }
    }
    public static void main(String[] args) {
        String input = "=4+2*9+7*(8+9)*2+5*7+6+8+9";
        double result = computeForm(input);
        System.out.println("Result: " + result);
    }

}
