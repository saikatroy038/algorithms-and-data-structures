package my.algorithms;

/**
 * Given a strings S and T containing letters and ‘#‘. The ‘#” represents a backspace.
 * The task is to check both string are equal are not
 */
public class StringEqual {

    public static boolean compare(String s, String t) {
        if (s == t) {
            return true;
        } else if ((s == null && t != null) || (s != null && t == null)) {
            return false;
        } else {
            return reduce(s).equals(reduce(t));
        }
    }

    private static String reduce(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = 0; i < stringBuilder.length() - 1; i++) {
            if (stringBuilder.charAt(i + 1) == '#') {
                stringBuilder = stringBuilder.deleteCharAt(i); // remove char for backspace
                stringBuilder = stringBuilder.deleteCharAt(i); // remove '#'
                i -= 2;
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        System.out.println(compare("abcd#djer##mk", "23##abcdjmk999#l###")); // abcdjmk, abcdjmk
    }
}
