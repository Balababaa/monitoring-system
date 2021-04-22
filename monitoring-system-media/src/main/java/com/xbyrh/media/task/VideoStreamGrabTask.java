package com.xbyrh.media.task;

import com.xbyrh.common.event.VideoFileAddEvent;
import com.xbyrh.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
@Slf4j
public class VideoStreamGrabTask implements Runnable, ApplicationContextAware {

    private AtomicBoolean stop = new AtomicBoolean(false);

    private Long deviceId;

    private String httpFlvUrl;

    private ApplicationContext applicationContext;

    public VideoStreamGrabTask(Long deviceId, String httpFlvUrl) {
        this.deviceId = deviceId;
        this.httpFlvUrl = httpFlvUrl;
    }

    @Override
    public void run() {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(httpFlvUrl);

        try {
            grabber.start();
        } catch (Exception e) {
            log.error("捕捉流失败，结束录制");
            return;
        }

        while (!stop.get()) {
            Date startTime = new Date();
            String startTimeString = DateUtil.dateToString(startTime, "yyyy-MM-dd_HH:mm:ss");

            String fileName = startTimeString + ".mp4";

            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(fileName, 1280, 720);
            recorder.setFrameRate(15D);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // avcodec.AV_CODEC_ID_H264，编码
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
            recorder.setInterleaved(true);
            recorder.setGopSize(30);
            recorder.setVideoBitrate(grabber.getVideoBitrate());

            try {
                recorder.start();
                Frame frame = null;

                while (!stop.get() && (frame = grabber.grabFrame()) != null) {
                    recorder.record(frame);
                    // 每十分钟结束一次录制
                    if (Calendar.getInstance().get(Calendar.MINUTE) % 10 == 0) {
                        log.info("一次录制成功，{}", startTimeString);
                        break;
                    }
                }
                recorder.stop();
                applicationContext.publishEvent(new VideoFileAddEvent(deviceId, fileName, startTime, new Date()));
            } catch (Exception e) {
                log.error("视频保存失败，忽略，开始时间 {}", startTimeString);
            }
        }
    }

    public void stop() {
        stop.set(true);
    }

    public boolean status() {
        return stop.get();
    }

    @Override
    public String toString() {
        return "VideoStreamGrabTask{" +
                "stop=" + stop.get() +
                ", httpFlvUrl='" + httpFlvUrl + '\'' +
                '}';
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
