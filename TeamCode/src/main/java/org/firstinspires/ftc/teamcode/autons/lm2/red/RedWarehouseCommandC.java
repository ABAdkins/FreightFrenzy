package org.firstinspires.ftc.teamcode.autons.lm2.red;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.FastDriveCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class RedWarehouseCommandC extends SequentialCommandGroup {
    public RedWarehouseCommandC(Drivetrain drivetrain, Lift lift, Telemetry telemetry) {
        //declare variables here

        addCommands(
                new InstantCommand(lift::toggleDel),
                new DriveForwardCommand(drivetrain, -8),
                new TurnCommand(drivetrain, 90),
                //drive to shipping hub
                new DriveForwardCommand(drivetrain, -21.5),
                new TurnCommand(drivetrain, -90),
                new DriveForwardCommand(drivetrain, -15),
                //lift to correct pos ---------------------------------------
                new InstantCommand(lift::liftMid),
                //open delivery
                new DriveForwardCommand(drivetrain, -2),
                new WaitCommand(750),
                new InstantCommand(lift::toggleDel),
                new WaitCommand(1250),
                //close delivery
                new InstantCommand(lift::toggleDel),
                //back up
                new DriveForwardCommand(drivetrain, 12),
                //lift down
                new InstantCommand(lift::liftLow),
                //turn towards warehouse
                new TurnCommand(drivetrain, -90),
                //park
                new FastDriveCommand(drivetrain, -65)
        );
    }
}