package za.co.wethinkcode.robots.client;
import za.co.wethinkcode.robots.server.Command;
import za.co.wethinkcode.robots.server.Robots;

public class ForwardCommand extends Command {
    public ForwardCommand(String argument) {
        super("forward", argument);
    }





    @Override
    public boolean execute(Robots target) {
        int nrSteps = Integer.parseInt(getArgument());
        if (target.updatePosition(nrSteps)) {
            target.setStatus("Moved forward by " + nrSteps + " steps.");
        } else {
            target.setStatus("Sorry, I can't move out of  my safe zone.");
        }
        return true;
    }
}

