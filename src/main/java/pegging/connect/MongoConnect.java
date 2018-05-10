package pegging.connect;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import pegging.configure.MongoConfigure;

import java.util.Properties;

/**
 * <p>Title: pegging.connect pegging</p>
 * <p>Description: </p>
 * <p>Company: WPT</p>
 *
 * @author caixiao
 * @version 1.0
 * @date 2018/5/4 下午3:54
 */
public class MongoConnect {
    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    private static MongoCollection mongoCollection;
    private static Properties props = MongoConfigure.getProps();
    private static String host = props.getProperty("MONGO.HOST");
    private static String port = props.getProperty("MONGO.PORT");
    private static String dataBase = props.getProperty("MONGO.DATABASE");
    private static String collection = props.getProperty("MONGO.COLLECTION");

    private MongoConnect(){}

    public static void mongoClient(){
        mongoClient = mongoClient(host,port);
    }

    public static MongoClient mongoClient(String host, String port){
        MongoClientOptions.Builder opt = new MongoClientOptions.Builder();
        opt.minConnectionsPerHost(2);
        opt.connectionsPerHost(3);
        opt.connectTimeout(20000);
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, Integer.parseInt(port)), opt.build());
        return mongoClient;
    }

    public static void mongoDatabase(){
        mongoDatabase = mongoDatabase(mongoClient,dataBase);
    }

    public static MongoDatabase mongoDatabase(MongoClient client,String dataBase){
        return client.getDatabase(dataBase);
    }

    public static MongoCollection mongoCollection(){
        mongoCollection = mongoCollection(mongoDatabase,collection);
        return mongoCollection;
    }

    public static MongoCollection mongoCollection(MongoDatabase database,String collection){
        return database.getCollection(collection);
    }


}
