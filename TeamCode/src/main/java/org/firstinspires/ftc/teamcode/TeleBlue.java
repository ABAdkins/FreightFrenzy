package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.commands.DeliverCommand;
import org.firstinspires.ftc.teamcode.commands.LowerLiftCommand;
import org.firstinspires.ftc.teamcode.commands.TestLowerLiftNoLimitSwitchCommand;
import org.firstinspires.ftc.teamcode.commands.drive.teleOp.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.commands.drive.teleOp.ReallySlowDriveCommand;
import org.firstinspires.ftc.teamcode.commands.drive.teleOp.SlowDriveCommand;
import org.firstinspires.ftc.teamcode.drive.MatchOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheels;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

@Config
@TeleOp(name = "Blue TeleOp", group = "kyle")
public class TeleBlue extends MatchOpMode {
    // Gamepad
    private GamepadEx driverGamepad, operatorGamepad;

    // Subsystems
    private Drivetrain drivetrain;
    private Lift lift;
    private Intake intake;
    private DuckWheels duckWheels;
    //private Cap cap;

    //Buttons
    private Button intakeButton, outtakeButton;
    private Button slowModeTrigger, reallySlowModeTrigger;
    public Button liftUpButton, liftDownButton, liftRestButton, liftHighButton, manualDownButton, manualUpButton;
    public Button deliveryButton1, deliveryButton2, deliverAndDriveButton1, deliverAndDriveButton2, closeButton;
    public Button spinButton, otherWay;
    public Button capToggleButton, scoreCapButton;

    //tools
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void robotInit() {
        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap),telemetry);

        intake = new Intake(hardwareMap, telemetry);
        lift = new Lift(hardwareMap, telemetry);
        duckWheels = new DuckWheels(hardwareMap, telemetry);
        //cap = new Cap(hardwareMap, telemetry);

        drivetrain.init();

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad));
        //cap.setDefaultCommand(new CapManualCommand(cap, operatorGamepad));

        lift.closeDel();
        lift.liftLow();
    }

    @Override
    public void configureButtons() {
        //dt
        slowModeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)).whileHeld(new SlowDriveCommand(drivetrain, driverGamepad));
        reallySlowModeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)).whileHeld(new ReallySlowDriveCommand(drivetrain, driverGamepad));

        //intake
        intakeButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER).whileHeld(intake::intake).whenReleased(intake::stop));
        outtakeButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER).whileHeld(intake::outtake).whenReleased(intake::stop));

        //lift
        liftUpButton = (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER).whenPressed(lift::moveUp));
        liftDownButton = (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.LEFT_TRIGGER).whenPressed(lift::moveDown));
        liftRestButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.LEFT_BUMPER).whenPressed(new TestLowerLiftNoLimitSwitchCommand(lift)));
        liftHighButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(lift::liftHigh));
        manualDownButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.BACK).whileHeld(lift::lowerLiftManual).whenReleased(lift::resetLift));
        manualUpButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.START).whileHeld(lift::raiseLiftPID).whenReleased(lift::stopLift));

        //delivery
        deliveryButton1 = (new GamepadButton(driverGamepad, GamepadKeys.Button.B)).toggleWhenPressed(
                new InstantCommand(lift::toggleDel, lift)
        );
        deliveryButton2 = (new GamepadButton(operatorGamepad, GamepadKeys.Button.A)).toggleWhenPressed(
                new InstantCommand(lift::toggleDel, lift)
        );
        deliverAndDriveButton1 = (new GamepadButton(driverGamepad, GamepadKeys.Button.X)).whenPressed(
                new DeliverCommand(drivetrain, lift)
        );
        deliverAndDriveButton2 = (new GamepadButton(operatorGamepad, GamepadKeys.Button.X)).whenPressed(
                new DeliverCommand(drivetrain, lift)
        );

        //duck wheels
        spinButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP)).whileHeld(
                new InstantCommand(duckWheels::spinDuckBlue, duckWheels))
                .whenReleased(new InstantCommand(duckWheels::stop, duckWheels)
        );
        otherWay = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN)).whileHeld(
                new InstantCommand(duckWheels::spinDuckRed, duckWheels))
                .whenReleased(new InstantCommand(duckWheels::stop, duckWheels)
        );

        //cap
        //capToggleButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y).whenPressed(cap::toggleCap));
        //scoreCapButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT).whenPressed(cap::scoreCap));
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void matchStart() {
        lift.liftLow();
        //if lift not down, go down
        schedule(new LowerLiftCommand(lift));
    }

    @Override
    public void robotPeriodic() {
    }
}
