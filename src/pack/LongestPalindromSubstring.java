package pack;

public class LongestPalindromSubstring {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccb"));
    }

    public static String longestPalindrome(String str) {
        if (str == null) {
            return null;
        }

        int len = str.length();
        int maxLen = 1;
        int subStringStart = 0;
        int subStringEnd = 0;

        for (int i = 0; i < len; i++) {

            for (int j = i + 1, k = i - 1; j < len && k >= 0; j++, k--) {
                if (str.charAt(j) == str.charAt(k)) {
                    int currentLen = j - k + 1;
                    if (currentLen > maxLen) {
                        maxLen = currentLen;
                        subStringStart = k;
                        subStringEnd = j;
                    }
                }
            }

            for (int j = i, k = i + 1; j >= 0 && k < len; j--, k++) {
                if (str.charAt(j) == str.charAt(k)) {
                    int currentLen = k - j + 1;
                    if (currentLen > maxLen) {
                        maxLen = currentLen;
                        subStringStart = j;
                        subStringEnd = k;
                    }
                }
            }
        }

        return str.substring(subStringStart, subStringEnd + 1);
    }
}
