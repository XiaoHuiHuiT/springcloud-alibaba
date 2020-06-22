package cn.ddossec.controller;

import cn.ddossec.pojo.Goods;
import cn.ddossec.service.GoodsFeignClient;
import cn.ddossec.util.ResponseResult;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 30315
 * @title: UserController
 * @projectName springcloud-parent
 * @description: TODO
 * @date 2020-03-1312:12
 */
@RestController
@RefreshScope
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private GoodsFeignClient goodsFeignClient;

    @RequestMapping("/getGoods")
    public ResponseResult getGoods() {
        return ResponseResult.success("调用成功", goodsFeignClient.getGoods());
    }

    @RequestMapping("/getUser")
    public ResponseResult getUser() {
        List<ServiceInstance> instances = discoveryClient.getInstances("provide-goods");
        ServiceInstance serviceInstance = instances.get(0);
        System.out.println(serviceInstance);

        String url = serviceInstance.getUri() + "/getGoods".toString();
        return ResponseResult.success("调用成功", restTemplate.getForObject(url, Object.class));
    }

    /*@RequestMapping("/getGoods")
    public ResponseResult getGoods() {
        String url = "http://provide-goods/getGoods";
        return ResponseResult.success("调用成功",restTemplate.getForObject(url, Object.class));
    }*/

    @RequestMapping("/getGoodsWithID/{id}")
    public ResponseResult getGoodsWithID(@PathVariable Integer id, HttpServletRequest request) {
        System.out.println(request.getHeader("my-request-name"));
        return ResponseResult.success("调用成功", goodsFeignClient.getGoodsWithID(id));
    }

    @RequestMapping("/getGoodsWithObj")
    public ResponseResult getGoodsWithObj(Goods goods) {
        return ResponseResult.success("调用成功", goodsFeignClient.getGoodsWithObj(goods));
    }

    @RequestMapping("/test")
    public void test() {
        System.out.println("test-------");
    }

    public static void main(String[] args) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            for (int i = 0; i < 100; i++) {
                restTemplate.getForObject("http://localhost:5000/test", Object.class);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Value("${user.name}")
    private String username;

    @RequestMapping("/getUserName")
    public String getUserName() {
        return username;
    }

    /*@RequestMapping("/getMyGoods")
    public String getMyGoods() {
        try (Entry entry = SphU.entry(GET_GOODS_KEY)) {
            // 被保护的逻辑
            return "getMyGoods";
        } catch (BlockException ex) {
            // 处理被流控的逻辑
            return "被限流了";
        }
    }*/

    private static final String GET_GOODS_KEY = "getMyGoods";

    @SentinelResource(value = GET_GOODS_KEY, blockHandler = "blockHandlerMethod")
    @RequestMapping("/getMyGoods")
    public String getMyGoods() {
        System.out.println("getMyGoods来了");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "getMyGoods";
    }

    public String blockHandlerMethod(BlockException e) {
        e.printStackTrace();
        return "被限流了----";
    }

}
