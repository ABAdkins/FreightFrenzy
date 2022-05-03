package org.firstinspires.ftc.teamcode.autons.regionals.red.warehouse.sequences;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.drive.auton.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.TurnToCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.TwoSplineCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class RedRegionals2CycleCommandSequence extends SequentialCommandGroup {
    public RedRegionals2CycleCommandSequence(Drivetrain drivetrain, Lift lift, Intake intake, Telemetry telemetry) {
        addCommands(
                //begin sequence

                new InstantCommand(intake::intake),
                //in 1
                new TwoSplineCommand(drivetrain, new Vector2d(-3, -10), new Vector2d(-4, -40.0), -265.8, -265.5),
                new WaitCommand(50),
                new InstantCommand(intake::outtake),
                //out 1 del
                new TwoSplineCommand(drivetrain, new Vector2d(-4,10), new Vector2d(22,21), -150, -220, true),
                new InstantCommand(intake::stop),
                new InstantCommand(lift::liftHigh),
                new TurnToCommand(drivetrain, 180, true),
                new InstantCommand(lift::openDel),
                new DriveForwardCommand(drivetrain, 4),
                new InstantCommand(lift::closeDel),
                new InstantCommand(lift::liftLow),
                new InstantCommand(intake::intake),
                //in 2
                new TwoSplineCommand(drivetrain, new Vector2d(-7, -6), new Vector2d(-5, -40.0), -265.8, -265.8),
                new WaitCommand(50),
                new InstantCommand(intake::outtake),
                //out 2 del
                new TwoSplineCommand(drivetrain, new Vector2d(-5.5,-10), new Vector2d(22,21), -150, -220, true),
                new InstantCommand(lift::liftHigh),
                new TurnToCommand(drivetrain, 180, false),
                new InstantCommand(lift::openDel),
                new DriveForwardCommand(drivetrain, 4),
                new InstantCommand(lift::closeDel),
                new InstantCommand(lift::liftLow),
                new InstantCommand(intake::intake),
                //in park
                new TwoSplineCommand(drivetrain, new Vector2d(-8, -7), new Vector2d(-5, -40.0), -265.8, -264.5),
                new WaitCommand(300),
                new InstantCommand(intake::stop)
        );
    }
}