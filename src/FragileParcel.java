public class FragileParcel extends Parcel implements Trackable {

    private String currentLocation;

    public FragileParcel(PackageType packageType, String description, int weight, String deliveryAddress, int sendDay) {
        super(packageType, description, weight, deliveryAddress, sendDay);
        this.currentLocation = "Склад отправки";
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public int calculateDeliveryCost() {
        return 4 * getWeight();
    }


    @Override
    public void reportStatus(String newLocation) {
        this.currentLocation = newLocation;
        System.out.println("Хрупкая посылка " + getDescription() + " изменила местоположение на " + newLocation);
    }
}
