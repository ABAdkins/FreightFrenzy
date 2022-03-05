package org.firstinspires.ftc.teamcode.autons.regionals.blue.carousel;

//tested and good night before comp
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.LowerLiftCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.SlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Cap;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheels;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class BlueRegionalsCarouselCCommand extends SequentialCommandGroup {
    public BlueRegionalsCarouselCCommand(Drivetrain drivetrain, Lift lift, DuckWheels duckWheels, Cap cap, Telemetry telemetry) {
        addCommands(
                //blue mid carousel
                //command sequence
                //duck
                new CarouselBlueRegionalsCommandSequence(drivetrain, lift, duckWheels, telemetry),


                //--------------------end command sequence------------------------------------------
                //--------------------------finished------------------------------------------------

                new SplineCommand(drivetrain, new Vector2d(55, 10.4), -230, true),
                new WaitCommand(200),

                //new IMUTurnCommand(drivetrain, 135, true),
                new TurnToCommand(drivetrain, -60, true),
                new InstantCommand(lift::liftMidAuton),

                new SlowDriveForwardCommand(drivetrain, -5.5),//perfect distance
                new WaitCommand(200),

                new InstantCommand(lift::openDel),
                new WaitCommand(700),
                new SlowDriveForwardCommand(drivetrain, 8),
                new LowerLiftCommand(lift),
                new InstantCommand(lift::closeDel),

                //park
                new TurnToCommand(drivetrain, 90, true),
                new SplineCommand(drivetrain,new Vector2d( 31, -24), 270, true)

        );
    }
}