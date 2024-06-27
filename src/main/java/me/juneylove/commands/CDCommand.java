package me.juneylove.commands;

import me.juneylove.ICommand;
import me.juneylove.Main;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CDCommand implements ICommand {

    @Override
    public String name() {
        return "cd";
    }

    @Override
    public boolean run(String arg) {

        Path newPath;
        try {
            newPath = Paths.get(Main.getCurrentPath().toString(), arg.split("/"));
        } catch (InvalidPathException e) {
            return false;
        }

        if (!Files.exists(newPath)) {
            System.out.println("[!] Path does not exist.");
        } else if (!Files.isDirectory(newPath)) {
            System.out.println("[!] Path is not a directory.");
        } else {
            Main.setCurrentPath(newPath.toAbsolutePath().normalize());
        }

        return true;

    }

    @Override
    public void printUsage() {
        System.out.println("cd <path>");
    }

    @Override
    public void printHelp() {
        System.out.println("Changes the current directory (relative path).");
        System.out.println("To navigate to the parent directory, use \"cd ..\"");
    }

}
