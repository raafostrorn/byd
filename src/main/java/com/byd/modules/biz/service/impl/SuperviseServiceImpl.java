package com.byd.modules.biz.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.byd.config.ElasticsearchConfig;
import com.byd.modules.biz.service.SuperviseService;

@Service
public class SuperviseServiceImpl implements SuperviseService {
    Logger logger = Logger.getLogger(SuperviseServiceImpl.class);
    @Autowired
    ElasticsearchConfig elasticsearchConfig;
    String json = null;

    @Override
    public String search(String value) {
        try {
            if (!StringUtils.isEmpty(value)) {
                TransportClient client = elasticsearchConfig.getObject();
                WildcardQueryBuilder wqb = QueryBuilders.wildcardQuery("message", "" + value + "");
                // SimpleQueryStringBuilder sqs = QueryBuilders.simpleQueryStringQuery(value);
                SearchResponse response = client.prepareSearch().setQuery(wqb).setFrom(0).setSize(1000).execute().actionGet();
                Iterator<SearchHit> iterator = response.getHits().iterator();
                Map<String, String> mm = new HashMap<String, String>();
                List<Map> list = new ArrayList<Map>();
                while (iterator.hasNext()) {
                    Map<String, Object> map = iterator.next().getSource();
                    // mm.put("id", iterator.next().getId());
                    mm.put("message", map.get("message").toString());
                    list.add(mm);
                    System.out.println();
                }
                // if (list.size() > 0) {
                // json = JsonConvert.convertToJson(new MessageBean(true, JsonConvert.convertToJson(list)));
                // } else {
                // json = JsonConvert.convertToJson(new MessageBean(false
                // , Constant.SEARCH_NO_DATA));
                // }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return json;
    }

    @Override
    public void querySearch(String index, String type, String term, String queryString) {
        try {
            TransportClient client = elasticsearchConfig.getObject();
            SearchResponse scrollResp = client.prepareSearch("byd")
                    .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                   // .setScroll(new TimeValue(60000))
                    .setFrom(1)
                    .setSize(10).get(); //max of 100 hits will be returned for each scroll
            //Scroll until no hits are returned
                for (SearchHit hit : scrollResp.getHits().getHits()) {
                    //Handle the hit...
                    System.out.println(hit.getSourceAsString());
                }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    
    public static void main(String[] args) throws UnknownHostException {
     // on startup
        Settings settings = Settings.builder()
                .put("cluster.name", "biyadi-test").build();
        //Add transport addresses and do something with the client...
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("101.89.68.208"), 9300));

        SearchResponse scrollResp = client.prepareSearch("byd")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
               // .setScroll(new TimeValue(60000))
                .setFrom(1)
                .setSize(10).get(); //max of 100 hits will be returned for each scroll
        //Scroll until no hits are returned
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                //Handle the hit...
                System.out.println(hit.getSourceAsString());
            }
        
        // on shutdown

        //client.close();
    }

}