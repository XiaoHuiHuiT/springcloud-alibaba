package cn.ddossec.service;

import cn.ddossec.pojo.Goods;
import cn.ddossec.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 30315
 * @title: GoodsFeignClient
 * @projectName spring-cloud-alibaba-parent
 * @description: TODO
 * @date 2020-03-2415:28
 */
@FeignClient(name = "provide-goods",fallback = GoodsFeignClientFallBack.class)
public interface GoodsFeignClient {

    @RequestMapping("/getGoods")
    public Object getGoods();

    @RequestMapping("/getGoodsWithID/{id}")
    public ResponseResult getGoodsWithID(@PathVariable Integer id);

    @RequestMapping("/getGoodsWithObj")
    public ResponseResult getGoodsWithObj(@SpringQueryMap Goods goods);
}
