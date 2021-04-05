public class ZooKeeper {
    private String foodType = null;
    private int foodAmount = 0;

    public ZooKeeper() {
    };

    void foodRefill(String foodType) {
        this.foodAmount = 10;
        this.foodType = foodType;
    }

    String foodType(){
        return foodType;
    }

    @Override
    public String toString() {
        return String.format("ZooKeeper: Actual food type:" + foodType + " Actual food amount:" + foodAmount + "\n");
    }

}