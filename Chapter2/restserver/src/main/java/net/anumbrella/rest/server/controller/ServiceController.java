package net.anumbrella.rest.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
public class ServiceController {    //直接从redis里取，取不到再到数据库取，并更新到redis
    //数据库更新，直接刷新redis
    @RequestMapping("/service")
    public Object service(HttpServletRequest request) {
        ArrayList<Map<String,Object>> services = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> subMap = new HashMap<>();
        Map<String,Object> subSubMap = new HashMap<>();
//        map.put("@class","org.apereo.cas.services.RegexRegisteredService");
//        map.put("serviceId","^(https|imaps|http)://.*/sample.*");
//        map.put("name","web1");
//        map.put("id",20000001);
//        services.add(map);
//        map = new HashMap<>();
//        map.put("@class","org.apereo.cas.services.RegexRegisteredService");
//        map.put("serviceId","^(https|imaps|http)://.*/sample1.*");
//        map.put("name","web2");
//        map.put("id",20000002);
//        services.add(map);
        map = new HashMap<>();
        map.put("@class","org.apereo.cas.services.RegexRegisteredService");
        map.put("serviceId","^(https|imaps|http)://.*/client1.*");
        map.put("name","web3");
        map.put("id",20000003);
        //返回属性
        subMap = new HashMap<>();
        subMap.put("@class", "org.apereo.cas.services.ReturnAllAttributeReleasePolicy");
        map.put("attributeReleasePolicy", subMap);
        //访问策略
        subMap = new HashMap<>();
        subMap.put("@class", "org.apereo.cas.services.DefaultRegisteredServiceAccessStrategy");
        subMap.put("enabled", true);
        subMap.put("ssoEnabled", true);
        subMap.put("requireAllAttributes", false);
        subSubMap = new HashMap<>();
        Object[] strs = new Object[2];
        strs[0] = "java.util.HashSet";
        strs[1] ="client1-enable";
        //subSubMap.put("@class", "java.util.HashMap");
        subSubMap.put("ssoAttr", strs);
        subMap.put("requiredAttributes", subSubMap);
        map.put("accessStrategy", subMap);
        services.add(map);
        map = new HashMap<>();
        subMap = new HashMap<>();
        map.put("@class","org.apereo.cas.services.RegexRegisteredService");
        map.put("serviceId","^(https|imaps|http)://.*/client2.*");
        map.put("name","web4");
        map.put("id",20000004);
        subMap.put("@class", "org.apereo.cas.services.ReturnAllAttributeReleasePolicy");
        map.put("attributeReleasePolicy", subMap);
        subMap = new HashMap<>();
        subMap.put("@class", "org.apereo.cas.services.DefaultRegisteredServiceAccessStrategy");
        subMap.put("enabled", true);
        subMap.put("ssoEnabled", true);
        subMap.put("requireAllAttributes", false);
        subSubMap = new HashMap<>();
        strs = new Object[2];
        strs[0] = "java.util.HashSet";
        strs[1] = "client2-enable";
        //subSubMap.put("@class", "java.util.HashMap");
        subSubMap.put("ssoAttr", strs);
        subMap.put("requiredAttributes", subSubMap);
        map.put("accessStrategy", subMap);
        services.add(map);
        return services;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);




}
