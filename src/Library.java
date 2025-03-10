import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        GenericCatalog<LibraryItem<Integer>> catalog = new GenericCatalog<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Menu:");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Display catalog");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
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
                    catalog.displayItemsTable();
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

    private static void addItem(GenericCatalog<LibraryItem<Integer>> catalog, Scanner scanner) {
        String title, author;
        int itemID;

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
            System.out.print("Enter item ID (integer): ");
            String idInput = scanner.nextLine().trim();
            try {
                itemID = Integer.parseInt(idInput);
                if (catalog.itemIDExists(idInput)) {
                    System.out.println("Item ID must be unique. Try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }

        LibraryItem<Integer> newItem = new LibraryItem<>(title, author, itemID);
        catalog.addItem(newItem);
    }

    private static void removeItem(GenericCatalog<LibraryItem<Integer>> catalog, Scanner scanner) {
        System.out.print("Enter item ID to remove (integer): ");
        int itemID;
        try {
            itemID = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        LibraryItem<Integer> itemToRemove = null;
        for (LibraryItem<Integer> item : catalog.items) {
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
