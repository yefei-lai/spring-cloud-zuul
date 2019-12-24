package cn.study.microservice.springcloudzuul.config;

import com.netflix.zuul.ZuulFilter;

public class MyFilter extends ZuulFilter {

    /**
     * 定义filter类型，有pre、route、post、error四种
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 定义filter的顺序，数字越小表示顺序越高，越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 表示是否需要执行该filter，true表示执行，false表示不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * filter需要执行的具体操作
     * @return
     */
    @Override
    public Object run() {
        return null;
    }
}
