package com.mga.dimens.constants;


public enum DimenTypes {

    //适配Android 3.2以上   大部分手机的sw值集中在  300-460之间
    DP_sw__300(300),  // values-sw300
    DP_sw__310(310),
    DP_sw__320(320),
    DP_sw__330(330),
    DP_sw__340(340),
    DP_sw__350(350),

    // 想生成多少自己以此类推
    DP_sw__360(360),//小米5的dpi是480,横向像素是1080px，根据px=dp(dpi/160)，横向的dp值是1080/(480/160),也就是360dp
    DP_sw__370(370),
    DP_sw__380(380),
    DP_sw__390(390),//1080*1920  5英寸（nexus5）440ppi   1080/（2203/5/160）=392.72dp   px-dp系数2203/5/160=2.75
    DP_sw__400(400),
    DP_sw__410(410),
    DP_sw__420(420),
    DP_sw__430(430),
    DP_sw__440(440),
    DP_sw__450(450),
    DP_sw__460(460),
    DP_sw__470(470);//华为mate8 1080*1920  6英寸 367ppi   1080/（2203/6/160）=470.63dp  px-dp系数2203/6/160=2.3


    /**
     * 屏幕最小宽度
     */
    private int swWidthDp;


    DimenTypes(int swWidthDp) {

        this.swWidthDp = swWidthDp;
    }

    public int getSwWidthDp() {
        return swWidthDp;
    }

    public void setSwWidthDp(int swWidthDp) {
        this.swWidthDp = swWidthDp;
    }

}
