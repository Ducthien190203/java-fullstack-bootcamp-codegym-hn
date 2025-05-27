public class LongestIncreasingSubsequenceInString {
    public static String findIncreasingSubsequence(String input){
        if (input==null||input.length()==0)return"";

        StringBuilder result = new StringBuilder();
        result.append(input.charAt(0));

        for (int i=1;i<input.length();i++){
            if(input.charAt(i)>result.charAt(result.length()-1)){
                result.append(input.charAt(i));
            }
        }
        return result.toString();
    }
}
