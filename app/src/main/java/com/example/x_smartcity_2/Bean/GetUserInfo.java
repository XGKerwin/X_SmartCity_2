package com.example.x_smartcity_2.Bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/25  10:33
 */
public class GetUserInfo {
    /**
     *             "id": "372401199008080808",
     *             "name": "小T",
     *             "avatar": "http://192.168.43.50:8080/mobileA/images/user2.png",
     *             "phone": "13805312222",
     *             "sex": "女"
     */
    private String id,name,avatar,phone,sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
