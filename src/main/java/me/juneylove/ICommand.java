package me.juneylove;

public interface ICommand {

    String name();
    boolean run(String arg);
    void printUsage();
    void printHelp();

}
