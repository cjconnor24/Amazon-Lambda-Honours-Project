package com.serverless.DAL;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class DynamoDBAdapter {

    private static DynamoDBAdapter db_adapter = null;
    private final AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    // CREATE THE CLIENT
    private DynamoDBAdapter() {

        this.client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_2)
                .build();
    }

    // RETURN AN INSTANCE OF THE ADAPTER
    public static DynamoDBAdapter getInstance() {
        if (db_adapter == null)
            db_adapter = new DynamoDBAdapter();

        return db_adapter;
    }

    // GET DB CLIENT
    public AmazonDynamoDB getDbClient() {
        return this.client;
    }


    // CREATE THE DB MAPPER
    public DynamoDBMapper createDbMapper(DynamoDBMapperConfig mapperConfig) {

        if (this.client != null)
            mapper = new DynamoDBMapper(this.client, mapperConfig);

        return this.mapper;
    }

}
