package io.github.apollozhu;

/**
 * Still in alpha
 */
public class AZConsole {
    public static void clear() {
        System.out.print("\033[H\033[2J");
    }

    public static final String resetStyle = "\033[0m";

    public static String bolded(String content) {
        return format(content, 1);
    }

    public static String italiclized(String content) {
        return format(content, 3);
    }

    public static String underlined(String content) {
        return format(content, 4);
    }

    public static String strikedThrough(String content) {
        return format(content, 9);
    }

    private static String format(String content, int code) {
        return "\033[" + code + "m" + content;
    }
}
