package obstacles;

import common.Location;

public class Fence extends LocatableObstacle{
    private final Location end;

    /**
     * Constrcuts a new Fence object with the given start and end locations.
     * @param start The start location of the fence
     * @param end The end location of the fence
     */
    public Fence(Location start, Location end) {
        super(start);
        this.end = end;
        if(!isAxial()){
            throw new IllegalArgumentException("Fence must be vertical or horizontal");
        }
    }

    /**
     * Returns whether the fence is vertical or horizontal.
     * @return whether the fence is vertical or horizontal.
     */
    private boolean isAxial() {
        return location.getX() == end.getX() || location.getY() == end.getY();
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        int xDiffEnd = x - end.getX();
        int yDiffEnd = y - end.getY();
        int xDiffStart = x - location.getX();
        int yDiffStart = y - location.getY();

        // Taking advantage of 1-D vector math:
        // Given two ends A and B, and a point P, if (P - A) * (P - B) <= 0
        // then P is between A and B
        // since AP and BP are pointing in opposite directions
        return xDiffEnd * xDiffStart <= 0 && yDiffEnd * yDiffStart <= 0;
    }

    @Override
    public char getSymbol() {
        return ObstacleType.FENCE.getSymbol();
    }

    public static Fence parse(String arg) {
        String[] parts = arg.split(",");
        if(parts.length != 4) {
            throw new IllegalArgumentException("Fence must hae 4 coordinates: startX, startY, endX, endY");
        }

        int startX = Integer.parseInt(parts[0]);
        int startY = Integer.parseInt(parts[1]);
        int endX = Integer.parseInt(parts[2]);
        int endY = Integer.parseInt(parts[3]);
        Location start = new Location(startX, startY);
        Location end = new Location(endX, endY);
        return new Fence(start, end);

    }
}
