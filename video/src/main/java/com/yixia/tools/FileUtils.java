package com.yixia.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Environment;

import java.io.File;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-05-05 18:13
 */

public class FileUtils {
    /**
     * 获取硬盘存储路径
     * @param mContext
     * @return
     */
    public static String getDiskCacheDir(Context mContext, String dirName) {
        boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if(externalStorageAvailable){
            if(mContext.getExternalCacheDir() != null){
                cachePath = mContext.getExternalCacheDir().getPath();
            }else {
                cachePath = mContext.getCacheDir().getPath();
            }
        }else {
            cachePath = mContext.getCacheDir().getPath();
        }
        return  cachePath + File.separator + dirName;
    }

    /**
     * 其中函数getFrameAtTime()有其他重载函数，
     * 该函数会随机选择一帧抓取，如果想要指定具体时
     * 间的缩略图，可以用函数getFrameAtTime(long timeUs),
     *  getFrameAtTime(long timeUs, int option)
     * @param filePath
     * @return
     */
    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
