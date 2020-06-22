package cn.ddossec.controller;

import cn.ddossec.pojo.Goods;
import cn.ddossec.util.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 30315
 * @title: UserController
 * @projectName springcloud-parent
 * @description: TODO
 * @date 2020-03-1312:12
 */
@RestController
public class GoodsController {

    @RequestMapping("/getGoods")
    public ResponseResult getGoods() {
        return ResponseResult.success("My Goods2");
    }

    @RequestMapping("/getGoodsWithID/{id}")
    public ResponseResult getGoodsWithID(@PathVariable Integer id){
        return ResponseResult.success("goods___id="+id);
    }

    @RequestMapping("/getGoodsWithObj")
    public ResponseResult getGoodsWithObj(Goods goods){
        return ResponseResult.success("Goods.id="+goods.getId()+"/Goods.name="+goods.getName());
    }
}