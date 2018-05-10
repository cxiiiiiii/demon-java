package pegging.connect;

import com.mongodb.ServerAddress;
import com.mongodb.async.client.*;
//import com.mongodb.client.MongoCollection;
import com.mongodb.connection.ClusterSettings;
import org.bson.Document;
import pegging.configure.MongoConfigure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Arrays.asList;

/**
 * <p>Title: pegging.connect pegging</p>
 * <p>Description: </p>
 * <p>Company: WPT</p>
 *
 * @author caixiao
 * @version 1.0
 * @date 2018/5/4 上午9:19
 */
public class MongoAsyncConnect implements BaseConnect {
    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    private static MongoCollection mongoCollection;
    private static Properties props = MongoConfigure.getProps();
    private static String host = props.getProperty("MONGO.HOST");
    private static String port = props.getProperty("MONGO.PORT");
    private static String dataBase = props.getProperty("MONGO.DATABASE");
    private static String collection = props.getProperty("MONGO.COLLECTION");

    private MongoAsyncConnect(){};

    public static void mongoClient(){
        mongoClient = mongoClient(host,port);
    }

    public static MongoClient mongoClient(String host, String port){
        System.out.println(host+" "+port);
        ClusterSettings clusterSettings = ClusterSettings.builder()
                .hosts(asList(new ServerAddress(host,Integer.parseInt(port)))).build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .clusterSettings(clusterSettings).build();
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

    public static void mongoDatabase(){
        mongoDatabase = mongoDatabase(mongoClient,dataBase);
    }

    public static MongoDatabase mongoDatabase(MongoClient mongoClient, String dataBase){
        System.out.println(mongoClient+" "+dataBase);
        return mongoClient.getDatabase(dataBase);
    }

    public static MongoCollection mongoCollection(){
        mongoCollection = mongoCollection(mongoDatabase,collection);
        return mongoCollection;
    }

    public static MongoCollection mongoCollection(MongoDatabase dataBase,String collection){
        System.out.println(collection);
        MongoCollection<Document> mongoCollection = dataBase.getCollection(collection);
        return mongoCollection;
    }

    public static void close(){
        mongoClient.close();
    }

}

