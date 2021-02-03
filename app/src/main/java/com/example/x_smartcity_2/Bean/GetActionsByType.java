package com.example.x_smartcity_2.Bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/27  8:43
 */
public class GetActionsByType {
    /**
     *             "id": 1,
     *             "typeid": 1,
     *             "time": "2020-10-02 09:10:08",
     *             "name": "百人书法比赛",
     *             "count": 109,
     *             "praiseCount": 1000,
     *             "description": "中心广场大家一起书写中华经典诵读诗篇，弘扬中国文化，抒发爱国情怀",
     *             "image": "http://192.168.43.50:8080/mobileA/images/a1.jpg",
     *             "recommand": 1,
     *             "showImage": 1
     */

    private String id,typeid,time,name,count,praiseCount,description,image,recommand,showImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(String praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRecommand() {
        return recommand;
    }

    public void setRecommand(String recommand) {
        this.recommand = recommand;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }
}
