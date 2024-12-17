package com.yuke.springboot.webservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.xml.ws.Endpoint;

@Slf4j
@Service
public class BeforeStartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${config.webservice.deptuser}")
    private String address;

    @Autowired
    private DeptUserService deptUserService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Endpoint.publish(address, deptUserService);
        log.debug("webService 服务发布成功！！！");
        log.debug("wsdl地址：" + address + "?wsdl");
    }
}
