package UCHelper.client;

import java.io.IOException;
import java.util.Scanner;

/**
 * @description this was a simple java program that help clubs to manage their
 *              community.
 * @author Marcus Lee Kai Yang RSFY2S2, marcustutorial@hotmail.com
 * @author Cecelia Lim Jie Shi RSFY2S2, cecelialjs-wm19@student.tarc.edu.my�
 * @author Dennis Lau Yik Ann RSFY2S2, dennislauyikann@gmail.com
 * @author
 */
public class Main {
    public static void clearScreen() {
        // Clears Screen in Java
        try {
            // If Windows then run 'cls' in cmd
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        do {
            System.out.println("------- UC Helper -------");
            System.out.println("[1] Admin");
            System.out.println("[2] Club");
            System.out.println("[3] Event");
            System.out.println("[4] Student Queue");
            System.out.println("[5] Exit");
            System.out.println("Select [1/2/3/4/5]");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.print("\nSelect [1/2/3/4/5]");
            }
            selection = scanner.nextInt();
            System.out.println("");
            switch (selection) {
            case 1:
                break;
            case 2:
                ClubHandler.runClubManager(scanner);
                break;
            case 3:
                EventHandler.EventUI();
                break;
            case 4:
                StudentQueue squ = new StudentQueue();
                squ.studentMainPage();
                break;
            default:
                System.out.println("\nYou can only enter selection from '1 - 4', try again.\n");
                break;
            }
            System.out.println("");
        } while (selection >= 1 && selection <= 4);

        scanner.close();
    }
}
