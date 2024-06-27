package me.juneylove.commands;

import me.juneylove.ICommand;
import me.juneylove.Main;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MkDirCommand implements ICommand {

    @Override
    public String name() {
        return "mkdir";
    }

    @Override
    public boolean run(String arg) {

        Path newPath;
        try {
            newPath = Paths.get(Main.getCurrentPath().toString(), arg.split("/"));
        } catch (InvalidPathException e) {
            return false;
        }

        if (Files.exists(newPath)) {
            System.out.println("[!] Directory already exists.");
        } else {

            if (!newPath.toFile().mkdirs()) {
                System.out.println("[!] Error creating directory.");
            }

        }

        return true;

    }

    @Override
    public void printUsage() {
        System.out.println("mkdir <path>");
    }

    @Override
    public void printHelp() {
        System.out.println("Creates a new directory in the current directory.");
        System.out.println("Can create multiple directories, i.e. \"mkdir dir1/dir2\"");
    }

}
