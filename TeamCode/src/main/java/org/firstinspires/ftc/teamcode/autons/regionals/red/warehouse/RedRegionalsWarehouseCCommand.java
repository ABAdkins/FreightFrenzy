package org.firstinspires.ftc.teamcode.autons.regionals.red.warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.autons.regionals.red.warehouse.sequences.RedRegionals2CycleCommandSequence;
import org.firstinspires.ftc.teamcode.commands.drive.auton.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class RedRegionalsWarehouseCCommand extends SequentialCommandGroup {
    public RedRegionalsWarehouseCCommand(Drivetrain drivetrain, Lift lift, Intake intake, Telemetry telemetry) {
        addCommands(

                new InstantCommand(lift::closeDel),
                //go to hub
                new SplineCommand(drivetrain, new Vector2d( 22.5, 19.5),-220, true),
                new WaitCommand(50),
                new DriveForwardCommand(drivetrain, -4),
                new InstantCommand(lift::liftMidAuton),
                new TurnToCommand(drivetrain, 180, false),
                new InstantCommand(lift::openDel),
                new WaitCommand(300),
                new InstantCommand(lift::closeDel),
                new InstantCommand(lift::liftLow),
                new RedRegionals2CycleCommandSequence(drivetrain, lift, intake, telemetry)

        );
    }
}
