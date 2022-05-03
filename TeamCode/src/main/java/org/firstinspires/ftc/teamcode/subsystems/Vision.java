package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;
import org.firstinspires.ftc.teamcode.pipelines.FFRectDetector;
import org.firstinspires.ftc.teamcode.pipelines.TeamMarkerPipeline;
import org.opencv.core.Mat;

import java.util.logging.Level;
//import org.firstinspires.ftc.teamcode.Util;


public class Vision extends SubsystemBase {
    private Telemetry telemetry;
    private FFRectDetector duckDetector;
    private TeamMarkerPipeline.Position currentPos;

    public Vision(HardwareMap hw, String webcamName, Telemetry tl, double LX, double LY, double CX, double CY, double RX, double RY) {

        /*duckDetector = new FFRectDetector(hw, webcamName);
        duckDetector.init();

        //switch left and right if camera mounted upside down
        duckDetector.setRightRectangle(RX, RY);//r
        duckDetector.setCenterRectangle(CX, CY);
        duckDetector.setLeftRectangle(LX, LY);//l
        telemetry = tl;
        currentPos = duckDetector.getPosition();*/
    }

    @Override
    public void periodic() {
        currentPos = duckDetector.getPosition();

        //Util.logger(this, telemetry, Level.INFO, "Current Position", currentPos);
    }

    public TeamMarkerPipeline.Position getCurrentPosition() {
        return currentPos;
    }
}