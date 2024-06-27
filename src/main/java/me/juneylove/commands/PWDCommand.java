package me.juneylove.commands;

import me.juneylove.ICommand;
import me.juneylove.Main;

public class PWDCommand implements ICommand {

    @Override
    public String name() {
        return "pwd";
    }

    @Override
    public boolean run(String arg) {

        System.out.println("Current directory: " + Main.getCurrentPath());
        return true;

    }

    @Override
    public void printUsage() {
        System.out.println("pwd <no args>");
    }

    @Override
    public void printHelp() {
        System.out.println("Prints the path of the current directory.");
    }

}
