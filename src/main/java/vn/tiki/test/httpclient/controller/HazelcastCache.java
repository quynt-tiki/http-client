package vn.tiki.test.httpclient.controller;


import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.stereotype.Service;

public class HazelcastCache{

    private static volatile HazelcastCache instance = new HazelcastCache();

    public static HazelcastCache getInstance() {
        return instance;
    }

    private HazelcastInstance hsClient;

    private IMap<String, String> hsMap;

    private HazelcastCache() {
        this.hsClient = HazelcastClient.newHazelcastClient(config());
        this.hsMap = hsClient.getMap("customer");
    }

    private ClientConfig config() {
        var clientConfig = new ClientConfig();
        var networkConfig = new ClientNetworkConfig();
        networkConfig.addAddress("127.0.0.1:5701");
        clientConfig.setNetworkConfig(networkConfig);
        return clientConfig;
    }


    public String get(String key) {
        return hsMap.get(key);
    }

    public void put(String key, Object value) {
        hsMap.put(key, value.toString());
    }
}
