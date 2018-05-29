package com.mga.financing.base.model;

/**
 * Created by mga on 2018/4/12 16:16.
 */

public class DataModel {
    public static BaseModel request(String token) {
        // 声明一个空的BaseModle
        BaseModel model = null;
        try {
            //利用反射机制获得对应Model对象的引用
            model = (BaseModel) Class.forName(token).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return model;
    }
}



