package com.mga.financing.utils;

import android.content.Context;

import com.ebupt.jlog.JLog;
import com.ebupt.jlog.constant.LogLevel;
import com.ebupt.jlog.constant.LogSegment;
import com.ebupt.jlog.constant.ZoneOffset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mga on 2018/4/8 11:12.
 */

public class LogUtils {

    private static boolean mLogDebugMode;
    private static final String modifier="---";//log打印修饰符

    public static void setLogDebugMode(boolean logDebugMode){
        mLogDebugMode=logDebugMode;
    }

    public static void initJlog(Context context) {
        FileUtil.checkDir(context);
        List<LogLevel> logLevels = new ArrayList<>();
        logLevels.add(LogLevel.DEBUG);
        logLevels.add(LogLevel.WARN);
        logLevels.add(LogLevel.ERROR);
        logLevels.add(LogLevel.INFO);
        JLog.init(context)
                .setDebug(mLogDebugMode)
                .writeToFile(true)
                .setLogLevelsForFile(logLevels)
                .setLogDir(FileUtil.getSelfFilePath(context))
                .setLogSegment(LogSegment.TWENTY_FOUR_HOURS)
                .setCharset("UTF-8")
                .setTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
                .setZoneOffset(ZoneOffset.P0800);

        i(LogUtils.class.getSimpleName(), "init Jlog ok");
    }

    public static void i(String TAG,String str){
        JLog.i(TAG,modifier+str+modifier);
    }

    public static void d(String TAG, String str) {
        JLog.d(TAG, modifier+str+modifier);
    }

    public static void e(String TAG, String str) {
        JLog.e(TAG, modifier+str+modifier);
    }

    public static void v(String TAG, String str) {
        JLog.v(TAG, modifier+str+modifier);
    }
}
