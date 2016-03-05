package com.idreamsky.dreamroom.util;

import android.content.Context;
import android.widget.ImageView;

import com.idreamsky.dreamroom.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Universal图片管理工具类
 */
public class UniversalUtil {

    private static ImageLoader imageLoader;
    private static ImageLoaderConfiguration configuration;
    private static DisplayImageOptions options;

    public static void initUniversal(Context context){
        imageLoader = ImageLoader.getInstance();

        //缺省配置
        //ImageLoaderConfiguration config1 = ImageLoaderConfiguration.createDefault(context);

        //自定义全局配置
        configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)//线程优先级
                .threadPoolSize(5)//线程池数量
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(configuration);

        //缺省配置
        //DisplayImageOptions options1 = DisplayImageOptions.createSimple();

        //自定义下载配置
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.checked)// 加载等待 时显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)// 加载数据为空时显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)// 加载失败时显示的图片
                .resetViewBeforeLoading(true)//加载前是否重置View
                .cacheInMemory(true)//是否启用内存缓存
                .cacheOnDisk(true)//是否启用磁盘缓存
//                .delayBeforeLoading(1000)//开始加载的延迟时间
                .build();
    }

    /**
     * 下载图片请求
     * @param url
     * @param iv
     * @param listener
     */
    public static void displayImage(String url, ImageView iv, ImageLoadingListener listener){
        if(listener != null){
            imageLoader.displayImage(url, iv, options, listener);
        } else {
            imageLoader.displayImage(url, iv, options);
        }
    }

    /**
     * 加载网络图片至位图对象
     * @param url
     * @return
     */
    public static void displayBitmap(String url, ImageLoadingListener listener){
        imageLoader.loadImage(url, options, listener);
    }

    /**
     * 清理缓存
     */
    public static void clearCache(){
        if(imageLoader != null) {
            imageLoader.clearMemoryCache();
            imageLoader.clearDiskCache();
        }
    }
}
