package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class TestLowerLiftNoLimitSwitchCommand extends SequentialCommandGroup {
    private Lift lift;

    public TestLowerLiftNoLimitSwitchCommand(Lift lift) {
        addCommands(
                new ParallelCommandGroup(
                    //new InstantCommand(cap::rest),
                    new InstantCommand(lift::closeDel, lift)
                ),
            new InstantCommand(lift::liftLow, lift)
        );
    }
}
