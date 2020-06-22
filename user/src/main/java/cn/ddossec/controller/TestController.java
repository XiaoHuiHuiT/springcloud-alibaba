package cn.ddossec.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 30315
 * @title: TestController
 * @projectName spring-cloud-alibaba-parent
 * @description: TODO
 * @date 2020-04-0918:38
 */
@RestController
public class TestController {

    @SentinelResource(value = "testWithID", blockHandler = "blockHandlerMethods")
    @RequestMapping("/testWidth/{id}")
    public String testWidth(@PathVariable Integer id) {
        int i = 1 / id;
        return "业务正常执行";
    }

    public String blockHandlerMethods(@PathVariable Integer id, BlockException e) {
        return "被降级了------";
    }

    @SentinelResource(value = "hotRule",fallback = "gethotRuleFallBack")
    @RequestMapping("/gethotRule")
    public String gethotRule(String a, String b) {
        return a + "---" + b;
    }

    public String gethotRuleFallBack(String a, String b) {
        return "被降级了-gethotRuleFallBack";
    }



}
