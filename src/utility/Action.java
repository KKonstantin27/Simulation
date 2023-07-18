package utility;

import entities.*;
import utility.Coordinate;
import utility.WorldMap;

import java.util.Map;

public class Action {
    public void initActions() {
        Renderer renderer = new Renderer();
        Border border = new Border();
        Ground ground = new Ground();
        Obstacle obstacle = new Obstacle();

        WorldMap.addEntity(border.createEntity());
        WorldMap.addEntity(ground.createEntity());
        WorldMap.addEntity(obstacle.createEntity());

        initActiveEntities();

        renderer.renderWorld(WorldMap.getWorldMap());
    }
    public void initActiveEntities() {
        Fruit fruit = new Fruit();
        Herbivore herbivore = new Herbivore();
        Predator predator = new Predator();

        WorldMap.addEntity(fruit.createEntity());
        WorldMap.addEntity(herbivore.createEntity());
        WorldMap.addEntity(predator.createEntity());
    }
    public void initTurns() {

        for (Map.Entry<Coordinate, Entity> entry : Herbivore.getHerbivoreMap().entrySet()) {
            Creature creature = (Creature) entry.getValue();
            System.out.println(entry.getKey());
            creature.findFood(entry.getKey());
            creature.makeMove(entry.getKey());
        }
    }
}
