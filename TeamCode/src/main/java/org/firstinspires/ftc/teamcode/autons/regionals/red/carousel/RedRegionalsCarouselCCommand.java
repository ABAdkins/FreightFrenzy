package org.firstinspires.ftc.teamcode.autons.regionals.red.carousel;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.autons.champs.red.carousel.CarouselRedCommandR;
import org.firstinspires.ftc.teamcode.autons.regionals.blue.carousel.CarouselBlueRegionalsCommandSequence;
import org.firstinspires.ftc.teamcode.commands.LowerLiftCommand;
import org.firstinspires.ftc.teamcode.commands.LowerLiftNoLimitSwitchCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.SlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.drive.auton.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Cap;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.DuckWheels;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class RedRegionalsCarouselCCommand extends SequentialCommandGroup {
    public RedRegionalsCarouselCCommand(Drivetrain drivetrain, Lift lift, Intake intake, DuckWheels duckWheels, Cap cap, Telemetry telemetry) {
        addCommands(
                //blue mid carousel
                //command sequence
                //duck
                new CarouselRedRegionalsCommandSequence(drivetrain, lift, duckWheels, telemetry),


                //--------------------end command sequence------------------------------------------
                //--------------------------finished------------------------------------------------

                new SplineCommand(drivetrain, new Vector2d(60, -11.4), -60, true),
                new WaitCommand(200),

                //new IMUTurnCommand(drivetrain, 135, true),
                new TurnToCommand(drivetrain, 40, true),
                new InstantCommand(lift::liftMidAuton),

                new SlowDriveForwardCommand(drivetrain, -5.5),//perfect distance

                new InstantCommand(lift::openDel),
                new SlowDriveForwardCommand(drivetrain, 8),
                new LowerLiftNoLimitSwitchCommand(lift, cap),
                new InstantCommand(lift::closeDel),

                //park
                new TurnToCommand(drivetrain, -90, true),
                new SplineCommand(drivetrain,new Vector2d( 30, 26), -270, true)




        );
    }
}
