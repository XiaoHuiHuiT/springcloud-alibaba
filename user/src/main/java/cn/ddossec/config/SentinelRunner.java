package cn.ddossec.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.ArrayList;
import java.util.List;

//@Component
public class SentinelRunner {
    /*@Override
    public void run(ApplicationArguments args) throws Exception {
        initFlowRules();
    }*/
    private static final String GET_GOODS_KEY = "getMyGoods";
    public void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //设置资源名称
        rule.setResource(GET_GOODS_KEY);
        //设置针对来源
        rule.setLimitApp("default");
        //设置阈值类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置阈值
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
