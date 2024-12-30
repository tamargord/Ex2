public class cell{
    public boolean isNumber(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public boolean isText(String text){
        if(text==null){
            return false;
        }
        if(text.charAt(0)=='='){
            return false;

        }
        try{
       Double.parseDouble(text);
       return false;
        }catch (NumberFormatException e){
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
        if(isNumber(text)&&text.charAt(0)=='='){
            return true;
        }
        if (index >= text.length() - 1) {
            return false;
        }

        if (index > 0 && index < text.length() - 1) {
            char currentChar = text.charAt(index);
            if (currentChar == '*' || currentChar == '+' || currentChar == '-') {

                if (Character.isLetterOrDigit(text.charAt(index - 1)) && Character.isLetterOrDigit(text.charAt(index + 1))) {
                    return true;
                }
            }
        }
        return isForm(text, index + 1);
    }

    public boolean countBracket(String brackets){
        return countBracket(brackets,0,0,0);
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