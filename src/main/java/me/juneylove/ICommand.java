package me.juneylove.commands;

public interface ICommand {

    String name();
    boolean run(String arg);
    void printUsage();

}
