package moe.cnkirito.benchmark;

import moe.cnkirito.krpc.remoting.Client;
import moe.cnkirito.krpc.remoting.codec.KrpcPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author daofeng.xjf
 * @date 2019/2/13
 */
@RestController
public class ClientEndpoint {

    private static AtomicLong id = new AtomicLong();
    @Autowired
    Client[] clients;

    AtomicInteger cursor = new AtomicInteger(0);
    private ResponseEntity ok = new ResponseEntity("OK", HttpStatus.OK);

    private static Long generateRequestId() {
        return id.getAndIncrement();
    }

    private static KrpcPacket createRandomRequest() {
        KrpcPacket krpcPacket = new KrpcPacket();
        krpcPacket.setProtocol(KrpcPacket.PROTOCOL_MESSAGE);
        krpcPacket.setType(KrpcPacket.REQUEST);
        Long requestId = generateRequestId();
        krpcPacket.setRequestId(requestId);
        krpcPacket.setContent(("hello " + requestId).getBytes());
        return krpcPacket;
    }

    @RequestMapping("/hello")
    public DeferredResult<ResponseEntity> hello() {
        DeferredResult<ResponseEntity> result = new DeferredResult<>();
        clients[cursor.getAndIncrement() % clients.length].send(createRandomRequest(), future -> {
            KrpcPacket krpcPacket = future.get();
            result.setResult(ok);
        });
        return result;
    }

}
