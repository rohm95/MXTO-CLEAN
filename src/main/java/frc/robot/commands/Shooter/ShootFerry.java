package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.subsystems.LedsSubsystem;
import frc.robot.subsystems.Shooter;

public class ShootFerry extends Command {
  private final Shooter shooter;
  private final IntakeRollers rollers;
  private final LedsSubsystem leds;
  private final Timer timer = new Timer();

  public ShootFerry(Shooter shooter, IntakeRollers rollers, LedsSubsystem leds) {
    this.shooter = shooter;
    this.rollers = rollers;
    this.leds = leds;
    addRequirements(shooter, rollers, leds);
  }

  @Override
  public void initialize() {
    this.timer.reset();
    this.timer.start();
    this.leds.setLeds("#0000ff");
  }

  @Override
  public void execute() {
    this.shooter.set(1, 0.85);
    this.rollers.giveToShooter();
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.stop();
    this.rollers.stop();
    this.timer.stop();
    this.leds.turnOff();
  }

  @Override
  public boolean isFinished() {
    return (this.timer.get() > 1.0);
  }
}
