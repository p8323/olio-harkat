package com.example.vko8_t4;

import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.Scanner;

public class BottleDispenser {

    // The array for the Bottle-objects

    public ArrayList<Bottle> bottle_array = new ArrayList();

    public int bottles;

    private float money;

    private static BottleDispenser instance = null;

    String bottlestr;

    int choice;



    public BottleDispenser() {

        bottles = 5;

        money = 0;

        // Initialize the array

        // Add Bottle-objects to the array

        bottle_array.add(new Bottle("Pepsi Max", 0.5, (float) 1.80));
        /*bottle_array.add(new Bottle("Pepsi Max", 0.5, (float) 1.80));
        bottle_array.add(new Bottle("Pepsi Max", 0.5, (float) 1.80));
        bottle_array.add(new Bottle("Pepsi Max", 0.5, (float) 1.80));
        bottle_array.add(new Bottle("Pepsi Max", 0.5, (float) 1.80));*/
        bottle_array.add(new Bottle("Pepsi Max", 1.5, (float) 2.2));
        bottle_array.add(new Bottle("Coca-Cola Zero", 0.5, (float) 2.0));
        bottle_array.add(new Bottle("Coca-Cola Zero", 1.5, (float) 2.5));
        bottle_array.add(new Bottle("Fanta Zero", 0.5, (float) 1.95));
    }



    public void addMoney() {

        money += 1;

        //System.out.println("Klink! Added more money!");
        MainActivity.text.setText("Klink! Added more money!");

    }



    public void buyBottle(String dropdown) {

        for (int i=0; i<bottle_array.size(); i++) {
            bottlestr = bottle_array.get(i).getName()+" "+bottle_array.get(i).getSize()+" "+bottle_array.get(i).getPrize()+"€";
            if (bottlestr.equals(dropdown)) {
                choice = i;
            }
        }

        //System.out.print("Your choice: ");
        //Scanner scans = new Scanner(System.in);

        /*if (scans.hasNextInt()) {
            choice = scans.nextInt();
        }
        else {
            scans.next();
        }*/

        float prize = bottle_array.get(choice).getPrize();

        try {
            if (money>=prize && bottles>=1) {
                bottles -= 1;
                money -= prize;
                MainActivity.text.setText("KACHUNK! "+bottle_array.get(choice).getName()+" "+bottle_array.get(choice).getSize()+" came out of the dispenser!");
                bottle_array.remove(choice);
            }
            else if (money < prize) {
                MainActivity.text.setText("Add money first!");
            }
            else if (bottles<1 ) {
                MainActivity.text.setText("Bottles out!");
            }
            else {
                MainActivity.text.setText("Shit happened...");
            }
        } catch (Exception e) {
            MainActivity.text.setText("Bottle not available.");
        }
        //scans.close();
        choice = 5000; //alustetaan valinta
    }



    public void returnMoney() {

        //System.out.printf("Klink klink. Money came out! You got %.2f€ back\n", money);
        String moneystr = String.format("%.2f", money);
        MainActivity.text.setText("Klink klink. Money came out! You got " +moneystr+"€ back\n");
        money = 0;
    }

    public static BottleDispenser getInstance() {
        if (instance == null) {
            instance = new BottleDispenser();
        }
        return instance;
    }

    public void buyBottleSimple(int index) {
        float prize = bottle_array.get(index).getPrize();

        if (money>=prize && bottles>=1) {

            bottles -= 1;
            money -= prize;

            //System.out.println("KACHUNK! "+bottle_array.get(index).getName()+ " came out of the dispenser!");
            MainActivity.text.setText("KACHUNK! "+bottle_array.get(index).getName()+ " came out of the dispenser!");
        }
        else if (money<prize) {
            //System.out.println("Add money first!");
            MainActivity.text.setText("Add money first!");
        }
        else if (bottles<1 ) {
            //System.out.println("Bottles out!");
            MainActivity.text.setText("Bottles out!");
        }
        else {
            //System.out.println("Add money first!");
            MainActivity.text.setText("Add money first!");
        }
        bottle_array.remove(index);
    }

}