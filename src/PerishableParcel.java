public class PerishableParcel extends Parcel {
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        if ((sendDay + timeToLive) >= currentDay) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int calculateDeliveryCost() {
        return 3 * weight;
    }

}
