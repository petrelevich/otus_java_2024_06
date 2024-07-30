package ru.otus.gc;

/*
-Xms256m
-Xmx256m
-verbose:gc
-XX:+UseG1GC
*/

@SuppressWarnings({"java:S106", "java:S1144", "java:S125"})
public class Boxing {
    public static void main(String[] args) throws InterruptedException {
        //        calcInteger();
        calcInt();
        System.out.println("done");
    }

    private static void calcInteger() throws InterruptedException {
        final int limit = Integer.MAX_VALUE;
        Integer counter = 0;
        for (int idx = 0; idx < limit; idx++) {

            counter += Integer.valueOf(1);

            if (idx % 1_000_000 == 0) {
                Thread.sleep(1000);
            }
        }
        System.out.println(counter);
    }

    private static void calcInt() throws InterruptedException {
        final int limit = Integer.MAX_VALUE;
        int counter = 0;
        for (int idx = 0; idx < limit; idx++) {

            counter += 1;

            if (idx % 1_000_000 == 0) {
                Thread.sleep(1000);
            }
        }
        System.out.println(counter);
    }
}
