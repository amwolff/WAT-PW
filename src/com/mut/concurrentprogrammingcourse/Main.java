package com.mut.concurrentprogrammingcourse;

public class Main {

    public static void main(String[] args) {
        final Launcher launcher = new Launcher(2, 4, 6, 1000);
        launcher.run();
    }
}
