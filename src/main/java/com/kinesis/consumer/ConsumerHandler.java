package com.kinesis.consumer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ConsumerHandler implements RequestHandler<KinesisEvent,Void> {

    @Override
    public Void handleRequest(KinesisEvent kinesisEvent, Context context) {
        LambdaLogger logger = context.getLogger();
        Map<String,Student> data = new LinkedHashMap<>();


        for(KinesisEvent.KinesisEventRecord record: kinesisEvent.getRecords()){
            data.put(record.getKinesis().getPartitionKey(),new Student(new String(record.getKinesis().getData().array())));
        }

        logger.log("Data From Producer:: " + data.size());

        dataProcessor(data,logger);

        return null;
    }

    private void dataProcessor(Map<String, Student> data, LambdaLogger logger) {
        int recordProcessed = 0;
        Set<Map.Entry<String, Student>> entries = data.entrySet();
        for(var entry: entries){
            logger.log("Key = "+entry.getKey()+" and Value = "+entry.getValue());
            ++recordProcessed;
        }
        logger.log("Total Processed: "+recordProcessed);
    }


}
