package org.firstinspires.ftc.teamcode.pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class NewTeamMarkerPipeline extends OpenCvPipeline {
    public Scalar minThreshold = new Scalar(0, 110, 37);
    public Scalar maxThreshold = new Scalar(33, 255, 255);

    //The position related to the screen
    public double leftRectWidthPercentage = 0.05;
    public double leftRectHeightPercentage = 0.3;
    public double centerRectWidthPercentage = 0.47;
    public double centerRectHeightPercentage = 0.3;
    public double rightRectWidthPercentage = 0.72;
    public double rightRectHeightPercentage = 0.3;

    //Where the average value of the rectangles are stored
    private double leftAverage;
    private double centerAverage;
    private double rightAverage;

    //The width and height of the rectangles in terms of pixels
    public int rectangleWidth = 40;
    public int rectangleHeight = 25;

    @Override
    public Mat processFrame(Mat input) {
        Mat matHSV = new Mat();

        Imgproc.cvtColor(input, matHSV, Imgproc.COLOR_RGB2HSV);

        Mat threshold = new Mat();

        Core.inRange(matHSV, minThreshold, maxThreshold, threshold);

        //The points needed for the rectangles are calculated here
        Rect leftRect = new Rect(
                (int) (matHSV.width() * leftRectWidthPercentage),
                (int) (matHSV.height() * leftRectHeightPercentage),
                rectangleWidth,
                rectangleHeight
        );

        //The points needed for the rectangles are calculated here
        Rect centerRect = new Rect(
                (int) (matHSV.width() * centerRectWidthPercentage),
                (int) (matHSV.height() * centerRectHeightPercentage),
                rectangleWidth,
                rectangleHeight
        );

        Rect rightRect = new Rect(
                (int) (matHSV.width() * rightRectWidthPercentage),
                (int) (matHSV.height() * rightRectHeightPercentage),
                rectangleWidth,
                rectangleHeight
        );

        drawRectOnToMat(input, leftRect, new Scalar(255, 0, 0));
        drawRectOnToMat(input, centerRect, new Scalar(255, 0, 0));
        drawRectOnToMat(input, rightRect, new Scalar(255, 0, 0));

        //We crop the image so it is only everything inside the rectangles and find the cb value inside of them
        Mat leftBlock = threshold.submat(leftRect);
        Mat centerBlock = threshold.submat(centerRect);
        Mat rightBlock = threshold.submat(rightRect);

        //We take the average
        Scalar leftMean = Core.mean(leftBlock);
        Scalar centerMean = Core.mean(centerBlock);
        Scalar rightMean = Core.mean(rightBlock);

        leftAverage = leftMean.val[0];
        centerAverage = centerMean.val[0];
        rightAverage = rightMean.val[0];
        if(leftAverage > centerAverage && leftAverage > centerAverage) {
            drawRectOnToMat(input, leftRect, new Scalar(0, 255, 0));
        }
        else if (centerAverage > leftAverage && centerAverage > rightAverage) {
            drawRectOnToMat(input, centerRect, new Scalar(0, 255, 0));
        } else {
            drawRectOnToMat(input, rightRect, new Scalar(0, 255, 0));
        }


        return input;
    }
    private void drawRectOnToMat(Mat mat, Rect rect, Scalar color) {
        Imgproc.rectangle(mat, rect, color, 1);
    }










    public double getLeftAverage() {
        return leftAverage;
    }


    public double getCenterAverage() {
        return centerAverage;
    }

    public double getRightAverage() {
        return rightAverage;
    }

    public void setLeftRectWidthPercentage(double topRectWidthPercentage) {
        this.leftRectWidthPercentage = topRectWidthPercentage;
    }

    public void setLeftRectHeightPercentage(double topRectWidthPercentage) {
        this.leftRectHeightPercentage = topRectWidthPercentage;
    }

    public void setCenterRectWidthPercentage(double topRectHeightPercentage) {
        this.centerRectWidthPercentage = topRectHeightPercentage;
    }

    public void setCenterRectHeightPercentage(double topRectHeightPercentage) {
        this.centerRectHeightPercentage = topRectHeightPercentage;
    }

    public void setRightRectWidthPercentage(double topRectHeightPercentage) {
        this.rightRectWidthPercentage = topRectHeightPercentage;
    }

    public void setRightRectHeightPercentage(double topRectHeightPercentage) {
        this.rightRectHeightPercentage = topRectHeightPercentage;
    }

    public void setRectangleWidth(int rectangleWidth) {
        this.rectangleWidth = rectangleWidth;
    }

    public void setRectangleHeight(int rectangleHeight) {
        this.rectangleHeight = rectangleHeight;
    }
}
