// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.text.Position;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.robot.commands.ShootingOutput;
import frc.util.SuperSystem;
import frc.util.PID.Gains;
import frc.util.motor.SuperSparkMax;
import frc.util.motor.SuperTalonFX;


// Yteam Example Subsystem
public class ShootingSubsystem extends SuperSystem {
//  public SuperSparkMax ShooingMotor;
 public Gains shootingGains;
 public DigitalInput upMicroSwitch;
 public DigitalInput downMicroSwitch;
//  public VictorSP firstShootingSp;
//  public VictorSP secondShootingSp;

 public SuperTalonFX ShooingMotor;
  // Motors, Selenoid and Sensors declaration
  public ShootingSubsystem() {
    super("ShootingSubsystem");
    shootingGains = new Gains("_shootingGains",0.4, 0,0);
    upMicroSwitch = new DigitalInput(1);
    downMicroSwitch = new DigitalInput(3);
    ShooingMotor = new SuperTalonFX(ShooingMotor, 15, 30, false);
    // firstShootingSp = new VictorSP(0);
    // secondShootingSp = new VictorSP(1);
    // ShooingMotor = new SuperSparkMax(14,false);
    // ShooingMotor = new SuperSparkMax(14, MotorType.kBrushless, 30, false, 1 ,1 , IdleMode.kBrake, ControlType.kPosition, shootingGains, 0, 0, 0);
    setDefaultCommand(new ShootingOutput(this, 0));
  }

  /** Creates a new ExampleSubsystem. */
  
  @Override
  public void periodic() {
    // getTab().putInDashboard("Position", ShooingMotor.getEncoder().getPosition(), false);
    // getTab().putInDashboard("fgr", ShooingMotor.getPosition(), false);
    // This method will be called once per scheduler run
  }

  public void setOutput(double output){
    // ShooingMotor.setMode(ControlMode.PercentOutput);
    ShooingMotor.set(ControlMode.PercentOutput, output);
    // neo motor 
    // if ((output > 0 && !isShootingUp()) || (output <0 && !isShootingDown())) {
    //   firstShootingSp.set(output);
    //   secondShootingSp.set(output);
    // }
    
  }
  public void resetEncoder(){
    // ShooingMotor.getEncoder().setPosition(0);
  }

  public void setPosition(double position){

    // ShooingMotor.setMode(ControlMode.Position);
    // ShooingMotor.getPIDController().setReference(position, ControlType.kPosition);
    // getTab().putInDashboard("encoder_live", ShooingMotor.getPosition(), false);

  }

  public boolean isShootingUp(){
    
    //  return upMicroSwitch.get();
    return false;
  }

  public boolean isShootingDown(){
    
    // return downMicroSwitch.get();
    return false;
  }
}

