package org.firstinspires.ftc.teamcode.autons.regionals.red.carousel.tests;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Util;
import org.firstinspires.ftc.teamcode.drive.MatchOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheels;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.subsystems.constants.VisionConstants;

import java.util.logging.Level;

@Disabled
@Autonomous(name = "Red Right Regionals Carousel Test", group = "RED C")
public class RedRightRegionalsCarouselTest extends MatchOpMode {
    public static double startPoseX = 0;
    public static double startPoseY = 0;
    public static double startPoseHeading = 180;

    // Motors
    private MotorEx leftFront, leftRear, rightRear, rightFront;
    private MotorEx intakeMotor;
    private MotorEx liftMotor;
    private ServoEx deliveryServo;

    // Gamepad
    private GamepadEx driverGamepad;

    // Subsystems
    private Drivetrain drivetrain;
    private Intake intake;
    private Lift lift;
    private Vision vision;
    private DuckWheels duckWheels;

    @Override
    public void robotInit() {
        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        drivetrain.init();

        vision = new Vision(hardwareMap, "Webcam 1", telemetry, VisionConstants.BLUE_CAROUSEL_VISION.LEFT_X , VisionConstants.BLUE_CAROUSEL_VISION.LEFT_Y, VisionConstants.BLUE_CAROUSEL_VISION.CENTER_X, VisionConstants.BLUE_CAROUSEL_VISION.CENTER_Y, VisionConstants.BLUE_CAROUSEL_VISION.RIGHT_X, VisionConstants.BLUE_CAROUSEL_VISION.RIGHT_Y);
        drivetrain.setPoseEstimate(new Pose2d(startPoseX, startPoseY, Math.toRadians(startPoseHeading)));
        intake = new Intake(hardwareMap, telemetry);
        lift = new Lift(hardwareMap, telemetry);
        duckWheels = new DuckWheels(hardwareMap, telemetry);
    }

    @Override
    public void disabledPeriodic() {
        Util.logger(this, telemetry, Level.INFO, "Current Position", vision.getCurrentPosition());
        vision.periodic();
    }

    @Override
    public void matchStart() {
        schedule(
                            //new RedRegionalsCarouselL(drivetrain, lift, duckWheels, telemetry)
        );

    }
}