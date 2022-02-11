package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class LowerLiftCommand extends SequentialCommandGroup {
    private Lift lift;

    public LowerLiftCommand(Lift lift) {
        addCommands(
            new InstantCommand(lift::toggleDel, lift),
            new InstantCommand(lift::liftMid, lift),
            new WaitCommand(150),
            new InstantCommand(lift::liftLow, lift)

        );
    }
}
