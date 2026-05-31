import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {

    private final int maxWeight;
    private List<T> parcels;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcels = new ArrayList<>();
        this.currentWeight = 0;
    }

    public boolean addParcel(T parcel) {
        int parcelWeight = parcel.getWeight();

        if (currentWeight + parcelWeight > maxWeight) {
            System.out.println("Предупреждение: невозможно добавить посылку — превышен максимальный вес коробки!");
            return false;
        }

        parcels.add(parcel);
        currentWeight += parcelWeight;
        System.out.println("Посылка '" + parcel.getDescription() + "' успешно добавлена в коробку.");
        return true;
    }

    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}
