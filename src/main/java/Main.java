import Item.Product;
import PrintMenu.MenuDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    static List<Product> productList = new ArrayList<>();
    static String[] userInputSplit;

    static {
        productList.add(new Product("Brownie", 0.65, 48));
        productList.add(new Product("Muffin", 1.00, 36));
        productList.add(new Product("Cakepop", 1.35, 1));
        productList.add(new Product("Water", 1.5, 30));
    }


    public static void main(String[] args) {
        run();
    }




    private static void run() {
        while(true){
            runProgramLogic();
        }
    }

    private static void runProgramLogic() {
        userInput();

        if(returnTotalPriceOfItems() == 0) {
            MenuDisplay.notEnoughStock();
        } else {
            MenuDisplay.totalItemsPrice(returnTotalPriceOfItems());
            MenuDisplay.amountPaid();
            double userPaidMoney;
            try {
                userPaidMoney = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException numberFormatException) {
                userPaidMoney = 0.0;
            }
            moneyToReturn(returnTotalPriceOfItems(), userPaidMoney);

        }
    }

    private static void userInput() {
        MenuDisplay.itemsToPurchase();
        String userInput = scanner.nextLine();
        userInputSplit = userInput.trim().split(",");
    }


    public static double returnTotalPriceOfItems() {
        double finalTotalPrice = 0.0;
        for (String firstLetter : userInputSplit) {
            for (Product product : productList) {
                if (isMatchAndHasStock(firstLetter, product)) {
                    finalTotalPrice += product.getProductPrice();
                }
            }
        }
        return finalTotalPrice;
    }

    private static boolean isMatchAndHasStock(String firstLetter, Product product) {
        return firstLetter.equals(product.getFirstLetterOfProduct()) && product.checkIfEnoughStock();
    }

    public static void moneyToReturn(double finalTotalPrice, double userPaidMoney) {
        double returnAmount = userPaidMoney - finalTotalPrice;
        if (returnAmount > 0) {
            MenuDisplay.amountToReturnPrint(returnAmount);
            changeStockAmounts();
            return;
        }
        MenuDisplay.notEnoughMoney();
    }

    public static void changeStockAmounts() {
        for (String firstLetter : userInputSplit) {
            for (Product product : productList) {
                if (isMatchAndHasStock(firstLetter, product)) {
                    product.updateProductStock();
                }
            }
        }
    }
}

