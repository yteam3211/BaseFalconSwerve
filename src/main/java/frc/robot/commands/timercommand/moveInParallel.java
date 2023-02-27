// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.timercommand;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Swerve;
import frc.robot.autos.next2human;
import frc.robot.commands.collectOutput;
import frc.robot.commands.setPointCollectCommand;
import frc.robot.subsystems.CollectSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.collectWheels;
import frc.robot.subsystems.armSubsystem;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class moveInParallel extends ParallelCommandGroup {
  Swerve s_Swerve;
  CollectSubsystem collectSubsystem;
  armSubsystem armSubsystem;
  collectWheels collectWheels;
  GripperSubsystem gripperSubsystem;
  double delayForTheArm;
  double positionForArm;
  double positionForGripper;
  double stop;
  double point;
  double secends;
  double delayForTheCollect;
  double collectSeconds;

  /** Creates a new collectInParallel. */
  public moveInParallel(Swerve s_Swerve,armSubsystem armSubsystem, CollectSubsystem collectSubsystem,
  collectWheels collectWheels,
  GripperSubsystem gripperSubsystem,
   double delayForTheArm,double positionForArm,
    double stop, double point, double collectSeconds,
     double secends,double positionForGripper,double delayForTheCollect) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    this.s_Swerve = s_Swerve;
    this.collectSubsystem = collectSubsystem;
    this.armSubsystem = armSubsystem;
    this.collectWheels = collectWheels;
    this.gripperSubsystem = gripperSubsystem;
    this.delayForTheArm = delayForTheArm;
    this.positionForArm = positionForArm;
    this.positionForGripper = positionForGripper; 
    this.stop = stop;
    this.point = point;
    this.secends = secends;
    this.delayForTheCollect = delayForTheCollect;
    this.collectSeconds = collectSeconds;

    

    addCommands(new TimerArmPosition(armSubsystem, positionForArm, delayForTheArm, stop),
    new timeSetPointCollectCommand(collectSubsystem, point, collectSeconds),
    new collectOutput(collectWheels, 0, 0),
    new TimerGripperCommand(positionForGripper,secends, gripperSubsystem)
    //next2human.getAutoCommand(s_Swerve)
    );
    
  }
}

