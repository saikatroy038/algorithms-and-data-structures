package my.algorithms;

/**
 * Code to convert snake_case to camelCase
 *
 * @author github.com/saikatroy038
 */
public class SnakeCaseToCamelCase {

    // considering word is always valid snake case
    public static String snakeToCamel(String word) {
        StringBuilder camelCase = new StringBuilder();
        int capsDiff = 'A' - 'a';
        boolean caps = false;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch == '_') {
                caps = true;
                continue;
            }

            if (caps) {
                if (ch >= 'a' && ch <= 'z') {
                    camelCase.append((char) (ch + capsDiff));
                }
                caps = false;
            } else {
                camelCase.append(ch);
            }
        }

        return camelCase.toString();
    }

    public static void main(String[] args) {
        System.out.println(snakeToCamel("ejhb_hello"));
    }
}
