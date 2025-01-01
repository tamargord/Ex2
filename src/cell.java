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




        public static double computeForm(String form) {
            if (form.startsWith("=")) {
                form = form.substring(1);
            }

            form = form.replaceAll("\\s+", "");


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
                double valueInside = computeForm(inside);
                form = form.substring(0, openIndex) + valueInside + form.substring(closeIndex + 1);
            }

            return evaluateNoParen(form);
        }


        private static double evaluateNoParen(String expr) {
            if (expr.isEmpty()) {
                return 0;
            }

            double result = 0;
            double lastNumber = 0;
            double currentNumber = 0;
            char operator = '+';

            for (int i = 0; i < expr.length(); i++) {
                char ch = expr.charAt(i);

                if (Character.isDigit(ch)) {
                    currentNumber = currentNumber * 10 + (ch - '0');
                }


                if ((!Character.isDigit(ch) && ch != ' ') || i == expr.length() - 1) {
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
                            lastNumber = lastNumber * currentNumber;
                            break;
                        case '/':
                            if (currentNumber == 0) {
                                throw new ArithmeticException("Divide by zero in " + expr);
                            }
                            lastNumber = lastNumber / currentNumber;
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
            String input = "=2*(9+9)";
            double result = computeForm(input);
            System.out.println("Expression: " + input);
            System.out.println("Result: " + result);
        }
    }
