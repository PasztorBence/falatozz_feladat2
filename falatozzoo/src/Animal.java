import java.lang.Math;

public class Animal {
    private double water, food;
    private String animalName, foodType;
    private boolean isDangerous, isInCage = false;

    public Animal(Double waterMin, Double waterMax, Double foodMin, Double foodMax, String animalName, String foodType,
            boolean isDangerous) {
        this.water = roundAvoid((Math.random() * (waterMax - waterMin)) + waterMin, 1);
        this.food = roundAvoid((Math.random() * (foodMax - foodMin)) + foodMin, 1);
        this.animalName = animalName;
        this.foodType = foodType;
        this.isDangerous = isDangerous;
    }

    void giveFood(Double amount) {
        this.food = this.food - amount;
    }

    void giveWater(Double amount) {
        Double newAmount = roundAvoid(this.water - amount, 1);
        this.water = newAmount;
    }

    double foodLevel() {
        return food;
    }

    double waterLevel(){
        return water;
    }

    String foodType(){
        return foodType;
    }

    String nameOfTheAnimal() {
        return animalName;
    }

    boolean isDangerous(){
        return isDangerous;
    }

    boolean isInCage(){
        return isInCage;
    }

    void putInCage(){
        this.isInCage = true;
    }

    void letOut(){
        this.isInCage = false;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    @Override
    public String toString() {
        return String.format("name:" + animalName + ", foodtype:" + foodType + ", food needs:" + food + ", water needs:"
                + water + ", dangerous:" + isDangerous + " locked:" + isInCage);
    }

}