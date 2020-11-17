package my.algorithms;

import java.util.*;

/**
 * Given a list of versions (in string format). The versions can have invalid characters (requires cleaning). Print the list in sorted order of increasing versions.
 * Input: ["1.2.34", "$5.j8", "3.4#", "3.45"]
 * output: 1.2, 3.4, 3.45
 */
public class VersionNumberSorting {

    public static List<String> sortVersions(List<String> versions) {
        versions = new ArrayList<>(versions);

        clean(versions);

        Comparator<String> comparator = (v1, v2) -> {
            String[] v1Nums = v1.split(".");
            String[] v2Nums = v2.split(".");

            for (int i = 0; i < v1Nums.length && i < v2Nums.length; i++) {
                int v1Part = Integer.parseInt(v1Nums[i]);
                int v2Part = Integer.parseInt(v2Nums[i]);

                if (v1Part > v2Part) {
                    return 1;
                } else if (v2Part > v1Part) {
                    return -1;
                }
            }

            if (v1Nums.length > v2Nums.length) {
                return 1;
            } else if (v1Nums.length < v2Nums.length) {
                return -1;
            } else {
                return 0;
            }
        };

        Collections.sort(versions, comparator);

        return versions;
    }

    public static void clean(List<String> versions) {
        Iterator<String> it = versions.iterator();
        while (it.hasNext()) {
            String version = it.next();
            for (int i = 0; i < version.length(); i++) {
                char ch = version.charAt(i);
                if (ch != '.' && (ch < '0' || ch > '9')) {
                    it.remove();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1.2.34", "$5.j8", "3.4#", "3.45");
//        System.out.println(sortVersions(list));
//        A a = new A(1);
//        A a2 = new A(2);
//
//        Set<Object> set = new HashSet<>();
//        set.add(a);
//        set.add(a2);
//
//        System.out.println(set.size());
//        a2.i = 1;
//        set.remove(a);
//        System.out.println(set.size());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        queue.add(2);

        System.out.println(queue.remove());

    }
}

class A {
    int i;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof A)) return false;
        A a = (A) o;
        return i == a.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i);
    }

    public A(int i) {
        this.i = i;
    }
}