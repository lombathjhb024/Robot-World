package za.co.wethinkcode.robots.client;

//import com.google.code.gson.JsonObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;


import za.co.wethinkcode.robots.server.Command;
import za.co.wethinkcode.robots.server.MyWorld;
import za.co.wethinkcode.robots.server.Robots;

public abstract class LookCommand implements Command {

    @Override
    public  void execute(){}

    public JsonObject executeJSON(Robots robot, MyWorld world) {
        int robotX = robot.getX();
        int robotY = robot.getY();

        JsonArray view = new JsonArray();

        // Loop through rows from top to bottom (y axis descending)
        for (int y = robotY + 2; y >= robotY - 2; y--) {
            JsonArray row = new JsonArray();
            for (int x = robotX - 2; x <= robotX + 2; x++) {
                String cellContent;
                if (!world.isInsideBounds(x, y)) {
                    cellContent = "wall";
                } else if (world.hasObstacle(x, y)) {
                    cellContent = "obstacle";
                } else if (world.hasRobot(x, y)) {
                    cellContent = "robot";
                } else {
                    cellContent = "empty";
                }
                row.add(new JsonPrimitive(cellContent)); // IMPORTANT: wrap as JsonPrimitive
            }
            view.add(row);
        }

        JsonObject response = new JsonObject();
        response.addProperty("result", "OK");
        response.add("data", view);  // Use "view" if that matches your expected key

        return response;
    }
}
