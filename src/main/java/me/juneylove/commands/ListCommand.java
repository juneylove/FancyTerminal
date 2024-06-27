package me.juneylove.commands;

import me.juneylove.ICommand;
import me.juneylove.Main;

import java.io.File;
import java.nio.file.Path;

public class ListCommand implements ICommand {

    @Override
    public String name() {
        return "ls";
    }

    @Override
    public boolean run(String arg) {

        listFiles();
        return true;

    }

    @Override
    public void printUsage() {
        System.out.println("ls <no args>");
    }

    // ==========

    private void listFiles() {

        Path currentPath = Main.getCurrentPath();
        File currentDir = currentPath.toFile();

        File[] files = currentDir.listFiles();
        if (files == null) {

            System.out.println("Path: " + currentPath);
            System.out.println("File " + currentDir.getName() + " is not a directory.");

        } else {

            System.out.println("Files in directory " + currentPath + ":");
            for (File file : files) {
                System.out.println("    " + file.getName());
            }

        }

    }

    @Override
    public void printHelp() {
        System.out.println("Lists all files and directories in the current directory.");
    }

}
