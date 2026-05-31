import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {

    @Test
    void addParcelWithinLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(50);
        StandardParcel p = new StandardParcel(PackageType.STANDARD, "Книга", 20, "адрес", 1);
        assertTrue(box.addParcel(p));
        assertEquals(1, box.getAllParcels().size());
        assertEquals(20, box.getCurrentWeight());
    }

    @Test
    void addMultipleParcelsAccumulatesWeight() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(50);
        box.addParcel(new StandardParcel(PackageType.STANDARD, "A", 20, "адрес", 1));
        box.addParcel(new StandardParcel(PackageType.STANDARD, "B", 30, "адрес", 1));
        assertEquals(2, box.getAllParcels().size());
        assertEquals(50, box.getCurrentWeight());
    }

    @Test
    void addParcelExceedingLimitRejected() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(50);
        box.addParcel(new StandardParcel(PackageType.STANDARD, "A", 30, "адрес", 1));
        StandardParcel tooHeavy = new StandardParcel(PackageType.STANDARD, "B", 25, "адрес", 1);
        assertFalse(box.addParcel(tooHeavy)); // превышение (30+25=55 > 50)
        assertEquals(1, box.getAllParcels().size());
        assertEquals(30, box.getCurrentWeight());
    }

    @Test
    void addParcelExactlyAtMaxWeight() {
        ParcelBox<FragileParcel> box = new ParcelBox<>(50);
        FragileParcel p = new FragileParcel(PackageType.FRAGILE, "Ваза", 50, "ул. Цветов", 5);
        assertTrue(box.addParcel(p));
        assertEquals(50, box.getCurrentWeight());
    }

    @Test
    void addParcelOneOverMaxWeight() {
        ParcelBox<FragileParcel> box = new ParcelBox<>(50);
        FragileParcel p = new FragileParcel(PackageType.FRAGILE, "Стекло", 51, "пр-т", 1);
        assertFalse(box.addParcel(p));
        assertEquals(0, box.getCurrentWeight());
        assertTrue(box.getAllParcels().isEmpty());
    }

    @Test
    void addToBoxWithDifferentTypeIsSafe() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
    }
}