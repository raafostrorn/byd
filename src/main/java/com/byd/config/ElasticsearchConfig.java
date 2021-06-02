package com.byd.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ElasticsearchConfig {
    Logger logger = Logger.getLogger(ElasticsearchConfig.class);
    @Value("${spring.data.elasticsearch.cluster-nodes}") // 获取集群节点
    private String clusterNodes;

    @Value("${spring.data.elasticsearch.cluster-name}") // 获取集群名称
    private String clusterName;

  //  private TransportClient client;

    @Bean
    public Client getESClient() {
        // 设置集群名字
        Settings settings = Settings.builder().put("cluster.name", this.clusterName).put("network.tcp.keep_alive",false).build();
        Client client = new PreBuiltTransportClient(settings);
        try {
            // 读取的ip列表是以逗号分隔的
            for (String clusterNode : this.clusterNodes.split(",")) {
                String ip = clusterNode.split(":")[0];
                String port = clusterNode.split(":")[1];
                ((TransportClient) client).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), Integer.parseInt(port)));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }
}