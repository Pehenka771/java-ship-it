public class PerishableParcel extends Parcel {
    private int timeToLive;
    private int currentDay;

    public PerishableParcel(PackageType packageType, String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(packageType, description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public boolean isExpired(int currentDay) {
        if ((getSendDay() + getTimeToLive()) >= currentDay) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int calculateDeliveryCost() {
        return 3 * getWeight(); // коэффициент 2 для STANDARD
    }

}
