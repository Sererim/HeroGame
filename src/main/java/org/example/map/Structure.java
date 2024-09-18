package org.example.map;

public class Structure extends MapObject {

    final private char representation;

    public Structure(int positionX, int positionY, char representation) {
        super(positionX, positionY);
        this.representation = representation;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public String colored() {
        return representation + "";
    }
}
