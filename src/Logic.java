import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Logic {

    int button = 0;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Logic() throws IOException {
        while (button != 4) {
            String s = reader.readLine();

            try {
                button = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("НАЖИМАТЬ ТОЛЬКО ЦИФРЫ!1111!!!!11!");
                continue;
            }

            if (button == 1) {
                System.out.println("Make order");
            } else if (button == 2) {
                System.out.println("Load order");
            } else if (button == 3) {
                System.out.println("Get order");
            } else if (button == 4) {
                System.exit(0);
            } else System.out.println("Смотри, куда клацаешь своими корявками, ламер!");
        }
    }
}

