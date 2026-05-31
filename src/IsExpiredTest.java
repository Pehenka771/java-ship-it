import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsExpiredTest {
    @Test
    void notExpiredWhenSumGreaterThanCurrentDay() {
        PerishableParcel p = new PerishableParcel(PackageType.PERISHABLE, "Йогурт", 1, "адрес", 10, 5);
        assertFalse(p.isExpired(14));
    }

    @Test
    void notExpiredExactlyOnLastDay() {
        PerishableParcel p = new PerishableParcel(PackageType.PERISHABLE, "Йогурт", 1, "адрес", 10, 5);
        assertFalse(p.isExpired(15));
    }

    @Test
    void expiredWhenSumLessThanCurrentDay() {
        PerishableParcel p = new PerishableParcel(PackageType.PERISHABLE, "Йогурт", 1, "адрес", 10, 5);
        assertTrue(p.isExpired(16));
    }

    @Test
    void notExpiredWhenCurrentDayBeforeSendDay() {
        PerishableParcel p = new PerishableParcel(PackageType.PERISHABLE, "Сыр", 1, "ул. Леса", 20, 3);
        assertFalse(p.isExpired(18));
    }

    @Test
    void expiredLongAfter() {
        PerishableParcel p = new PerishableParcel(PackageType.PERISHABLE, "Молоко", 2, "дом", 1, 2);
        assertTrue(p.isExpired(100));
    }
}