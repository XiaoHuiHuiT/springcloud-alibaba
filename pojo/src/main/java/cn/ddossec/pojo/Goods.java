package cn.ddossec.pojo;

import java.io.Serializable;

/**
 * @author 30315
 * @title: Goods
 * @projectName spring-cloud-alibaba-parent
 * @description: TODO
 * @date 2020-03-2523:08
 */
public class Goods implements Serializable {

    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
