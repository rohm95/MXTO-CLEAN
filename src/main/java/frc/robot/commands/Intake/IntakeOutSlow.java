package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeRollers;
import lib.team3526.utils.LimelightLED;

public class IntakeOutSlow extends Command {
  private IntakeRollers rollers;

  public IntakeOutSlow(IntakeRollers rollers) {
    this.rollers = rollers;
    addRequirements(rollers);
  }
  
  @Override
  public void initialize() {
  }
  
  @Override
  public void execute() {
    rollers.setRollersOutSlow();
  }
  
  @Override
  public void end(boolean interrupted) {
    LimelightLED.blinkLeds(Constants.Vision.kLimelightName);
    rollers.stop();
  }
  
  @Override
  public boolean isFinished() {
    return rollers.hasPiece();
  }
}
