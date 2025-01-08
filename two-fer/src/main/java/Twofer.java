public class Twofer {
    public String twofer(String name) {
        String known = "One for "+name+", one for me.";
        String unknown = "One for you, one for me.";
        boolean condit = name != null;
        return condit ? known : unknown;

//        if(name != null){
//            return "One for "+name+", one for me.";
//        }
//        else{
//            return "One for you, one for me.";
//        }
    }
}
