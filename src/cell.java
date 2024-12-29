public class cell{
    public boolean isNumber(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    boolean isText(String){
        if(text.charAt(0)=='='){
            return false;

        }
    }

}