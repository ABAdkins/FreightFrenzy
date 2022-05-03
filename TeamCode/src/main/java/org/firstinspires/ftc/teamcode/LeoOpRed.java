package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DeliverCommand;
import org.firstinspires.ftc.teamcode.commands.LowerLiftNoLimitSwitchCommand;
import org.firstinspires.ftc.teamcode.commands.drive.teleOp.LeosFastDriveCommand;
import org.firstinspires.ftc.teamcode.commands.drive.teleOp.ReallyReallySlowDriveCommand;
import org.firstinspires.ftc.teamcode.commands.drive.teleOp.SlowDriveCommand;
import org.firstinspires.ftc.teamcode.drive.MatchOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheels;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

@Config
@TeleOp(name = "LeoOp Red", group = "leo")
public class LeoOpRed extends MatchOpMode {
    // Gamepad
    private GamepadEx driverGamepad, operatorGamepad;

    // Subsystems
    private Drivetrain drivetrain;
    private Lift lift;
    private Intake intake;
    private DuckWheels duckWheels;

    //Buttons
    private Button intakeButton, outtakeButton;
    private Button boostTrigger, reallySlowModeTrigger;
    public Button liftUpButton, liftDownButton, liftRestButton, liftHighButton, manualDownButton, manualUpButton;
    public Button deliveryButton1, deliveryButton2, deliverAndDriveButton1, deliverAndDriveButton2, closeButton;
    public Button capToggleButton, scoreCapButton;
    public Button spinButton, otherWay;

    @Override
    public void robotInit() {
        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap),telemetry);
        drivetrain.init();
        intake = new Intake(hardwareMap, telemetry);
        lift = new Lift(hardwareMap, telemetry);
        duckWheels = new DuckWheels(hardwareMap, telemetry);

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        drivetrain.setDefaultCommand(new SlowDriveCommand(drivetrain, driverGamepad));

        lift.closeDel();
        lift.liftLow();
    }

    @Override
    public void configureButtons() {
        reallySlowModeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)).whileHeld(new ReallyReallySlowDriveCommand(drivetrain, driverGamepad));
        boostTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)).whileHeld(new LeosFastDriveCommand(drivetrain, driverGamepad));

        intakeButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER).whileHeld(intake::intake).whenReleased(intake::stop));
        outtakeButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER).whileHeld(intake::outtake).whenReleased(intake::stop));

        liftUpButton = (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER).whenPressed(lift::moveUp));
        liftDownButton = (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.LEFT_TRIGGER).whenPressed(lift::moveDown));
        liftRestButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.LEFT_BUMPER).whenPressed(new LowerLiftNoLimitSwitchCommand(lift)));
        liftHighButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(lift::liftHigh));
        manualDownButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.BACK).whileHeld(lift::lowerLiftManual).whenReleased(lift::resetLift));
        manualUpButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.START).whileHeld(lift::raiseLiftPID).whenReleased(lift::stopLift));


        liftHighButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(lift::liftHigh));

        deliveryButton1 = (new GamepadButton(driverGamepad, GamepadKeys.Button.B)).toggleWhenPressed(
                new InstantCommand(lift::toggleDel, lift)
        );

        deliveryButton2 = (new GamepadButton(operatorGamepad, GamepadKeys.Button.B)).toggleWhenPressed(
                new InstantCommand(lift::toggleDel, lift)
        );

        deliverAndDriveButton1 = (new GamepadButton(driverGamepad, GamepadKeys.Button.X)).whenPressed(
                new DeliverCommand(drivetrain, lift)
        );

        deliverAndDriveButton2 = (new GamepadButton(operatorGamepad, GamepadKeys.Button.X)).whenPressed(
                new DeliverCommand(drivetrain, lift)
        );
        closeButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.A).whenPressed(lift::closeDel));

        //capToggleButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y).whenPressed(cap::toggleCap));
        //scoreCapButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT).whenPressed(cap::scoreCap));
        spinButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP)).whileHeld(
                new InstantCommand(duckWheels::spinDuckRed, duckWheels))
                .whenReleased(new InstantCommand(duckWheels::stop, duckWheels)
                );

        otherWay = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN)).whileHeld(
                new InstantCommand(duckWheels::spinDuckBlue, duckWheels))
                .whenReleased(new InstantCommand(duckWheels::stop, duckWheels)
                );
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void matchStart() {
        lift.liftLow();
        //lift.closeDelivery();
    }

    @Override
    public void robotPeriodic() {
    }
}
