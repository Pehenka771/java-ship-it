import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeliveryCostTest {
    @Test
    void standardParcelCost() {
        StandardParcel p = new StandardParcel("Книга", 5, "ул. Ленина", 10);
        assertEquals(10, p.calculateDeliveryCost());
    }

    @Test
    void standardParcelZeroWeight() {
        StandardParcel p = new StandardParcel("Письмо", 0, "адрес", 1);
        assertEquals(0, p.calculateDeliveryCost());
    }

    @Test
    void fragileParcelCost() {
        FragileParcel p = new FragileParcel("Ваза", 3, "ул. Садовая", 15);
        assertEquals(12, p.calculateDeliveryCost());
    }

    @Test
    void perishableParcelCost() {
        PerishableParcel p = new PerishableParcel("Торт", 4, "пр. Мира", 20, 3);
        assertEquals(12, p.calculateDeliveryCost());
    }

    @Test
    void fragileParcelHeavyWeight() {
        FragileParcel p = new FragileParcel("Стекло", 1000, "склад", 1);
        assertEquals(4000, p.calculateDeliveryCost());
    }
}