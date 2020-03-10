package t1;

import java.util.Scanner;

public class Mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice;
		Scanner scan = new Scanner(System.in);
		BottleDispenser dispenser = BottleDispenser.getInstance();
		//int index=0;
		mainloop: while (true) {
			System.out.print("\n*** BOTTLE DISPENSER ***\n" + 
					"1) Add money to the machine\n" + 
					"2) Buy a bottle\n" + 
					"3) Take money out\n" + 
					"4) List bottles in the dispenser\n" + 
					"0) End\n"+"Your choice: ");
			choice = scan.nextInt();
			switch(choice) {
				case 1:
					dispenser.addMoney();
					break;
				case 2:
					dispenser.buyBottle();
					break;
				case 3:
					dispenser.returnMoney();
					break;
				case 4:
					for (int i=0; i<dispenser.bottles; i++) {
						System.out.println((i+1)+". Name: "+dispenser.bottle_array.get(i).getName());
						System.out.println("	Size: "+dispenser.bottle_array.get(i).getSize()+"	Price: "+dispenser.bottle_array.get(i).getPrize());
					}
					break;
				case 0:
					break mainloop;
			}
		}
		scan.close();
	}
}

