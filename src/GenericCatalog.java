import java.util.ArrayList;
import java.util.List;

public class GenericCatalog<T> {
    List<T> items;

    public GenericCatalog() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
        System.out.println("Item added: " + item);
    }

    public boolean removeItem(T item) {
        if (items.contains(item)) {
            items.remove(item);
            System.out.println("Item removed: " + item);
            return true;
        }
        System.out.println("Error: Item not found.");
        return false;
    }

    public boolean itemIDExists(String itemID) {
        for (T item : items) {
            if (item instanceof LibraryItem<?> && ((LibraryItem<?>) item).getItemID().toString().equals(itemID)) {
                return true;
            }
        }
        return false;
    }

    public void displayItemsTable() {
        if (items.isEmpty()) {
            System.out.println("The catalog is empty.");
        } else {
            System.out.printf("%-10s %-30s %-30s%n", "ID", "| Title", "| Author");
            System.out.println("------------------------------------------------------------");
            for (T item : items) {
                if (item instanceof LibraryItem<?>) {
                    LibraryItem<?> libraryItem = (LibraryItem<?>) item;
                    System.out.printf("%-10s %-30s %-30s%n",
                            libraryItem.getItemID(),
                            "| " + libraryItem.getTitle(),
                            "| " + libraryItem.getAuthor());
                }
            }
        }
    }
}
