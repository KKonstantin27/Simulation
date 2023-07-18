package utility;

import entities.*;
import utility.Coordinate;
import utility.WorldMap;

import java.util.Map;

public class Action {
    Renderer renderer = new Renderer();
    Fruit fruit = new Fruit();
    Herbivore herbivore = new Herbivore();
    Predator predator = new Predator();
    public void initActions() {

        Border border = new Border();
        Ground ground = new Ground();
        Obstacle obstacle = new Obstacle();

        WorldMap.addEntity(border.createEntity());
        WorldMap.addEntity(ground.createEntity());
        WorldMap.addEntity(obstacle.createEntity());

        WorldMap.fillWorldMap();

        renderer.renderWorld(WorldMap.getWorldMap());
    }
    public void initActiveEntities() {
        WorldMap.addEntity(fruit.createEntity());
        WorldMap.addEntity(herbivore.createEntity());
        WorldMap.addEntity(predator.createEntity());
    }
    public void initTurns() {
        Creature.updateCreatureMap();
        for (Map.Entry<Coordinate, Entity> entry : Creature.getCreatureMap().entrySet()) {
            Creature creature = (Creature) entry.getValue();
            System.out.println(entry.getKey());
            creature.findFood(entry.getKey());
            creature.makeMove(entry.getKey());
            renderer.renderWorld(WorldMap.getWorldMap());


        }
        WorldMap.fillWorldMap();

    }
}
