package common;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    /**
     * Parses a direction from a string(n, s, e, w).
     * @param arg The string to parse
     * @return The direction represented by the string
     */
    public static Direction parse(String arg) {
        switch(arg.toUpperCase()){
            case "N":
                return NORTH;
            case "S":
                return SOUTH;
            case "E":
                return EAST;
            case "W":
                return WEST;
            default:
                throw new IllegalArgumentException("Direction must be one of n, s, e, w");
        }
    }

    public static Direction getDirection(Location from, Location to) {
        int xDiff = to.getX() - from.getX();
        int yDiff = to.getY() - from.getY();
        if (xDiff == 0 && yDiff == 0){
            return null;
        }
        if (xDiff == 0) {
            return yDiff > 0 ? SOUTH : NORTH;
        }
        if (yDiff == 0) {
            return xDiff > 0 ? EAST : WEST;
        }
        return null;
    }
}
