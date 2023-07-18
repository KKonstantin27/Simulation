package entities;

import utility.Coordinate;

import java.util.Map;

public abstract class Entity {
    public abstract Map<Coordinate, Entity> createEntity();

}
