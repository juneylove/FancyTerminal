package me.juneylove.commands;

import me.juneylove.ICommand;
import me.juneylove.Main;

import java.io.File;
import java.nio.file.Path;

public class ListCommand implements ICommand {

    private static final String indent = "    ";
    private static final int FILE_LIST_LIMIT = 250; // max number of files that will be listed at once

    private int counter = 0;

    @Override
    public String name() {
        return "ls";
    }

    @Override
    public boolean run(String arg) {

        counter = 0;
        boolean deep = arg.equals("deep");
        listFiles(Main.getCurrentPath(), deep, 0);
        return true;

    }

    @Override
    public void printUsage() {
        System.out.println("ls [deep]");
    }

    @Override
    public void printHelp() {
        System.out.println("Lists all files and directories in the current directory.");
        System.out.println("\"ls deep\" lists all files in subdirectories as well.");
    }

    // ==========

    private void listFiles(Path path, boolean deep, int depth) {

        File currentDir = path.toFile();

        File[] files = currentDir.listFiles();
        if (files == null) {

            System.out.println("File " + currentDir.getName() + " is not a directory.");

        } else {

            if (depth == 0) System.out.println("Files in directory " + path + ":");
            for (File file : files) {

                System.out.println(indent.repeat(depth) + file.getName());
                if (deep && file.isDirectory()) {
                    listFiles(file.toPath(), true, depth+1);
                }

                counter++;
                if (counter > FILE_LIST_LIMIT) {
                    if (depth == 0) System.out.println("[!] File list limit reached, all files not shown.");
                    break;
                }

            }

        }

    }

}
