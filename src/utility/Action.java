package utility;

import entities.*;
import utility.Coordinate;
import utility.WorldMap;

import java.util.List;
import java.util.Map;

public class Action {
    int turnCounter = 0;

    public void initActions() {
        Border border = new Border();
        Ground ground = new Ground();
        Obstacle obstacle = new Obstacle();
        Fruit fruit = new Fruit();
        Herbivore herbivore = new Herbivore();
        Predator predator = new Predator();

        WorldMap.addEntity(border.createEntity());
        WorldMap.addEntity(ground.createEntity());
        WorldMap.addEntity(obstacle.createEntity());
        WorldMap.addEntity(fruit.createEntity());
        WorldMap.addEntity(herbivore.createEntity());
        WorldMap.addEntity(predator.createEntity());

    }

    public void initTurns() {
        Creature.updateCreatureList();
        for (int i = 0; i < Creature.getCreatureList().size(); i++) {
            Creature curCreature = (Creature) Creature.getCreatureList().get(i);
            if (curCreature == null) {
                continue;
            }
            for (int j = 0; j < curCreature.getSpeed(); j++) {
                Coordinate curCoordinate = Creature.getCoordinateList().get(i);
                curCreature.findFood(curCoordinate);
                curCreature.makeMove(curCoordinate);
            }
        }
        WorldMap.fillWorldMap();
    }
    public void printTurnCounter() {
        turnCounter++;
        System.out.println("Количество ходов: " + turnCounter);
    }
}
