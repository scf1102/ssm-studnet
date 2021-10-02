package com.hkd.student.util;

import javax.servlet.http.HttpServletRequest;

public class BasePathUtil {

    public static String getBasePath(HttpServletRequest request){
        String basePath = "";
        String scheme = request.getScheme(); //http
        String serverName = request.getServerName(); //localhost
        int serverPort = request.getServerPort(); //8080
        String contextPath = request.getContextPath(); //上下文路径
        if (serverPort == 80){
            basePath = scheme + "://" + serverName + contextPath + "/";
        }else {
            basePath = scheme + "://" + serverName + ":" + serverPort + contextPath + "/";
        }
        return basePath;
    }
}
