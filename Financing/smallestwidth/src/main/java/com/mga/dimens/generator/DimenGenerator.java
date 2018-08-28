package com.mga.dimens.generator;


import com.mga.dimens.constants.DimenTypes;
import com.mga.dimens.utils.MakeUtils;

public class DimenGenerator {

    /**
     * 设计稿尺寸(根据自己设计师的设计稿的宽度填入)
     */
    private static final int DESIGN_WIDTH = 470;//1080*1920  6英寸（华为mate8）367ppi   1080/（2203/6/160）=470.63dp  px-dp系数2203/6/160=2.3
//    private static final int DESIGN_WIDTH = 393;//1080*1920  5英寸（nexus5）440ppi   1080/（2203/5/160）=392.72dp   px-dp系数2203/5/160=2.75

    /**
     * 设计稿高度  没用到
     */
    private static final int DESIGN_HEIGHT = 667;

    public static void main(String[] args) {
        int smallest = DESIGN_WIDTH>DESIGN_HEIGHT? DESIGN_HEIGHT:DESIGN_WIDTH;  //     求得最小宽度
        DimenTypes[] values = DimenTypes.values();
        for (DimenTypes value : values) {
            MakeUtils.makeAll(DESIGN_WIDTH, value, "./app/src/main/res");
        }

    }

}
