package ru.job4j.io;

public class ArgZip {
    private final String[] args;
    private String directory = "";
    private String exclude = "";
    private String output = "";

    public ArgZip(String[] args) {
        this.args = args;
        parseArgs();
    }

    public boolean valid() {
        return !directory.isEmpty() && !output.isEmpty();
    }

    public String directory() {
        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }

    private void parseArgs() {
        for (String arg : args) {
            if (arg.startsWith("-d")) {
                directory = arg.substring(3);
            } else if (arg.startsWith("-e")) {
                exclude = arg.substring(3);
            } else if (arg.startsWith("-o")) {
                output = arg.substring(3);
            }
        }
    }
}
