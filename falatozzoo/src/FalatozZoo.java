import java.util.ArrayList;
import java.util.Scanner;

public class FalatozZoo {
    public static void main(String[] args) throws Exception {

        System.out.println("\nZoo is generated: \n");

        ArrayList<Animal> notFeededAnimals = new ArrayList<Animal>();
        notFeededAnimals.add(new Animal(1.0, 2.0, 20.0, 30.0, "tiger", "meat", true));
        notFeededAnimals.add(new Animal(15.0, 20.0, 20.0, 35.0, "elephant", "vegetable", false));
        notFeededAnimals.add(new Animal(1.0, 2.0, 0.5, 1.0, "fox", "meat", false));
        notFeededAnimals.add(new Animal(4.0, 8.0, 5.0, 8.0, "horse", "vegetable", false));
        notFeededAnimals.add(new Animal(1.0, 1.5, 25.0, 30.0, "lion", "meat", true));
        notFeededAnimals.add(new Animal(9.0, 13.0, 15.0, 20.0, "cattle", "vegetable", false));

        ArrayList<Animal> FeededAnimals = new ArrayList<Animal>();

        String foodType;

        while (notFeededAnimals.size() > 0) {
            ZooKeeper zooKeeper = new ZooKeeper();

            System.out.println("Animals waiting for feeding:");
            for (int i = 0; i < notFeededAnimals.size(); i++) {
                System.out.println(notFeededAnimals.get(i));
            }

            System.out.println("Animals already feeded:");
            for (int i = 0; i < FeededAnimals.size(); i++) {
                System.out.println(FeededAnimals.get(i));
            }

            System.out
                    .println("\nChoose the food type what you want to give to the animal! (meat, vegetable, water)\n");
            Scanner input = new Scanner(System.in);
            foodType = input.nextLine();
            if (foodType.equals("meat") || foodType.equals("vegetable") || foodType.equals("water")) {

                zooKeeper.foodRefill(foodType);
                System.out.println("choose the animal what you want to feed now!\n");
                String searchName = input.nextLine();
                int findedName = -1;

                for (int i = 0; i < notFeededAnimals.size(); i++) {
                    if (notFeededAnimals.get(i).nameOfTheAnimal().equals(searchName)) {
                        findedName = i;
                        break;
                    }
                }
                if (findedName == -1) {
                    System.out.println("there is no such animal in this zoo\n");
                } else {
                    Double foodAmount;
                    if (notFeededAnimals.get(findedName).isDangerous()) {
                        if (notFeededAnimals.get(findedName).isInCage()) {
                            System.out.println("this animal is dangerous, but already locked in a cage!\n");
                        } else {
                            System.out.println(
                                    "This animal is dangerous so you should lock it in a cage before the feeding! (Type 'lock')\n");
                            String putting;
                            do {
                                putting = input.nextLine();
                                if (putting.equals("lock")) {
                                    notFeededAnimals.get(findedName).putInCage();
                                    System.out.println("the animal is now locked, you can feed it now\n");
                                } else {
                                    System.out.println("you should type 'lock'!\n");
                                }
                            } while (!putting.equals("lock"));
                        }
                    }

                    do {
                        System.out.println("How much food do you want to give the animal? (min:0, max:10)\n");
                        foodAmount = input.nextDouble();
                        if (foodAmount < 0 || foodAmount > 10) {
                            System.out.println("wrong amount, try again!\n");
                        } else {

                            if (zooKeeper.foodType().equals("water")) {
                                notFeededAnimals.get(findedName).giveWater(foodAmount);
                                System.out.println("the quantity is given to the animal!\n");
                            } else if (zooKeeper.foodType().equals(notFeededAnimals.get(findedName).foodType())) {
                                notFeededAnimals.get(findedName).giveFood(foodAmount);
                                System.out.println("the quantity is given to the animal!\n");
                            } else {
                                System.out.println("This animal can't eat this type of food!\n");
                            }
                        }
                    } while (foodAmount < 0 || foodAmount > 10);

                    if (notFeededAnimals.get(findedName).waterLevel() < 0
                            || notFeededAnimals.get(findedName).foodLevel() < 0) {
                        System.out.println("this animal is died from overfeeding! Program is now shutting down!\n");
                        System.exit(0);
                    }

                    if (notFeededAnimals.get(findedName).waterLevel() == 0
                            && notFeededAnimals.get(findedName).foodLevel() == 0) {
                        FeededAnimals.add(notFeededAnimals.get(findedName));
                        notFeededAnimals.remove(findedName);
                        System.out.println("this animal is succesfully feeded!\n");
                    }
                }
            } else {
                System.out.println("you should choose from the three food type(meat, vegetable, water)\n");
            }
        }

        if (notFeededAnimals.size() == 0) {
            System.out.println("Animals:");
            for (int i = 0; i < FeededAnimals.size(); i++) {
                System.out.println(FeededAnimals.get(i));
            }

            System.out.println(
                    "\nevery animal is feeded, but you should let out the dangerous animals from the feeding cage!\n");

            Scanner input = new Scanner(System.in);
            String free;
            do {
                System.out.println("type 'free' to let out the animals\n");
                free = input.nextLine();
                if (!free.equals("free")) {
                    System.out.println("You should type 'free'!\n");
                }
            } while (!free.equals("free"));
            System.out.println("Animals:");
            for (int i = 0; i < FeededAnimals.size(); i++) {
                if (FeededAnimals.get(i).isInCage()) {
                    FeededAnimals.get(i).letOut();
                }
                System.out.println(FeededAnimals.get(i));
            }
            System.out.println("\nThe zoo is succesfully feeded!");
        }
    }
}