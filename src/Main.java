import obstacles.Guard;
import obstacles.Obstacle;
import obstacles.ObstacleType;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> parsedArgs = parseArgs(args);

        System.out.println(parsedArgs);

    }

    /**
     * Parses the command line arguments into a HashMap of arguments
     * @param args The command line arguments
     * @return A HashMap of arguments
     */
    public static HashMap<String, ArrayList<String>> parseArgs(String[] args){
        HashMap<String, ArrayList<String>> parsedArgs = new HashMap<>();
        ArrayList<String> argValues = null;
        for(String arg : args) {
            if(arg.startsWith("-")){
                argValues = new ArrayList<>();
                parsedArgs.put(arg, argValues);
                continue;
            }
            if(argValues != null) {
                argValues.add(arg);
            }
        }
        return parsedArgs;
    }

    /**
     * Strips the parentheses from the argument
     * @param arg The argument to strip
     * @return The argument without parenthesis
     */
    public static String stripParenthesis(String arg) {
        return arg.substring(1, arg.length() - 1);
    }

    /**
     * Parses the obstacles from the command line arguments
     * @param parsedArgs The parsed arguments
     * @return The parsed obstacles.
     */
    public static ArrayList<Obstacle> parseObstacles(HashMap<String, ArrayList<String>> parsedArgs){
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        ObstacleType type = ObstacleType.GUARD;
        String key = "-" + type.getArgumentName();
        ArrayList<String> args = parsedArgs.get(key);
        if(args == null) {
            return obstacles;
        }
        for(String arg : args) {
            // Remove the parenthesis from the argument
            String cleanedArg = stripParenthesis(arg);
            Obstacle obstacle = Guard.parse(cleanedArg);
            obstacles.add(obstacle);
        }
        return obstacles;
    }

}
