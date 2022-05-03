package org.firstinspires.ftc.teamcode.autons.regionals.red.warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.autons.regionals.red.warehouse.sequences.RedRegionals2CycleCommandSequence;
import org.firstinspires.ftc.teamcode.autons.regionals.red.warehouse.sequences.RedRegionals3CycleCommandSequence;
import org.firstinspires.ftc.teamcode.commands.drive.auton.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class RedRegionalsWarehouseRCommand extends SequentialCommandGroup {
    public RedRegionalsWarehouseRCommand(Drivetrain drivetrain, Lift lift, Intake intake, Telemetry telemetry) {


        addCommands(
                new InstantCommand(lift::closeDel),
                //go to hub
                new SplineCommand(drivetrain, new Vector2d( 23, 20.75),-220, true),
                new WaitCommand(50),
                new TurnToCommand(drivetrain, 180, false),
                new DriveForwardCommand(drivetrain, -6.5),
                new InstantCommand(lift::liftHigh),
                new WaitCommand(400),
                new InstantCommand(lift::openDel),
                new WaitCommand(350),
                new InstantCommand(lift::closeDel),
                new WaitCommand(100),
                new InstantCommand(lift::liftLow),
                new RedRegionals2CycleCommandSequence(drivetrain, lift, intake, telemetry)
        );
    }
}