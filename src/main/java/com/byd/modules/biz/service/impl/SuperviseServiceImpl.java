package com.byd.modules.biz.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byd.config.ElasticsearchConfig;
import com.byd.modules.biz.entity.SuperviseEntity;
import com.byd.modules.biz.entity.SuperviseREntity;
import com.byd.modules.biz.entity.SuperviseSubEntity;
import com.byd.modules.biz.service.SuperviseService;
import com.google.gson.Gson;

@Service
public class SuperviseServiceImpl implements SuperviseService {
    Logger logger = Logger.getLogger(SuperviseServiceImpl.class);
    @Autowired
    ElasticsearchConfig elasticsearchConfig;
    String json = null;

    public static void main(String[] args) throws UnknownHostException {
        // on startup AWCgQG4m1caaXKmn3fbu
        Settings settings = Settings.builder().put("cluster.name", "biyadi-test").build();
        // Add transport addresses and do something with the client...
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("101.89.68.208"), 9300));
        GetResponse response = client.prepareGet("byd", "article", "AWCgQG4m1caaXKmn3fbu").setOperationThreaded(false).get();// ????????????

        System.out.println(response.getSourceAsString());

        /*
         * SearchResponse scrollResp = client.prepareSearch("byd").addSort(FieldSortBuilder.DOC_FIELD_NAME,
         * SortOrder.ASC)
         * // .setScroll(new TimeValue(60000))
         * .setFrom(1).setSize(10).get(); // max of 100 hits will be returned for each scroll
         * // Scroll until no hits are returned
         * for (SearchHit hit : scrollResp.getHits().getHits()) {
         * // Handle the hit...
         * System.out.println(hit.getSourceAsString());
         * }
         */

        // on shutdown

        // client.close();
    }

    /**
     * ????????????
     * 
     * @param params
     * @return
     */
    @Override
    public List<SuperviseEntity> queryList(Map<String, Object> params) {
        List<SuperviseEntity> list = new ArrayList<SuperviseEntity>();
        try {
            Client client = elasticsearchConfig.getESClient();

            MatchPhraseQueryBuilder mpq1 = QueryBuilders.matchPhraseQuery("bbs_name", "??????");
            RangeQueryBuilder mpq2 = QueryBuilders.rangeQuery("post_time.keyword").gte("2017-12-18");
            QueryBuilder qb = QueryBuilders.boolQuery().must(mpq1).must(mpq2);

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(qb);

            SearchResponse scrollResp = client.prepareSearch("byd")
                    // .setQuery(QueryBuilders.rangeQuery("post_time.keyword").gte("2016-01-01"))
                    .setQuery(qb).addSort("post_time.keyword", SortOrder.DESC)
                    // .setScroll(new TimeValue(60000))
                    .setFrom(1).setSize(10).get(); // max of 100 hits will be returned for each scroll
            Gson g = new Gson();
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                SuperviseEntity res = g.fromJson(hit.getSourceAsString(), SuperviseEntity.class);
                res.setId(hit.getId());
                list.add(res);
                // System.out.println(hit.getSourceAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * ????????????
     * 
     * @param id
     * @return
     */
    @Override
    public SuperviseEntity queryObject(String id) {
        SuperviseEntity res = null;
        try {
            Client client = elasticsearchConfig.getESClient();
            GetResponse response = client.prepareGet("byd", "article", id).setOperationThreaded(false).get();// ????????????
            // GetResponse response = client.prepareGet("byd", "article",
            // "AWCgQG4m1caaXKmn3fbu").setOperationThreaded(false).get();// ????????????
            Gson g = new Gson();
            res = g.fromJson(response.getSourceAsString(), SuperviseEntity.class);
            res.setId(res.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * ????????????
     * 
     * @param supervise
     */
    @Override
    public void update(SuperviseREntity supervise) {
        Client client = elasticsearchConfig.getESClient();
        SuperviseEntity entity = this.queryObject("AWCgXNep1caaXKmnIBok");
        SuperviseSubEntity sub = new SuperviseSubEntity();
        sub.setBrand("??????");
        entity.getResult().add(sub);
        UpdateResponse response = client.prepareUpdate("byd", "article", "AWCgXNep1caaXKmnIBok").setDoc(new Gson().toJson(entity, SuperviseEntity.class), XContentType.JSON).get();

    }

}