package com.byd.modules.biz.controller;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.script.Script;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 使用java API操作elasticSearch
 * 
 * @author 231
 *
 */
public class JavaESTest {

    private TransportClient client;
    private IndexRequest source;
    
    
    /**
     * 获取连接, 第二种方式
     * @throws Exception
     */
    /**
     * 获取连接, 第二种方式
     * @throws Exception
     */
    @Before
    public void before() throws Exception {
     // on startup
        Settings settings = Settings.builder()
                .put("cluster.name", "biyadi-test").build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("101.89.68.208"), 9300));
    }
    
    
    
    /**
     * 查看集群信息
     */
    @Test
    public void testInfo() {
        List<DiscoveryNode> nodes = client.connectedNodes();
        for (DiscoveryNode node : nodes) {
            System.out.println(node.getHostAddress());
        }
    }
    
    /**
     * 组织json串, 方式1,直接拼接
     */
    public String createJson1() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
            "}";
        return json;
    }
    
    /**
     * 使用map创建json
     */
    public Map<String, Object> createJson2() {
        Map<String,Object> json = new HashMap<String, Object>();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out elasticsearch");
        return json;
    }
    
    /**
     * 使用fastjson创建
     */
    public JSONObject createJson3() {
        JSONObject json = new JSONObject();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out elasticsearch");
        return json;
    }
    
    /**
     * 使用es的帮助类
     */
    public XContentBuilder createJson4() throws Exception {
        // 创建json对象, 其中一个创建json的方式
        XContentBuilder source = XContentFactory.jsonBuilder()
            .startObject()
                .field("user", "kimchy")
                .field("postDate", new Date())
                .field("message", "trying to out ElasticSearch")
            .endObject();
        return source;
    }
    
    /**
     * 进行连接测试
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        XContentBuilder source = createJson4();
        // 存json入索引中
        IndexResponse response = client.prepareIndex("twitter", "tweet", "1").setSource(source).get();
//        // 结果获取
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        boolean created = response.isFragment();
        System.out.println(index + " : " + type + ": " + id + ": " + version + ": " + created);
    }

    /**
     * get API 获取指定文档信息
     */
    @Test
    public void testGet() {
//        GetResponse response = client.prepareGet("twitter", "tweet", "1")
//                                .get();
        GetResponse response = client.prepareGet("byd", "article", "1")
                .setOperationThreaded(false)    // 线程安全
                .get();
        System.out.println(response.getSourceAsString());
    }
    
    /**
     * 测试 delete api
     */
    @Test
    public void testDelete() {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1")
                .get();
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        System.out.println(index + " : " + type + ": " + id + ": " + version);
    }
    
    /**
     * 测试更新 update API
     * 使用 updateRequest 对象
     * @throws Exception 
     */
    @Test
    public void testUpdate() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("twitter");
        updateRequest.type("tweet");
        updateRequest.id("1");
        updateRequest.doc(XContentFactory.jsonBuilder()
                .startObject()
                // 对没有的字段添加, 对已有的字段替换
                    .field("gender", "male")
                    .field("message", "hello")
                .endObject());
        UpdateResponse response = client.update(updateRequest).get();
        
        // 打印
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        System.out.println(index + " : " + type + ": " + id + ": " + version);
    }
    
    /**
     * 测试update api, 使用client
     * @throws Exception 
     */
    @Test
    public void testUpdate2() throws Exception {
        // 使用Script对象进行更新
//        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1")
//                .setScript(new Script("hits._source.gender = \"male\""))
//                .get();
        
        // 使用XContFactory.jsonBuilder() 进行更新
//        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1")
//                .setDoc(XContentFactory.jsonBuilder()
//                        .startObject()
//                            .field("gender", "malelelele")
//                        .endObject()).get();
        
        // 使用updateRequest对象及script
//        UpdateRequest updateRequest = new UpdateRequest("twitter", "tweet", "1")
//                .script(new Script("ctx._source.gender=\"male\""));
//        UpdateResponse response = client.update(updateRequest).get();
        
        // 使用updateRequest对象及documents进行更新
        UpdateResponse response = client.update(new UpdateRequest("twitter", "tweet", "1")
                .doc(XContentFactory.jsonBuilder()
                        .startObject()
                            .field("gender", "male")
                        .endObject()
                    )).get();
        System.out.println(response.getIndex());
    }
    
    /**
     * 测试update
     * 使用updateRequest
     * @throws Exception 
     * @throws InterruptedException 
     */
    @Test
    public void testUpdate3() throws InterruptedException, Exception {
        UpdateRequest updateRequest = new UpdateRequest("twitter", "tweet", "1")
            .script(new Script("ctx._source.gender=\"male\""));
        UpdateResponse response = client.update(updateRequest).get();
    }
    
    /**
     * 测试upsert方法
     * @throws Exception 
     * 
     */
    @Test
    public void testUpsert() throws Exception {
        // 设置查询条件, 查找不到则添加生效
        IndexRequest indexRequest = new IndexRequest("twitter", "tweet", "1")
            .source(XContentFactory.jsonBuilder()
                .startObject()
                    .field("name", "qergef")
                    .field("gender", "malfdsae")
                .endObject());
        // 设置更新, 查找到更新下面的设置
        UpdateRequest upsert = new UpdateRequest("twitter", "tweet", "1")
            .doc(XContentFactory.jsonBuilder()
                    .startObject()
                        .field("user", "wenbronk")
                    .endObject())
            .upsert(indexRequest);
        
        client.update(upsert).get();
    }
    
    /**
     * 测试multi get api
     * 从不同的index, type, 和id中获取
     */
    @Test
    public void testMultiGet() {
        MultiGetResponse multiGetResponse = client.prepareMultiGet()
        .add("twitter", "tweet", "1")
        .add("twitter", "tweet", "2", "3", "4")
        .add("anothoer", "type", "foo")
        .get();
        
        for (MultiGetItemResponse itemResponse : multiGetResponse) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String sourceAsString = response.getSourceAsString();
                System.out.println(sourceAsString);
            }
        }
    }
    
    /**
     * bulk 批量执行
     * 一次查询可以update 或 delete多个document
     */
    @Test
    public void testBulk() throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                        .endObject()));
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "another post")
                        .endObject()));
        BulkResponse response = bulkRequest.get();
    }
    
    /**
     * 使用bulk processor
     * @throws Exception 
     */
    @Test
    public void testBulkProcessor() throws Exception {
        // 创建BulkPorcessor对象
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, new Listener() {
            public void beforeBulk(long paramLong, BulkRequest paramBulkRequest) {
                // TODO Auto-generated method stub
            }
            
            // 执行出错时执行
            public void afterBulk(long paramLong, BulkRequest paramBulkRequest, Throwable paramThrowable) {
                // TODO Auto-generated method stub
            }
            
            public void afterBulk(long paramLong, BulkRequest paramBulkRequest, BulkResponse paramBulkResponse) {
                // TODO Auto-generated method stub
            }
        })
        // 1w次请求执行一次bulk
        .setBulkActions(10000)
        // 1gb的数据刷新一次bulk
        .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
        // 固定5s必须刷新一次
        .setFlushInterval(TimeValue.timeValueSeconds(5))
        // 并发请求数量, 0不并发, 1并发允许执行
        .setConcurrentRequests(1)
        // 设置退避, 100ms后执行, 最大请求3次
        .setBackoffPolicy(
                BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
        .build();
        
        // 添加单次请求
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "1"));
        bulkProcessor.add(new DeleteRequest("twitter", "tweet", "2"));
        
        // 关闭
        bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
        // 或者
        bulkProcessor.close();
    }
}