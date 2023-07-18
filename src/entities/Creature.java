package entities;

public abstract class Creature extends Entity {
    private int hp;
    private int speed;

    public Creature(int hp, int speed) {
        this.hp = hp;
        this.speed = speed;
    }
}
