package interpreter;

import macchiatosyntax.Program;

import java.io.IOException;
import java.util.Scanner;

public class Debugger {
    private static class Command {
        final Scanner scanner;
        char command;
        String argument;

        Command() {
            scanner = new Scanner(System.in);
        }

        public char getCommand() {
            return command;
        }

        public String getArgument() {
            assert argument != null;

            return argument;
        }

        public Integer getIntegerArgument() {
            return Integer.parseInt(getArgument());
        }

        public void setCommandFromStdin() {
            System.out.print("> ");
            String nextLine = scanner.nextLine();

            command = nextLine.charAt(0);
            argument = null;

            if (command == 's' || command == 'd' || command == 'm') {
                argument = nextLine.substring(1).strip();
            }
        }
    }

    public void debug(Program program) {
        Command command = new Command();

        command.setCommandFromStdin();

        while (command.getCommand() != 'e') {
            char commandChar = command.getCommand();
            switch (commandChar) {
                case 'c' -> program.continueProgram();
                case 's' -> program.step(command.getIntegerArgument());
                case 'd' -> program.display(command.getIntegerArgument());
                case 'm' -> {
                    try {
                        program.memoryDump(command.getArgument());
                    }
                    catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            command.setCommandFromStdin();
        }
    }
}
