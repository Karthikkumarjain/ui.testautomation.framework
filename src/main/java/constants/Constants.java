package constants;


import lombok.Getter;

@Getter
public class Constants {


    private static Constants INSTANCE = null;

    private Constants(){}

    public static synchronized Constants getInstance() {
        if(INSTANCE ==null){

            INSTANCE = new Constants();
        }
        return INSTANCE;

    }

    private final String GLOBAL_PROPERTIES = System.getProperty("user.dir")+"/src/test/resources/config/config.properties";
}
