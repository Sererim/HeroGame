package org.example.map;

public class Space extends Structure {


    public Space(int positionX, int positionY, char representation) {
        super(positionX, positionY, representation);
    }

    @Override
    public boolean isPassable() {
        return true;
    }
}
