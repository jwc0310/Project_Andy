package example.andy.com.emandy.opensource;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.concurrent.Executor;

import example.andy.com.emandy.R;

/**
 * Created by Andy on 16/5/25.
 */
public class UILHelper {

    /**
     * UIL全局初始化
     * @param mContext
     */
    public static void init(Context mContext){
        ImageLoader instance = ImageLoader.getInstance();

        // 线程池的优先级过高，改成框架默认的优先级
        /** 网络连接的线程池 */
        Executor executor = DefaultConfigurationFactory.createExecutor(
                10, Thread.NORM_PRIORITY - 1,
                QueueProcessingType.FIFO);

        DiskCache diskCache = DefaultConfigurationFactory.createDiskCache(
                mContext, new Md5FileNameGenerator(), 0, 0);

        // 一些低端机上可用内存比较小，可能没法分配20M给图片缓存框架使用。
        int maxCache = 20 * 1024 * 1024;
        int maxRuntimeCache = (int) (Runtime.getRuntime().maxMemory() / 8);
        if (maxCache > maxRuntimeCache) {
            maxCache = maxRuntimeCache;
        }
        /** 内存缓存 */
        MemoryCache memoryCache = DefaultConfigurationFactory
                .createMemoryCache(mContext,maxCache);

        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(mContext);

        ImageLoaderConfiguration configuration = builder
                .memoryCache(memoryCache)
                .memoryCacheExtraOptions(1920, 1080)// 设置内存缓存可接受的最大尺寸
                .taskExecutor(executor)// 加载和显示图片的线程池
                .taskExecutorForCachedImages(executor)// 磁盘缓存的线程池
                .denyCacheImageMultipleSizesInMemory()//
                .diskCache(diskCache)// 磁盘缓存
                //.writeDebugLogs()// 开启日志
                .build();
        instance.init(configuration);
    }

    public class DisplayOptions {

        public final DisplayImageOptions GAO_NENG = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.placeholder_100)
                .showImageOnFail(R.mipmap.placeholder_100)
                .showImageOnLoading(R.mipmap.placeholder_100)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
    }
}
