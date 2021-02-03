package com.example.x_smartcity_2.Bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/26  11:09
 */
public class GetServiceByType {

    /**
     *             "serviceid": 1,
     *             "serviceName": "便民服务",
     *             "icon": "http://192.168.43.50:8080/mobileA/images/tubiao1.png",
     *             "url": "https://new.qq.com/omn/20201003/20201003A06MRV00.html",
     *             "serviceType": "智慧服务",
     *             "desc": "aaa"
     */

    private String serviceid,serviceName,icon,url,serviceType,desc;

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
