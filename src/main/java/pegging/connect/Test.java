package pegging.connect;

import com.mongodb.Block;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import org.bson.Document;
import pegging.configure.MongoConfigure;

import static java.util.Arrays.asList;

/**
 * <p>Title: pegging.connect pegging</p>
 * <p>Description: </p>
 * <p>Company: WPT</p>
 *
 * @author caixiao
 * @version 1.0
 * @date 2018/5/4 上午9:13
 */
public class Test {
    public static void main(String[] args){
        MongoAsyncConnect.mongoClient();
        MongoAsyncConnect.mongoDatabase();
        MongoCollection collection = MongoAsyncConnect.mongoCollection();
        System.out.println(collection.toString());

        SingleResultCallback<Document> callbackPrintDocuments = new SingleResultCallback<Document>() {
            public void onResult(final Document document, final Throwable t) {
                System.out.println(document.toJson());
            }
        };

        SingleResultCallback<Void> callbackWhenFinished = new SingleResultCallback<Void>() {
            public void onResult(final Void result, final Throwable t) {
                System.out.println("Operation Finished!");
            }
        };

        Document doc = new Document("_id", "1")
                .append("type", "database")
                .append("count", 1)
                .append("versions", asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));

        collection.insertOne(doc, new SingleResultCallback<Void>() {
            public void onResult(final Void result, final Throwable t) {
                System.out.println("Inserted!");
            }
        });
        MongoAsyncConnect.close();
    }
}
