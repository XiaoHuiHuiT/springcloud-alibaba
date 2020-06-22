package cn.ddossec.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//@Component
public class RequestOrigin implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        //从请求参数中获取origin的参数并返回
        String origin = httpServletRequest.getParameter("origin");
        //如果获取不到origin 就抛出异常
        if (StringUtils.isBlank(origin)){
            throw new IllegalArgumentException("必须指定origin");
        }
        return origin;
    }
}
