public abstract class Parcel {
    private PackageType packageType;
    private String description;
    private int weight;
    private String deliveryAddress;
    private int sendDay;
    private int deliveryCost;

    public Parcel(PackageType packageType, String description, int weight, String deliveryAddress, int sendDay) {
        this.packageType = packageType;
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

    public int getDeliveryCost() {
        return calculateDeliveryCost();
    }

    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка " + getDescription() + " доставлена по адресу " + getDeliveryAddress());
    }

    public abstract int calculateDeliveryCost();

}
