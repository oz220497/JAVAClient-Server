package KnockKnock;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MainClass <1|2>");
            System.out.println("1: Start KnockKnockServer");
            System.out.println("2: Start RuppinServer");
            System.exit(1);
        }

        int choice;

        try {
            choice = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter '1' or '2'.");
            return;
        }

        switch (choice) {
            case 1:
                System.out.println("Starting KnockKnockServer...");
			try {
				KnockKnockServer.main(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
                break;

            case 2:
                System.out.println("Starting RuppinServer...");
                RuppinServer.main(null);
                break;

            default:
                System.out.println("Invalid choice. Use 1 for KnockKnockServer or 2 for RuppinServer.");
                System.exit(1);
        }
    }
}
