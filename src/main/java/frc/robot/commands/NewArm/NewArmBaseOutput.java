// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.NewArm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class NewArmBaseOutput extends CommandBase {
  private final ArmSubsystem armSubsystem;
  private double BaseOutput;

  /** Creates a new NewArmOutput. */
  public NewArmBaseOutput(ArmSubsystem armSubsystem,double BaseOutput ) {
    this.armSubsystem = armSubsystem;
    this.BaseOutput = BaseOutput;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    armSubsystem.setArmBaseOutput(BaseOutput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
