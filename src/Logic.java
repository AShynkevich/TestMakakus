import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Logic {

    public Logic() throws IOException {
        int button = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (button != 4) {
            new Menu();
            String s = reader.readLine();

            try {
                button = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("НАЖИМАТЬ ТОЛЬКО ЦИФРЫ!1111!!!!11!");
                continue;
            }

            switch (button)
            {
                case 1: System.out.println("Make order"); break;
                case 2: System.out.println("Load order"); break;
                case 3: System.out.println("Get order"); break;
                case 4: System.exit(0); break;
                default: System.out.println("Смотри, куда клацаешь своими корявками, ламер!");
            }
            for ( int i = 0; i < 25; ++i )
                System.out.println();
        }
    }
}

