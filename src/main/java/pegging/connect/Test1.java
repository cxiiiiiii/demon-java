package pegging.connect;

import com.mongodb.Mongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: pegging.connect pegging</p>
 * <p>Description: </p>
 * <p>Company: WPT</p>
 *
 * @author caixiao
 * @version 1.0
 * @date 2018/5/4 下午4:13
 */
public class Test1 {
    public static void main(String[] args){
        MongoConnect.mongoClient();
        MongoConnect.mongoDatabase();
        MongoCollection collection = MongoConnect.mongoCollection();
        UpdateOptions opt = new UpdateOptions().upsert(true);
        BulkWriteOptions options = new BulkWriteOptions().ordered(false);

        List<WriteModel<Document>> list1 = new ArrayList<>();
        List<Document> list = new ArrayList<Document>();
        for(int i = 1000000; i < 2000000; i++){
            Document filter = new Document();
            Document update = new Document();

            Document doc = new Document("rmd",
                    new Document("preference",
                            new Document("secc_top_score","{\"1\":0.42,\"2\":0.08,\"3\":0.09,\"4\":0.12,\"5\":0.14,\"0\":0.1,\"7\":0.06}")
                                    .append("secc_top_score","[\"1001:0.25\",\"4001:0.13\",\"1004:0.12\",\"1002:0.1\",\"4002:0.1\",\"5006:0.07\",\"1003:0.07\",\"2003:0.06\",\"7001:0.06\",\"3004:0.05\"]"))
            );
            doc.append("_id",i);
            filter.append("_id",i);
            update.put("$set",doc);
            UpdateManyModel model = new UpdateManyModel(filter,update,opt);
            list1.add(model);
        }
        collection.bulkWrite(list1,options);






//        collection.insertOne(doc);
//        collection.insertMany(list,new InsertManyOptions().ordered(false).bypassDocumentValidation(false));

//        collection.bulkWrite(list);
    }
}
