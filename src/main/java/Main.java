import Item.Product;
import PrintMenu.MenuDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    static List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product("Brownie", 0.65, 48));
        productList.add(new Product("Muffin", 1.00, 36));
        productList.add(new Product("Cakepop", 1.35, 1));
        productList.add(new Product("Water", 1.5, 30));
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        while(true){
            runProgramLogic();
        }
    }


    private static void runProgramLogic() {
        MenuDisplay.itemsToPurchase();

        String[] splitUserInput = getUserInputAndSplit();

        if(getTotalPriceOfProducts(splitUserInput) == 0) {
            MenuDisplay.notEnoughStock();
        } else {
            MenuDisplay.totalItemsPrice(getTotalPriceOfProducts(splitUserInput));
            MenuDisplay.amountPaid();

            String userMoneyInput = scanner.nextLine();
            double userPaidMoney = getUserPaidMoney(userMoneyInput);
            getAmountToReturn(getTotalPriceOfProducts(splitUserInput), userPaidMoney, splitUserInput);

        }
    }

    private static double getUserPaidMoney(String userMoneyPaid) {
        try {
            return Double.parseDouble(userMoneyPaid);
        } catch (NumberFormatException numberFormatException) {
            return  0.0;
        }
    }


    private static String[] getUserInputAndSplit() {
        String userInput = scanner.nextLine();
        return userInput.trim().split(",");
    }


    private static double getTotalPriceOfProducts(String[] splitUserInput) {
        double finalTotalPrice = 0.0;
        for (String firstLetter : splitUserInput) {
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


    private static void getAmountToReturn(double finalTotalPrice, double userPaidMoney, String[] splitUserInput) {
        double returnAmount = getReturnAmount(finalTotalPrice, userPaidMoney);
        if (isEnoughMoney(returnAmount)) {
            MenuDisplay.amountToReturnPrint(returnAmount);
            changeStockAmounts(splitUserInput);
        }
        MenuDisplay.notEnoughMoney();
    }

    private static boolean isEnoughMoney(double returnAmount) {
        return returnAmount > 0;
    }

    private static double getReturnAmount(double finalTotalPrice, double userPaidMoney) {
        return userPaidMoney - finalTotalPrice;
    }

    private static void changeStockAmounts(String[] splitUserInput) {
        for (String firstLetter : splitUserInput) {
            for (Product product : productList) {
                if (isMatchAndHasStock(firstLetter, product)) {
                    product.updateProductStock();
                }
            }
        }
    }
}

