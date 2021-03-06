package com.demo.microservices.logging;

import com.demo.microservices.service.RabbitMQSender;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
@Log4j2
public class ZuulLoggingFilter extends ZuulFilter {

    @Autowired
    RabbitMQSender sender;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException{
        HttpServletRequest request =
                RequestContext.getCurrentContext().getRequest();
        log.info("Request uri -> {}",request.getRequestURI());
        try {
            sender.send(request.getRequestURI());
        }
        catch (Exception e){
            log.error("Issue happen in {} ",this.getClass().getName(),
                    "and method is run method");
            log.error("Issue in Connecting to RabbitMQ");
            log.error("Cause Of Exaception : {} \n",e.getStackTrace());
        }
        return null;
    }
}
