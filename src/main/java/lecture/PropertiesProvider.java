package lecture;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesProvider {
    public static final Properties PROPS;
    private PropertiesProvider(){}

    static{
        PROPS = new Properties();
        try {
            /* krever at du har lagret opplysningene i 'files/brettspill.properties' som følgt :
             * host=localhost
             * db_name=brettspill
             * port=3306
             * uname=<username>  -- her setter du din brukernavn
             * pwd=<password> -- her setter du passordet ditt
             */
            PROPS.load(new FileInputStream("files/brettspill.properties"));
        } catch (IOException e) {
            IO.println("Unable to load properties:"+e.getMessage());
        }
    }
}
