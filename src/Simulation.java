import utility.Action;
import utility.Renderer;
import utility.WorldMap;

import java.util.Scanner;

public class Simulation {
    private static Action action = new Action();
    private static Renderer renderer = new Renderer();
    private static Scanner console = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        action.initActions();
        renderer.renderWorld(WorldMap.getWorldMap());
        while (true) {
            System.out.println("Введите \"n\", чтобы воспроизвести один шаг симуляции.\n" +
                    "Введите \"Start\", чтобы запустить бесконечную симуляцию \n" +
                    "Введите \"Exit\", чтобы завершить работу программы.");
            String input = console.nextLine().toLowerCase();
            if (input.equals("n")) {
                nextTurn();
            } else if (input.equals("start")) {
                startSimulation();
            } else if (input.equals("exit")) {
                break;
            }
        }
    }
    private static void nextTurn() {
        action.initTurns();
        renderer.renderWorld(WorldMap.getWorldMap());
        action.printTurnCounter();
    }
    private static void startSimulation() throws InterruptedException {
        Thread simulation = new Thread((() -> {
            while (!Thread.currentThread().isInterrupted()) {
                action.initTurns();
                renderer.renderWorld(WorldMap.getWorldMap());
                action.printTurnCounter();
                System.out.println("Если Вы хотите приостановить симуляцию, введите \"P\"");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Симуляция приостановлена");
                    break;
                }
            }
        }));
        simulation.start();
        Scanner console1 = new Scanner(System.in);
        String input1 = console1.nextLine().toLowerCase();
        while (!input1.equals("p")) {
            input1 = console1.nextLine().toLowerCase();
        }
        simulation.interrupt();
        simulation.join();
    }
    private static void pauseSimulation() {
        action.initTurns();
        renderer.renderWorld(WorldMap.getWorldMap());
        action.printTurnCounter();
    }
}