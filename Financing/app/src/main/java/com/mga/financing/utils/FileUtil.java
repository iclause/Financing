package com.mga.financing.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileUtil {

    private static String rootdir = "/";
    private static String TAG = FileUtil.class.getSimpleName();

    /**
     * 检查是否SD卡正常
     *
     * @return
     */
    public static boolean checkSDCard(int sizeMb) {
        boolean ishasSpace = true;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            String sdcard = Environment.getExternalStorageDirectory().getPath();
            StatFs statFs = new StatFs(sdcard);
            long blockSize = statFs.getBlockSize();
            long blocks = statFs.getAvailableBlocks();
            long availableSpare = (blocks * blockSize) / (1024 * 1024);
            Log.d("剩余空间", "availableSpare = " + availableSpare);
            if (availableSpare > sizeMb) {
                ishasSpace = true;
            }
        }
        return ishasSpace;
    }

    //若有SD卡则放在SD卡，无则放在程序目录下
    public static String getSdPath(Context ct) {
        if (!checkSDCard(5)) { // 不存在SD卡或者SD卡空间不足
            String filePath = ct.getCacheDir().getAbsolutePath();
//			Log.d(TAG,"getSdPath"+filePath);
            return filePath;
        }
//		Log.d(TAG,"getSdPath"+ct.getExternalCacheDir().getAbsolutePath());
        return ct.getExternalCacheDir().getAbsolutePath();
    }

    public static String getBootPath(Context ct) {
//		Log.d(TAG,"getBootPath"+FileUtil.getSdPath(ct) +rootdir);
        return FileUtil.getSdPath(ct) +rootdir ;
    }



    public static String getBootPathPath(Context ct) {
        return FileUtil.getSdPath(ct) ;
    }
    /**
     * ebjar日志文件夹半路径
     * @param ct
     * @return
     */
    public static String getSelfFilePath(Context ct) {
        String bootpath = getBootPath(ct);
        Log.d(TAG,"getSelfFilePath"+bootpath.substring(bootpath.indexOf("/Android"),bootpath.length())+ "profiles_ebjarself");
        return  getBootPath(ct).substring(getBootPath(ct).indexOf("Android"))+ "profiles_ebjarself";
//		return  getBootPath(ct) + "profiles_ebjarself";
    }

    /**
     * ebjar日志文件夹全路径
     * @param ct
     * @return
     */
    public static String getSelfFilePath2(Context ct) {
        Log.d(TAG,"getSelfFilePath2"+getBootPath(ct)+ "profiles_ebjarself");
        return  getBootPath(ct) + "profiles_ebjarself";
//		return  getBootPath(ct) + "profiles_ebjarself";
    }




    /**
     * 删除缓存
     */
    public static void deleteCache(Context ct) {
        Log.i("deleteCache", "deleteCache");
        File bootDir = new File(getSelfFilePath(ct));
        if (!bootDir.exists()) {
            return;
        }
        File[] files = bootDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            files[i].delete();
        }
    }
    /**
     * 删除缓存
     */
    public static void deleteCache2(Context ct) {
        Log.i("deleteCache", "deleteCache");
        File bootDir = new File(getBootPath(ct));
        if (!bootDir.exists()) {
            return;
        }
        delete(bootDir);
    }
    //递归删除文件及文件夹
    public static void delete(File file) {

        if (file.isFile()) {
            file.delete();
            return;
        }

        if(file.isDirectory()){
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }

    /**
     * 检查路径是否存在
     */
    public static void checkDir(Context ct) {
        if (!checkSDCard(5)) {
            return;
        }
        File bootFile = new File(getBootPath(ct));
        if (!bootFile.exists()) {
            boolean result = bootFile.mkdirs();
        }
        File picFile = new File(getSelfFilePath(ct));
        if (!picFile.exists()) {
            picFile.mkdirs();
        }
        // File saveFile = new File(getSavePath());
        // if (!saveFile.exists()) {
        // saveFile.mkdirs();
        // }


    }

    /**
     * 检查ebjar日志文件是否存在
     * @param ct
     * @return
     */
    public static boolean checkEbjarLogFile(Context ct, int filescount) {
        if (!checkSDCard(5)) {
            return false;
        }
        File ebjarFile = new File(getSelfFilePath2(ct));


        if (ebjarFile.isDirectory()) {
            if (!(ebjarFile.listFiles().length > 0)) {
                Log.e(TAG, "ebjar log file does not exist");
                return false;
            }else{
                if(ebjarFile.listFiles().length>filescount){
                    List<File> childFiles = orderByName(ebjarFile.listFiles());
                    List<File> childdelFiles = new ArrayList<>();
                    for (int i = filescount; i < childFiles.size(); i++) {
                        childdelFiles.add(childFiles.get(i));
                    }
                    for (File f : childdelFiles) {
                        Log.i(TAG, "ebjar log file  delete:"+f.getName());
                        delete(f);
                    }
                }
                Log.i(TAG, "ebjar log file  exist");
                return true;
            }
        }else{
            Log.e(TAG, "ebjar log file dir does not exist2");
            return false;
        }
    }

    //按照文件名称排序 时间逆序
    public static List<File> orderByName(File[] Files) {
        List<File> files = Arrays.asList(Files);
        Collections.sort(files, new Comparator< File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o2.getName().compareTo(o1.getName());
            }
        });
        return files;
    }


    public static int CopySdcardFile(String fromFile, String toFile) {

        try {
//			Log.v(TAG, fromFile + "  " + toFile);
            File toFile2 = new File(toFile);
            if (toFile2.exists()) {
                toFile2.delete();
            }
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;

        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long 单位为b
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        File[] fileList = file.listFiles();
//    	Log.i("getFolderSize", "fileList.length:"+fileList.length);
        for (int i = 0; i < fileList.length; i++) {
//        	Log.i("getFolderSize", "name:"+fileList[i].toString());
            if (fileList[i].isDirectory()) {
                size = size + getFolderSize(fileList[i]);
            } else {
                size = size + fileList[i].length();
            }
        }
        return size;
    }

    //获取缓存文件大小
    public static long getCacheFolderSize(Context ct) {
        long size = 0;
        File bootDir = new File(getBootPath(ct));
        if (!bootDir.exists()) {
            return size;
        }
        try {
            size = getFolderSize(bootDir);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return size;
    }

    public static void doDownLoadFile(Handler handler) {
        final String requesl = "http://txt.bxwxtxt.com/packdown/fulltxt/107/107656.txt?46";
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(requesl);
            //1 提取url中的文件名
            int location = requesl.lastIndexOf('/');
            String filename = requesl.substring(location + 1);
            //2.打开连接
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //3 获得相应头
            Map<String, List<String>> map = httpURLConnection.getHeaderFields();
            Set<String> keySet = map.keySet();
            StringBuilder builder = new StringBuilder();
            builder.append("filename : " + filename + "\n");
            List<String> values;
            if (keySet != null) {
                for (String s : keySet
                        ) {
                    builder.append(s + ":");
                    values = map.get(s);
                    for (String ss : values
                            ) {
                        builder.append(ss);
                    }
                    builder.append("\n");
                }
            }
            //4. 获得读入流
            InputStream in = httpURLConnection.getInputStream();
            //4.1获得文件长度
            int length = Integer.valueOf(map.get("Content-Length").get(0));
            byte[] bbb = new byte[length];
            //读出文件
            in.read(bbb);
            String ssss = new String(bbb);
            builder.append(ssss);
            //通知UI更新
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("name", builder.toString());
            message.setData(bundle);
            handler.sendMessage(message);

            //关闭流
            in.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doSaveFileInDefaultFileDir(Context context, String filename, byte[] bb) {
        //1.首先判断文件是否存在，存在就什么都不做，不存在就保存它
        File file = new File(context.getFilesDir(), filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(bb);
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 需要知道当前SD卡的目录，Environment.getExternalStorageDierctory()
     */


    // 在sdcard卡上创建文件
    public File createSDDownloadFile(String fileName, Context ct) throws IOException {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
//			file.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }


    // 在sd卡上创建目录
    public File createSDDownloadDir(String dirName, Context ct) {
        File dir = new File(getSelfFilePath(ct) + dirName);
// mkdir只能创建一级目录 ,mkdirs可以创建多级目录
        dir.mkdir();
        return dir;
    }


    // 判断sd卡上的文件夹是否存在
    public boolean isDownloadFileExist(String fileName, Context ct) {
        File file = new File(getSelfFilePath(ct) + fileName);
        return file.exists();
    }


    public void deleteDownloadFile(String fileName, Context ct) {
        File file = new File(getSelfFilePath(ct) + fileName);
        file.delete();
    }


    /**
     * 将一个inputstream里面的数据写入SD卡中 第一个参数为目录名 第二个参数为文件名
     */
    public File write2SDFromInput(Context ct, String path, InputStream inputstream) {
        File file = null;
        OutputStream output = null;
        try {
            file = createSDDownloadFile(path, ct);
            output = new FileOutputStream(file);
// 4k为单位，每4K写一次
            byte buffer[] = new byte[4 * 1024];
            int temp = 0;
            while ((temp = inputstream.read(buffer)) != -1) {
// 获取指定信,防止写入没用的信息
                output.write(buffer, 0, temp);
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return file;
    }

    private void getFileList(File path, List<String> fileList) {
        //如果是文件夹的话
        if (path.isDirectory()) {

            //返回文件夹中有的数据
            File[] files = path.listFiles();
            //先判断下有没有权限，如果没有权限的话，就不执行了
            if (null == files) {
                return;
            }

            for (int i = 0; i < files.length; i++) {
//				Log.v("FileService", files[i].getName());
                getFileList(files[i], fileList);
            }
        } else {
//			Log.i("FileService", path.getAbsolutePath());
            //进行文件的处理
            String filePath = path.getAbsolutePath();
            //文件名
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            //添加
            fileList.add(fileName);
        }
    }

}
