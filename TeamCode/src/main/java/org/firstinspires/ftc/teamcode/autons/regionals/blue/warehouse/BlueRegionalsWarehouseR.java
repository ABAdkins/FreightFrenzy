package org.firstinspires.ftc.teamcode.autons.regionals.blue.warehouse;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.drive.auton.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.TurnToCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.TwoSplineCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheels;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class BlueRegionalsWarehouseR extends SequentialCommandGroup {
    public BlueRegionalsWarehouseR(Drivetrain drivetrain, Lift lift, Intake intake, DuckWheels duckWheels, Telemetry telemetry) {
        addCommands(
                //testing blue warehouse new


                new InstantCommand(lift::closeDel),
                //go to hub
                new SplineCommand(drivetrain, new Vector2d( 24.5, -20.5),220, true),
                new WaitCommand(50),
                new TurnToCommand(drivetrain, 180, false),
                new DriveForwardCommand(drivetrain, -4),


                //deliver and spline away
                new InstantCommand(lift::liftHigh),
                new InstantCommand(lift::openDel),
                new WaitCommand(300),
                new InstantCommand(lift::closeDel),
                new InstantCommand(lift::liftLow),
                new InstantCommand(intake::intake),
                new TwoSplineCommand(drivetrain, new Vector2d(-2,10), new Vector2d(-3, 40.0), 265.8, 264.5),
                new WaitCommand(50),

                new TurnToCommand(drivetrain, 90, false),

                //wait a bit and outtake
                new WaitCommand(200),
                new InstantCommand(intake::outtake),


                //turn to correct
                new TurnToCommand(drivetrain, 85, false),
                new InstantCommand(intake::stop),
                new TwoSplineCommand(drivetrain, new Vector2d(-2,10), new Vector2d(22,-19.5), 265.8, 220, true),
                new TurnToCommand(drivetrain, 160, false),
                new InstantCommand(lift::liftHigh),
                new WaitCommand(400),
                new InstantCommand(lift::openDel),
                new DriveForwardCommand(drivetrain, 4),
                new InstantCommand(lift::liftLowAuton),
                new InstantCommand(lift::closeDel),
                new WaitCommand(150),
                new InstantCommand(intake::intake),
                new TwoSplineCommand(drivetrain, new Vector2d(-3,10), new Vector2d(-3, 42.0), 265.8, 264.5),
                //wait a bit and outtake
                new WaitCommand(200),
                new InstantCommand(intake::outtake),
                new WaitCommand(200),
                //stop intake
                new SplineCommand(drivetrain, new Vector2d(22, -19.5),220, true),
                new InstantCommand(intake::stop),
                new TurnToCommand(drivetrain, 160, false),
                new InstantCommand(lift::liftHigh),
                new WaitCommand(400),
                new InstantCommand(lift::openDel),
                new WaitCommand(200),
                new DriveForwardCommand(drivetrain, 4),
                new InstantCommand(lift::liftLow),
                new InstantCommand(lift::closeDel),
                new WaitCommand(200),
                new TwoSplineCommand(drivetrain, new Vector2d(-3,10), new Vector2d(-3, 42.0), 265.8, 264.5)
                //wait a bit and outtake

        );
    }
}
