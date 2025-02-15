package org.firstinspires.ftc.teamcode.autons.lm3.red;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.FastDriveCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class RedWarehouseCommandR extends SequentialCommandGroup {
    public RedWarehouseCommandR(Drivetrain drivetrain, Lift lift, Telemetry telemetry) {
        addCommands(
                new InstantCommand(lift::closeDel),
                new DriveForwardCommand(drivetrain, 8),
                new TurnCommand(drivetrain, -32),
                //drive to shipping hub
                new DriveForwardCommand(drivetrain, 21),
                //lift to correct pos ---------------------------------------
                new InstantCommand(lift::liftHigh),
                //open delivery
                new DriveForwardCommand(drivetrain, 2),
                new WaitCommand(750),
                new InstantCommand(lift::openDel),
                new WaitCommand(1250),
                //close delivery
                new InstantCommand(lift::closeDel),
                //back up
                new DriveForwardCommand(drivetrain, -12),
                //lift down
                new InstantCommand(lift::liftLow),
                //turn towards warehouse
                new TurnCommand(drivetrain, 120),
                //park - speed
                new FastDriveCommand(drivetrain, 53)
        );
    }
}