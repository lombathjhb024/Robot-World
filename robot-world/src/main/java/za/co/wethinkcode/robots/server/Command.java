package za.co.wethinkcode.robots.server;

import za.co.wethinkcode.robots.client.BackwardCommand;
import za.co.wethinkcode.robots.client.LeftCommand;
import za.co.wethinkcode.robots.client.RightCommand;
import za.co.wethinkcode.robots.client.ForwardCommand;

import java.awt.*;

//
public abstract class Command {
    private final String name;
    private String argument;
    private String lastNumber;

    public abstract boolean execute(Robots target);

    public Command(String name) {
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public Command(String name, String argument, String lastNumber) {
        this(name, argument);
        this.lastNumber = lastNumber;
    }

    public String getLastNumber() {
        return lastNumber;
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]) {

            case "forward":
                if (args.length < 2) throw new IllegalArgumentException("Missing steps for forward.");
                return new ForwardCommand(args[1]);
            case "back":
                if (args.length < 2) throw new IllegalArgumentException("Missing steps for back.");
                return new BackwardCommand(args[1]);
            case "right":
                return new RightCommand();
            case "left":
                return new LeftCommand();

            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

}


