package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;
import org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants;

import java.util.logging.Level;

import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.LIFT_DOWN_SPEED;
import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.LIFT_HIGH_POSITION;
import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.LIFT_LOW_POSITION;
import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.LIFT_PID_COEFFICIENTS;
import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.LIFT_TOLERANCE;
import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.LIFT_RESTING_POSITION;
import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.LIFT_MID_POSITION;
import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.LIFT_UP_SPEED;
import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.DEL_CLOSE_POS;
import static org.firstinspires.ftc.teamcode.subsystems.constants.SubsystemConstants.Lift.DEL_OPEN_POS;


@Config
/**
 * Lift class controls the scoring lift and ball/box depositer
 */
public class Lift extends SubsystemBase {
    private Telemetry telemetry;
    private MotorEx liftMotor;
    private ServoEx deliveryServo;

    private PIDFController controller;
    private boolean pidEnabled;
    private double encoderOffset = 0;
    private int liftPosition = 0;

    double CURRENT_POSITION = DEL_OPEN_POS;

    public Lift(HardwareMap hw, Telemetry tl) {
        this.liftMotor = new MotorEx(hw, SubsystemConstants.Lift.LIFT_MOTOR_ID);
        this.deliveryServo = new SimpleServo(hw, SubsystemConstants.Lift.DELIVERY_MOTOR_ID, 0,1);

        this.liftMotor.setDistancePerPulse(SubsystemConstants.DEGREES_PER_ROTATION / SubsystemConstants.Lift.LIFT_TICKS_PER_ROTATION);
        liftMotor.setInverted(true);

        controller = new PIDFController(LIFT_PID_COEFFICIENTS.p, LIFT_PID_COEFFICIENTS.i, LIFT_PID_COEFFICIENTS.d, LIFT_PID_COEFFICIENTS.f,  getAngle(), getAngle());
        controller.setTolerance(LIFT_TOLERANCE);


        this.telemetry = tl;
        pidEnabled = false;
        setOffset();
    }
    @Override
    public void periodic() {
        if (pidEnabled) {
            //controller.setF(LIFT_PID_COEFFICIENTS.f * Math.cos(Math.toRadians(controller.getSetPoint())));
            double output = controller.calculate(getAngle());
            liftMotor.set(output);
        }
        Util.logger(this, telemetry, Level.INFO, "current pos: ", liftPosition);
        Util.logger(this, telemetry, Level.INFO, "encoder pos: ", liftMotor.getCurrentPosition());
        Util.logger(this, telemetry, Level.INFO, "del pos: ", deliveryServo.getPosition());
        deliveryServo.setPosition(CURRENT_POSITION);
    }

    public void toggleDel() {
        if(CURRENT_POSITION == DEL_OPEN_POS) {
            CURRENT_POSITION = DEL_CLOSE_POS;
        }
        else {
            CURRENT_POSITION = DEL_OPEN_POS;
        }
    }
    public void closeDel() {
        CURRENT_POSITION = DEL_CLOSE_POS;
    }

    public void openDel() {
        CURRENT_POSITION = DEL_OPEN_POS;
    }

    public void LowerLiftCommand () {
        CURRENT_POSITION = DEL_CLOSE_POS;
        pidEnabled = true;
        controller.setSetPoint(LIFT_RESTING_POSITION);

        liftPosition = 0;
        CURRENT_POSITION = DEL_CLOSE_POS;
    }






    public void toggleAutomatic() {
        pidEnabled = !pidEnabled;
    }
    public boolean isPidEnabled() {
        return pidEnabled;
    }

    private double getEncoderDistance() {
        return liftMotor.getDistance() - encoderOffset;
    }

    public void liftManual() {
        pidEnabled = false;
        liftMotor.set(LIFT_UP_SPEED);
    }

    public void lowerLiftManual() {
        pidEnabled = false;
        liftMotor.set(LIFT_DOWN_SPEED);
    }

    public void stopLift() {
        liftMotor.stopMotor();
        controller.setSetPoint(getAngle());
        pidEnabled = false;
    }

    public void setPidEnabled(boolean auto) {
        this.pidEnabled = auto;
    }

    public void resetEncoder() {
        encoderOffset = liftMotor.getDistance();
    }

    public double getAngle() {
        return getEncoderDistance();
    }

    /************************************************************************************************/
    public void liftLowAuton() {
        pidEnabled = true;
        controller.setSetPoint(LIFT_LOW_POSITION);

        liftPosition = 0;
    }

    public void liftLow() {
        pidEnabled = true;
        controller.setSetPoint(LIFT_RESTING_POSITION);

        liftPosition = 0;
    }
    public void liftMid() {
        pidEnabled = true;
        controller.setSetPoint(LIFT_MID_POSITION);

        liftPosition = 1;
    }

    public void liftHigh() {
        pidEnabled = true;
        controller.setSetPoint(LIFT_HIGH_POSITION);

        liftPosition = 2;
    }

    public void setLift(double angle) {
        pidEnabled = true;
        controller.setSetPoint(angle);
    }

    public boolean atTargetAngle() {
        return controller.atSetPoint();
    }

    public void setOffset() {
        resetEncoder();
        controller.setSetPoint(getAngle());
    }

    public void moveUp() {
        liftPosition = liftPosition + 1;
        if(liftPosition > 2) {
            liftPosition = 2;
        }
        moveLiftToCorrectHeight();
    }

    public void moveDown() {
        liftPosition = liftPosition - 1;
        if(liftPosition < 0) {
            liftPosition = 0;
        }
        moveLiftToCorrectHeight();
    }

    public void moveLiftToCorrectHeight() {
        if(liftPosition == 0) {
            liftLow();
        } else if(liftPosition == 1) {
            liftMid();
        } else if(liftPosition == 2) {
            liftHigh();
        }
    }
}

