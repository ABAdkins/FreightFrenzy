package org.firstinspires.ftc.teamcode.autons.regionals.blue.carousel;


import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.drive.auton.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.IMUTurnCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.SlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.SlowestDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheels;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class CarouselBlueRegionalsCommandSequence extends SequentialCommandGroup {
    public CarouselBlueRegionalsCommandSequence(Drivetrain drivetrain, Lift lift, DuckWheels duckWheels, Telemetry telemetry) {
        addCommands(
                //finished 1/27
                //duck
                //a-ok
                new InstantCommand(lift::closeDel),
                new KindaSlowDriveForwardCommand(drivetrain, -23),
                new TurnToCommand(drivetrain, 235, false),
                new DriveForwardCommand(drivetrain, 21),
                new SlowDriveForwardCommand(drivetrain, 12),
                new InstantCommand(duckWheels::spinBlueAuton),
                new ParallelCommandGroup(
                        new SlowestDriveForwardCommand(drivetrain, 4),
                        new WaitCommand(3500)
                ),
                new InstantCommand(duckWheels::stop),
                new DriveForwardCommand(drivetrain, -5),
                new IMUTurnCommand(drivetrain, -0)
        );
    }
}