package com.xbyrh.media.context;

import com.xbyrh.media.task.VideoStreamGrabTask;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * create at 2021/4/22
 *
 * @author chenxinhui
 */
@Slf4j
public class VideoStreamGrabTaskContext {

    private static ExecutorService executorService =new ThreadPoolExecutor(20, 20,
                                                                    0L, TimeUnit.MILLISECONDS,
                                                                    new LinkedBlockingQueue<Runnable>());



    private static HashMap<Long, VideoStreamGrabTask> context = new HashMap<>();

    public static void execute(VideoStreamGrabTask videoStreamGrabTask){
        executorService.execute(videoStreamGrabTask);
    }

    public static void add(Long id,VideoStreamGrabTask videoStreamGrabTask){
        context.put(id, videoStreamGrabTask);
        executorService.execute(videoStreamGrabTask);
        log.info("启动视频录制，deviceId：{}", id);
    }

    public static void stop(Long id) {
        VideoStreamGrabTask videoStreamGrabTask = context.get(id);

        if (videoStreamGrabTask != null && !videoStreamGrabTask.status()) {
            videoStreamGrabTask.stop();
        }
    }

}
