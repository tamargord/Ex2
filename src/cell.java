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
    public static boolean isForm(String text) {
        // Step 1: Check if the string starts with "=" and is not empty
        if (text == null || text.length() < 2 || text.charAt(0) != '=') {
            return false;
        }
        co

}
public boolean countBracket(String brackets){
        return countBracket(brackets,0,0,0);
}
private boolean countBracket(String brackets,int index, int count1,int count2 ){
        if(index==brackets.length()){
            return count1==count2;
        }

}