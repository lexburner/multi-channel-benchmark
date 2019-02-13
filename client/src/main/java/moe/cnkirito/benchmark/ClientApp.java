package moe.cnkirito.benchmark;

import moe.cnkirito.krpc.remoting.Client;
import moe.cnkirito.krpc.remoting.NettyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author daofeng.xjf
 * @date 2019/2/13
 */
@SpringBootApplication
public class ClientApp {

    // you can change the channelNum to benchmark the qps of different channels
    private static final int channelNum = 10;

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }

    @Bean
    Client[] clients() {
        int n = channelNum;
        Client[] clients = new Client[n];
        for (int i = 0; i < n; i++) {
            clients[i] = new NettyClient("127.0.0.1", 9898);
        }
        return clients;
    }

}
