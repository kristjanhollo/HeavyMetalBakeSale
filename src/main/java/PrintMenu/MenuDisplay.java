package PrintMenu;

public class MenuDisplay {

    public static void itemsToPurchase() {
        System.out.print("Items to purchase > ");
    }

    public static void amountPaid() {
        System.out.print("Amount paid > $ ");
    }

    public static void totalItemsPrice(double amount) {
        System.out.print("Total > $ " + amount +"\n");
    }

    public static void notEnoughMoney() {
        System.out.print("Not enough Money!\n");
    }

    public static void amountToReturnPrint(double returnAmount) {
        System.out.println("Change > $ " + returnAmount);
    }

    public static void notEnoughStock() {
        System.out.println("Not enough stock!");
    }


}
