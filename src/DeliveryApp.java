import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();
    private static final ParcelBox<StandardParcel> standardParcelBoxes = new ParcelBox<>(50);
    private static final ParcelBox<FragileParcel> fragileParcelBoxes = new ParcelBox<>(40);
    private static final ParcelBox<PerishableParcel> perishableParcelBoxes = new ParcelBox<>(30);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportTrackingStatus();
                    break;
                case 5:
                    showParcelContents();
                    break;
                case 6:
                    isExpired();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить статус посылок с трекингом");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("6 — Проверить свежесть продукта");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        String packageType = null;

        while (packageType == null) {
            System.out.println("Введите тип посылки (1 - STANDARD, 2 - FRAGILE, 3 - PERISHABLE):");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    packageType = "STANDARD";
                    break;
                case 2:
                    packageType = "FRAGILE";
                    break;
                case 3:
                    packageType = "PERISHABLE";
                    break;
                default:
                    System.out.println("Ошибка! Допустимые значения: 1 - STANDARD, 2 - FRAGILE, 3 - PERISHABLE. Попробуйте ещё раз.");
            }
        }

        System.out.println("Введите описание посылки");
        String description = scanner.nextLine();

        System.out.println("Введите вес посылки (кг):");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите адрес доставки");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Введите день отправления");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        Parcel newParcel = null;
        boolean added = false;

        switch (packageType) {
            case "STANDARD":
                newParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                added = standardParcelBoxes.addParcel((StandardParcel) newParcel);
                break;
            case "FRAGILE":
                newParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                added = fragileParcelBoxes.addParcel((FragileParcel) newParcel);
                if (added) {
                    trackableParcels.add((Trackable) newParcel);
                }
                break;
            case "PERISHABLE":
                System.out.println("Введите срок в днях, за который посылка не испортится");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                newParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                added = perishableParcelBoxes.addParcel((PerishableParcel) newParcel);
                break;
            default:
                System.out.println("Такой тип посылок не отправляем");
        }
        if (added) {
            allParcels.add(newParcel);
            System.out.println("Посылка добавлена!");
        } else {
            System.out.println("Превышен вес коробки, посылка не принята");
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для расчёта стоимости.");
            return;
        }

        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println(totalCost);
    }

    private static void reportTrackingStatus() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет посылок с поддержкой трекинга.");
            return;
        }

        System.out.println("Введите новое местоположение для всех отслеживаемых посылок:");
        String newLocation = scanner.nextLine();

        for (Trackable parcel : trackableParcels) {
            parcel.reportStatus(newLocation);
        }
    }

    private static void showParcelContents() {

        System.out.println("Выберите тип коробки:");
        System.out.println("1 — STANDARD");
        System.out.println("2 — FRAGILE");
        System.out.println("3 — PERISHABLE");

        int command = scanner.nextInt();
        scanner.nextLine();

        switch (command) {
            case 1:
                List<StandardParcel> allStandardParcels = standardParcelBoxes.getAllParcels();
                for (StandardParcel parcel : allStandardParcels) {
                    System.out.println(parcel.description);
                }
                break;
            case 2:
                List<FragileParcel> allFragileParcels = fragileParcelBoxes.getAllParcels();
                for (FragileParcel parcel : allFragileParcels) {
                    System.out.println(parcel.description);
                }
                break;
            case 3:
                List<PerishableParcel> allPerishableParcels = perishableParcelBoxes.getAllParcels();
                for (PerishableParcel parcel : allPerishableParcels) {
                    System.out.println(parcel.description);
                }
                break;
            default:
                System.out.println("Неверный выбор!");
        }
    }

    private static void isExpired() {
        List<PerishableParcel> perishableParcels = new ArrayList<>();
        for (Parcel parcel : allParcels) {
            if (parcel instanceof PerishableParcel) {
                perishableParcels.add((PerishableParcel) parcel);
            }
        }
        if (perishableParcels.isEmpty()) {
            System.out.println("Нет скоропортящихся посылок для проверки.");
            return;
        }

        System.out.println("Введите текущий день месяца:");
        int currentDay = scanner.nextInt();
        scanner.nextLine();

        for (PerishableParcel parcel : perishableParcels) {
            if (parcel.isExpired(currentDay)) {
                System.out.println("Посылка '" + parcel.description + "' испортилась.");
            } else {
                System.out.println("Посылка '" + parcel.description + "' ещё свежая.");
            }
        }
    }
}
