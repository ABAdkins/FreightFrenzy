package org.firstinspires.ftc.teamcode.pipelines;

//package com.arcrobotics.ftclib.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

public class FFRectDetector {

    private OpenCvCamera camera;
    private boolean isUsingWebcam;
    private String webcamName;
    private final HardwareMap hardwareMap;
    private TeamMarkerPipeline teamMarkerPipeline;

    public static int CAMERA_WIDTH = 320, CAMERA_HEIGHT = 240;
    public static OpenCvCameraRotation ORIENTATION = OpenCvCameraRotation.UPRIGHT;

    // The constructor is overloaded to allow the use of webcam instead of the phone camera
    public FFRectDetector(HardwareMap hMap) {
        hardwareMap = hMap;
    }

    public FFRectDetector(HardwareMap hMap, String webcamName) {
        hardwareMap = hMap;
        isUsingWebcam = true;
        this.webcamName = webcamName;
    }

    public void init() {
        //This will instantiate an OpenCvCamera object for the camera we'll be using
        if (isUsingWebcam) {
            int cameraMonitorViewId = hardwareMap
                    .appContext.getResources()
                    .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            camera = OpenCvCameraFactory.getInstance()
                    .createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);
        } else {
            int cameraMonitorViewId = hardwareMap
                    .appContext.getResources()
                    .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            camera = OpenCvCameraFactory.getInstance()
                    .createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        }

        //Set the pipeline the camera should use and start streaming
        camera.setPipeline(teamMarkerPipeline = new TeamMarkerPipeline());
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.openCameraDevice();
                camera.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, ORIENTATION);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    public TeamMarkerPipeline.Position getPosition() {
        return teamMarkerPipeline.getPosition();
    }
/*
    public void setThreshold(int threshold) {
        teamMarkerPipeline.setThreshold(threshold);
    }

 */

    public double getLeftAverage() {
        return teamMarkerPipeline.getLeftAverage();
    }

    public double getCenterAverage() {
        return teamMarkerPipeline.getCenterAverage();
    }

    public double getRightAverage() {
        return teamMarkerPipeline.getRightAverage();
    }

    public void setLeftRectangle(double x, double y) {
        teamMarkerPipeline.setLeftRectangle(x,y);
    }
    public void setCenterRectangle(double x, double y) {
        teamMarkerPipeline.setCenterRectangle(x,y);
    }
    public void setRightRectangle(double x, double y) {
        teamMarkerPipeline.setRightRectangle(x,y);
    }
    public void setRectangleSize(int w, int h) {
        teamMarkerPipeline.setRectangleSize(w,h);
    }

}
