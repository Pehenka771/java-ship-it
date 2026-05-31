public class FragileParcel extends Parcel implements Trackable {

    private String currentLocation;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
        this.currentLocation = "Склад отправки";
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + description + " обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public int calculateDeliveryCost() {
        return 4 * weight;
    }


    @Override
    public void reportStatus(String newLocation) {
        this.currentLocation = newLocation;
        System.out.println("Хрупкая посылка " + description + " изменила местоположение на " + newLocation);
    }
}
