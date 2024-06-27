package me.juneylove;

import me.juneylove.commands.CDCommand;
import me.juneylove.commands.ListCommand;
import me.juneylove.commands.MkDirCommand;
import me.juneylove.commands.PWDCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {

    private static final HashMap<String, ICommand> commands = new HashMap<>();

    private static final Path homePath = Paths.get(".").toAbsolutePath();
    private static Path currentPath = homePath;

    public static void main(String[] args) {

        registerCommand(new ListCommand());
        registerCommand(new CDCommand());
        registerCommand(new PWDCommand());
        registerCommand(new MkDirCommand());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            System.out.println("Enter command:");

            String fullString = "";
            try {
                fullString = reader.readLine();
            } catch (IOException e) {
                System.out.println("[!] I/O error, please validate input and try again.");
            }

            String commandName = fullString.split(" ")[0];
            String arg = fullString.substring(commandName.length()).trim();

            if (commandName.equals("quit")) break;

            if (commandName.equals("help")) {

                if (arg.equals("quit")) {
                    System.out.println("Quits out of the fancy terminal.");
                } else if (arg.isEmpty()) {
                    listCommands();
                } else if (commands.containsKey(arg)) {
                    commands.get(arg).printHelp();
                } else {
                    System.out.println("[!] Unrecognized command.");
                    listCommands();
                }

            } else {

                if (commands.containsKey(commandName)) {

                    if (!commands.get(commandName).run(arg)) { // run command - return false indicates a syntax error
                        System.out.println("[!] Error - command " + commandName + " usage:");
                        commands.get(commandName).printUsage();
                    }

                } else {
                    listCommands();
                }

            }

        }

    }

    private static void listCommands() {

        System.out.println("Valid commands are:");

        for (String command : commands.keySet()) {
            System.out.println("    " + command);
        }

        System.out.println("    quit");
        System.out.println("Use help <command> for more information.\n");

    }

    private static void registerCommand(ICommand command) {
        commands.put(command.name(), command);
    }

    // ==========

    public static Path getCurrentPath() {
        return currentPath;
    }

    public static void setCurrentPath(Path newPath) {

        if (!newPath.isAbsolute()) newPath = newPath.toAbsolutePath();
        currentPath = newPath.normalize();

    }

}