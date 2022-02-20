package org.firstinspires.ftc.teamcode.commands.drive.teleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.commands.drive.teleOp.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class ReallySlowDriveCommand extends DefaultDriveCommand {
    public ReallySlowDriveCommand(Drivetrain drive, GamepadEx driverGamepad) {
        super(drive, driverGamepad);
        this.multiplier = 0.25;
        this.rotMultiplier = 0.25;
    }
}