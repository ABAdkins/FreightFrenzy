package org.firstinspires.ftc.teamcode.autons.champs.red.warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.drive.atuon.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.atuon.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.drive.atuon.TurnToCommand;
import org.firstinspires.ftc.teamcode.commands.drive.atuon.TwoSplineCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheels;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class LSimpleWarehouseRedCommand extends SequentialCommandGroup {
    public LSimpleWarehouseRedCommand(Drivetrain drivetrain, Lift lift, Intake intake, DuckWheels duckWheels, Telemetry telemetry) {
        addCommands(
                //testing blue warehouse new


                new InstantCommand(lift::closeDel),
                //go to hub
                new SplineCommand(drivetrain, new Vector2d( 14, 12.5),-220, true),
                new WaitCommand(50),
                new TurnToCommand(drivetrain, 180, false),
                new DriveForwardCommand(drivetrain, -1.5),


                //deliver and spline away
                new InstantCommand(lift::liftLow),
                new InstantCommand(lift::openDel),
                new WaitCommand(300),

                new TwoSplineCommand(drivetrain, new Vector2d(-1.2, 10.0), new Vector2d(-2, -18.0), -265.8, -264.5),
                new InstantCommand(lift::closeDel),
                new WaitCommand(50),
                new InstantCommand(intake::intake),

                new TurnToCommand(drivetrain, -90, true),

                //wait a bit and outtake
                new WaitCommand(750),
                new InstantCommand(intake::outtake),

                new WaitCommand(1500),
                //stop intake
                new InstantCommand(intake::stop),

                //turn to correct
                new TurnToCommand(drivetrain, -85, true)
        );
    }
}
