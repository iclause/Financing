# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android-sdk\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-----------------混淆配置设定------------------------------------------------------------------------
-optimizationpasses 5                                                       #指定代码压缩级别
-dontusemixedcaseclassnames                                                 #混淆时不会产生形形色色的类名
-dontskipnonpubliclibraryclasses                                            #指定不忽略非公共类库
-dontpreverify                                                              #不预校验，如果需要预校验，是-dontoptimize
-ignorewarnings                                                             #屏蔽警告
-verbose                                                                    #混淆时记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*    #优化
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers

# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*



-dontwarn com.umeng.**
-dontwarn android.support.**
-dontwarn org.apache.**
#-----------------不需要混淆系统组件等-------------------------------------------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.Fragment #如果有引用v4包可以添加下面这行
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.view.View
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}

-keep class financing.bean.** { *; } # #过滤掉自己编写的实体类
-keep class com.mga.financing.bean.** { *; } # #过滤掉自己编写的实体类
-keep class com.mga.financing.bean.** { *; } # #过滤掉自己编写的实体类


-keep class com.mga.financing.ui.** { *; } #自定义控件不参与混淆


-keep class com.facebook.**{*;}
-keep class com.umeng.**{*;}
-keep class com.zcw.**{*;}
-keep class org.apache.**{*;}

#-----------ebauth------------#
-keep class com.mga.financing.biz.** { *; } # 保持自己定义的类不被混淆，写上类的时候，要把类所在的报名带上。
-keep class com.ebupt.jlog1.** { *; }
#-----------ebauth------------#

#-----------ebjar------------#
-keep class com.mga.financing.** { *; } # 保持自己定义的类不被混淆，写上类的时候，要把类所在的报名带上。
-keep class com.ebupt.jlog.** { *; }
-keep class com.justalk.cloud.** { *; }
-keep interface com.justalk.cloud.** { *; }
-keep class com.justalk.ui.MtcNotify { *; }
#-----------ebjar------------#


#-----------jpush------------#
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

#-----------jiguang------------#
-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
-keep class com.google.protobuf.** {*;}

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
public <init>(android.content.Context, android.util.AttributeSet, int);

}
-keepclassmembers class * extends android.app.Activity {  # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-dontwarn com.nostra13.universalimageloader.core.download.*
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
 public static **[] values();
 public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
 public static final android.os.Parcelable$Creator *;

}
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}
# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#-------------greendao--------#
-keep class de.greenrobot.dao.** {*;}
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
  public static java.lang.String TABLENAME;
}
-keep class **$Properties

#-----------gson------------#
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }

#-----------huaweipush------------#
-ignorewarning -keepattributes *Annotation*
-keepattributes Exceptions -keepattributes InnerClasses
-keepattributes Signature
# hmscore-support: remote transport
-keep class * extends com.huawei.hms.core.aidl.IMessageEntity { *; }
# hmscore-support: remote transport
-keepclasseswithmembers class * implements com.huawei.hms.support.api.transport.DatagramTransport {<init>(...); }
# manifest: provider for updates
-keep public class com.huawei.hms.update.provider.UpdateProvider { public *; protected *; }
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn org.apache.http.**
-ignorewarnings

# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
#-----------rxjava-rxandroid-----------#
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}



#-----------shareSDK------------#
-keep class cn.sharesdk.**{*;}
	-keep class com.sina.**{*;}
	-keep class **.R$* {*;}
	-keep class **.R{*;}
	-keep class com.mob.**{*;}
	-dontwarn com.mob.**
	-dontwarn cn.sharesdk.**
	-dontwarn **.R$*
#-----------wangyiqiyu------------#
	-dontwarn com.qiyukf.**
    -keep class com.qiyukf.** {*;}

