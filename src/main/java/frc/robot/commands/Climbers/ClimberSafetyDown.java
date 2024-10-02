package frc.robot.commands.Climbers;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ClimberSafetyDown extends Command {
    Climber m_climber;

    public ClimberSafetyDown(Climber climber) {
        this.m_climber = climber;
        addRequirements(climber);
    }

    public void execute() {
        
    }
}
