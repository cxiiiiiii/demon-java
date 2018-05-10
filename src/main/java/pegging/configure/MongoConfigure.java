package pegging.configure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title: pegging.configure pegging</p>
 * <p>Description: </p>
 * <p>Company: WPT</p>
 *
 * @author caixiao
 * @version 1.0
 * @date 2018/5/4 上午11:46
 */
public class MongoConfigure implements BaseConfigure {
    private static Properties props = new Properties();
    private static InputStream in = MongoConfigure.class.getClassLoader().getResourceAsStream("common");

    private MongoConfigure(){}

    public static Properties getProps(){
        try{
            props.load(in);
            return props;
        }catch(IOException e){
            e.printStackTrace();
        }
        return props;
    }
}
