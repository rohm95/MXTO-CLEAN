// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.SignalLogger;

import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command m_teleopInitCommand;
  private RobotContainer m_robotContainer;
  private PowerDistribution m_pdp;

  @Override
  public void robotInit() {
    // * RobotContainer
    m_robotContainer = new RobotContainer();

    // * DISABLE LIVE WINDOW
    LiveWindow.disableAllTelemetry();

    // * DISABLE PHOENIX LOGGING
    SignalLogger.stop();
    SignalLogger.enableAutoLogging(false);

    // * Limelight port forwarding over USB
    for (int port = 5800; port <= 5807; port++) PortForwarder.add(port, "limelight.local", port);

    // * Power DIstribution
    this.m_pdp = new PowerDistribution(1, PowerDistribution.ModuleType.kRev);

    // DATALOG
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putData("PowerDistribution", m_pdp);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    if (m_teleopInitCommand != null) m_teleopInitCommand.cancel();
    if (m_autonomousCommand != null) m_autonomousCommand.schedule();
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    m_teleopInitCommand = m_robotContainer.getTeleopInitCommand();
    if (m_teleopInitCommand != null) m_teleopInitCommand.schedule();
    if (m_autonomousCommand != null) m_autonomousCommand.cancel();
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}

  @Override
  public void close() {
    m_pdp.close();
  }
}
