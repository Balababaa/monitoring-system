package com.xbyrh.media.task;

import com.xbyrh.common.event.VideoFileAddEvent;
import com.xbyrh.common.utils.DateUtil;
import com.xbyrh.common.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
@Slf4j
public class VideoStreamGrabTask implements Runnable {

    private AtomicBoolean stop = new AtomicBoolean(false);

    private Long deviceId;

    private String httpFlvUrl;

    private FFmpegFrameGrabber grabber;

    public VideoStreamGrabTask(Long deviceId, String httpFlvUrl) {
        this.deviceId = deviceId;
        this.httpFlvUrl = httpFlvUrl;
    }

    public VideoStreamGrabTask(Long deviceId, String httpFlvUrl, FFmpegFrameGrabber grabber) {
        this.deviceId = deviceId;
        this.httpFlvUrl = httpFlvUrl;
        this.grabber = grabber;
    }

    @Override
    public void run() {
        if (grabber == null) {
            grabber = new FFmpegFrameGrabber(httpFlvUrl);
            try {
                grabber.start();
            } catch (Exception e) {
                log.error("捕捉流失败，结束录制");
                e.printStackTrace();
                return;
            }
        }

        Date startTime = new Date();
        String startTimeString = DateUtil.dateToString(startTime, "yyyy-MM-dd-HH-mm-ss");
        String fileName = deviceId+"-"+startTimeString + ".mp4";
        log.info("开始录制，文件名: {}", fileName);

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
            Calendar instance = null;
            while (!stop.get() && (frame = grabber.grabFrame()) != null) {
                recorder.record(frame);
                // 每一分钟结束一次录制
                instance = Calendar.getInstance();
                if (instance.getTime().getTime() - startTime.getTime() >= 1000 * 80) {
                    log.info("一次录制成功，{} {}", deviceId, startTimeString);
                    break;
                }
            }
            recorder.stop();
            SpringUtil.getApplicationContext()
                    .publishEvent(new VideoFileAddEvent(deviceId, fileName, startTime, instance.getTime()));
        } catch (Exception e) {
            log.error("视频保存失败，忽略，开始时间 {}", startTimeString);
            e.printStackTrace();
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
        return "VideoStreamGrabTask{" + "stop=" + stop.get() + ", httpFlvUrl='" + httpFlvUrl + '\'' + '}';
    }
}
