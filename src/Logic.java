import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Logic {

    public void logic() throws IOException, InterruptedException {
        int button = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Menu menu = new Menu(); // создание объекта, ровно один раз!!!
        while (button != 4) {
            menu.menu(); // вызов метода menu() при каждом проходе цикла
            String s = reader.readLine();
            try {
                button = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Press only NUMBERS"); // без ругательств
                continue;
            }

            switch (button) {
                case 1:
                    System.out.println("Make order");
                    break;
                case 2:
                    System.out.println("Load order");
                    break;
                case 3:
                    System.out.println("Get order");
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("I think, you need some 'format C:/' or tea with polonium. Just choose fucking number...");
            }
            for (int i = 0; i < 25; ++i) {
                System.out.println();
                Thread.sleep(100); // это для чего?
            }
        }
    }


}

