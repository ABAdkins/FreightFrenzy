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

public class BlueRegionalsWarehouseRCommand extends SequentialCommandGroup {
    public BlueRegionalsWarehouseRCommand(Drivetrain drivetrain, Lift lift, Intake intake, DuckWheels duckWheels, Telemetry telemetry) {
        addCommands(
                new InstantCommand(lift::closeDel),
                //preload
                new SplineCommand(drivetrain, new Vector2d( 26.5, -20.5),220, true),
                new WaitCommand(50),
                new TurnToCommand(drivetrain, 180, false),
                new DriveForwardCommand(drivetrain, -4),
                new InstantCommand(lift::liftHigh),
                new WaitCommand(600),
                new InstantCommand(lift::openDel),
                new DriveForwardCommand(drivetrain,5),
                new InstantCommand(lift::closeDel),
                new InstantCommand(lift::liftLow),
                new InstantCommand(intake::intake),
                //into warehouse 1
                new TwoSplineCommand(drivetrain, new Vector2d(-2,10), new Vector2d(-3, 40.0), 265.8, 264.5),
                new WaitCommand(50),
                new TurnToCommand(drivetrain, 100, false),
                new WaitCommand(200),
                new InstantCommand(intake::outtake),
                new TurnToCommand(drivetrain, 80, false),
                new WaitCommand(100),
                new InstantCommand(intake::stop),
                //deliver 1
                new TwoSplineCommand(drivetrain, new Vector2d(-4,15), new Vector2d(17,-26), 180,220, true),
                new TurnToCommand(drivetrain, 190, false),
                new DriveForwardCommand(drivetrain, -7),
                new InstantCommand(lift::liftHigh),
                new WaitCommand(700),
                new InstantCommand(lift::openDel),
                new DriveForwardCommand(drivetrain, 6),
                new InstantCommand(lift::closeDel),
                new InstantCommand(lift::liftLow),
                new WaitCommand(150),
                new InstantCommand(intake::intake),
                //into warehouse 2
                new TwoSplineCommand(drivetrain, new Vector2d(-3,10), new Vector2d(-3, 43.0), 265.8, 264.5),


                new TurnToCommand(drivetrain, 81, false),

                //take this out when fixed
                new WaitCommand(1200),
                new TurnToCommand(drivetrain, 90, false),

                new InstantCommand(intake::outtake),
                new WaitCommand(2000),
                new InstantCommand(intake::stop)

                /*
                new WaitCommand(200),
                new InstantCommand(intake::outtake),
                new TurnToCommand(drivetrain, 81, false),
                //deliver 2
                new TwoSplineCommand(drivetrain, new Vector2d(-5,5), new Vector2d(22,-19), 180,220, true),
                new WaitCommand(100),
                new InstantCommand(intake::stop),
                new TurnToCommand(drivetrain, 190, false),
                new DriveForwardCommand(drivetrain, -3),
                new InstantCommand(lift::liftHigh),
                new WaitCommand(550),
                new InstantCommand(lift::openDel),
                new WaitCommand(200),
                new DriveForwardCommand(drivetrain, 4),
                new InstantCommand(lift::closeDel),
                new InstantCommand(lift::liftLow),

                new WaitCommand(200),
                //into warehouse park
                new TwoSplineCommand(drivetrain, new Vector2d(-2,5), new Vector2d(-3, 42.0), 265.8, 264.5),
                new InstantCommand(intake::intake),
                new WaitCommand(800),
                new InstantCommand(intake::outtake),
                new WaitCommand(300),
                new InstantCommand(intake::stop)

                 */

        );
    }
}
