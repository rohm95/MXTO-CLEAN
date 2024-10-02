// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import static edu.wpi.first.units.Units.Degrees;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeLifter;

public class LifterShooter extends Command {
  IntakeLifter intake;

  /** Creates a new IntakeShooter. */
  public LifterShooter(IntakeLifter intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.intake.setLifterAngle(Constants.Intake.Physical.kShooterAngle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(Math.toDegrees(this.intake.getLifterAngleRadians()) - Constants.Intake.Physical.kShooterAngle.in(Degrees)) < 5;
  }
}
