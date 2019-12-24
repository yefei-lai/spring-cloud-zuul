package cn.study.microservice.springcloudzuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Token Filter(判断Token是否有值)
 */
@Slf4j
@Component
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    /**
     * 校验是否带token
     * @return
     */
    @Override
    public Object run() {
        //获取当前请求上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.getResponse().setContentType("application/json;charset=UTF-8");
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        String url = httpServletRequest.getRequestURL().toString();
        String method = httpServletRequest.getMethod();
        String token = httpServletRequest.getHeader("token");

        log.info("请求地址：{},请求方法是：{},请求token是{}", url,method,token);
        if (StringUtils.isNotBlank(token)){
            requestContext.setSendZuulResponse(true);
            requestContext.setResponseStatusCode(HttpStatus.OK.value());
            requestContext.set("isSuccess", true);
            return null;
        }else {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            requestContext.setResponseBody("token is empty");
            requestContext.set("isSuccess", false);
            return null;
        }
    }
}
