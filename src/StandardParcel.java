public class StandardParcel extends Parcel {
    public StandardParcel(PackageType packageType, String description, int weight, String deliveryAddress, int sendDay) {
        super(packageType, description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int calculateDeliveryCost() {
        return 2 * getWeight(); // коэффициент 2 для STANDARD
    }
}
