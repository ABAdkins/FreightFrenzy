package org.firstinspires.ftc.teamcode.autons.regionals.red.warehouse;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.autons.champs.blue.warehouse.LSimpleWarehouseBlueCommand;
import org.firstinspires.ftc.teamcode.drive.MatchOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.pipelines.TeamMarkerPipeline;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheels;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.subsystems.constants.VisionConstants;

import java.util.HashMap;

//@Disabled
@Autonomous(name = "Red Regionals Warehouse", group = "RED W")
public class RedRegionalsWarehouseAuton extends MatchOpMode {
    public static double startPoseX = 0;
    public static double startPoseY = 0;
    public static double startPoseHeading = 180;

    // Motors
    private MotorEx leftFront, leftRear, rightRear, rightFront;
    private MotorEx intakeMotor;

    // Gamepad
    private GamepadEx driverGamepad;

    // Subsystems
    private Drivetrain drivetrain;
    private Intake intake;
    private Lift lift;
    private DuckWheels duckWheels;
    private Vision vision;


    @Override
    public void robotInit() {
        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        drivetrain.init();

        intake = new Intake(hardwareMap, telemetry);
        lift = new Lift(hardwareMap,telemetry);
        duckWheels = new DuckWheels(hardwareMap,telemetry);
        vision = new Vision(hardwareMap, "Webcam 1", telemetry, VisionConstants.RED_WAREHOUSE_VISION.LEFT_X , VisionConstants.RED_WAREHOUSE_VISION.LEFT_Y, VisionConstants.RED_WAREHOUSE_VISION.CENTER_X, VisionConstants.RED_WAREHOUSE_VISION.CENTER_Y, VisionConstants.RED_WAREHOUSE_VISION.RIGHT_X, VisionConstants.RED_WAREHOUSE_VISION.RIGHT_Y);

        //drivetrain.setPoseEstimate(Trajectories.BlueLeftTape.startPose);
        drivetrain.setPoseEstimate(new Pose2d(startPoseX, startPoseY, Math.toRadians(startPoseHeading)));
    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void matchStart() {
        schedule(
                new SelectCommand(new HashMap<Object, Command>() {{
                    put(TeamMarkerPipeline.Position.LEFT, new SequentialCommandGroup(
                            //new BlueRegionalsCarouselL(drivetrain, lift, duckWheels, telemetry)
                    ));
                    put(TeamMarkerPipeline.Position.MIDDLE, new SequentialCommandGroup(
                            //new BlueRegionalsCarouselL(drivetrain, lift, duckWheels, telemetry)
                    ));
                    put(TeamMarkerPipeline.Position.RIGHT, new SequentialCommandGroup(
                            //new BlueRegionalsCarouselL(drivetrain, lift, duckWheels, telemetry)
                    ));
                }}, vision::getCurrentPosition)
        );

    }
}