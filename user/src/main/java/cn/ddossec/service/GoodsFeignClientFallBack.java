package cn.ddossec.service;

import cn.ddossec.pojo.Goods;
import cn.ddossec.util.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author 30315
 * @title: GoodsFeignClientFallBack
 * @projectName spring-cloud-alibaba-parent
 * @description: TODO
 * @date 2020-04-1011:02
 */
@Component
public class GoodsFeignClientFallBack implements GoodsFeignClient {
    @Override
    public Object getGoods() {
        return null;
    }

    @Override
    public ResponseResult getGoodsWithID(Integer id) {
        return ResponseResult.error("被降级了----");
    }

    @Override
    public ResponseResult getGoodsWithObj(Goods goods) {
        return null;
    }
}
