package moe.cnkirito.benchmark;

import moe.cnkirito.krpc.remoting.NettyServer;

/**
 * @author daofeng.xjf
 * @date 2019/2/13
 */
public class Server {
    public static void main(String[] args) {
        new NettyServer(9898).start();
    }
}
