import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        GenericCatalog<LibraryItem<String>> catalog = new GenericCatalog<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Catalog Menu:");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Display catalog");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number: ");
                }
            }

            switch (choice) {
                case 1:
                    addItem(catalog, scanner);
                    break;
                case 2:
                    removeItem(catalog, scanner);
                    break;
                case 3:
                    catalog.displayItems();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addItem(GenericCatalog<LibraryItem<String>> catalog, Scanner scanner) {
        String title, author, itemID;

        do {
            System.out.print("Enter title: ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty.");
            }
        } while (title.isEmpty());

        do {
            System.out.print("Enter author: ");
            author = scanner.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("Author cannot be empty.");
            }
        } while (author.isEmpty());

        while (true) {
            System.out.print("Enter item ID (any non-empty value): ");
            itemID = scanner.nextLine().trim();
            if (itemID.isEmpty()) {
                System.out.println("Item ID cannot be empty.");
            } else if (catalog.itemIDExists(itemID)) {
                System.out.println("Item ID must be unique. Try again.");
            } else {
                break;
            }
        }

        LibraryItem<String> newItem = new LibraryItem<>(title, author, itemID);
        catalog.addItem(newItem);
    }

    private static void removeItem(GenericCatalog<LibraryItem<String>> catalog, Scanner scanner) {
        System.out.print("Enter item ID to remove: ");
        String itemID = scanner.nextLine().trim();

        LibraryItem<String> itemToRemove = null;
        for (LibraryItem<String> item : catalog.items) {
            if (item.getItemID().equals(itemID)) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            catalog.removeItem(itemToRemove);
        } else {
            System.out.println("Item not found.");
        }
    }
}
