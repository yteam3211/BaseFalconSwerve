package frc.robot;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import javax.swing.GroupLayout.Group;

import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;

import edu.wpi.first.networktables.PubSub;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.resetCommand;
import frc.robot.commands.ShootingCommnads.ShootingCommand;
import frc.robot.commands.ShootingCommnads.ShootingDownGroupCommand;
import frc.robot.commands.ShootingCommnads.ShootingFixercommand;
import frc.robot.commands.ShootingCommnads.ShootingGroupCommand;
import frc.robot.commands.SwereCommands.BalanceCommand;
import frc.robot.commands.SwereCommands.LeftGRIDmovmentCommand;
import frc.robot.commands.SwereCommands.LimelightCommand;
import frc.robot.commands.SwereCommands.TeleopSwerve;
import frc.robot.commands.SwereCommands.TurnToZeroCommand;
import frc.robot.commands.SwereCommands.lockWheelsCommnad;
import frc.robot.commands.SwereCommands.rightGRIDmovmentCommand;
import frc.robot.commands.ArmCommands.ArmCollectCommand;
import frc.robot.commands.ArmCommands.armCollectOutput;
import frc.robot.commands.IntakeCommands.OpenIntakeAndArm;
import frc.robot.commands.IntakeCommands.collectWheelsCommand;
import frc.robot.commands.IntakeCommands.setPointCollectCommand;
import frc.robot.commands.ShootingCommnads.CartridgeOutputCommand;
import frc.robot.commands.ShootingCommnads.CubeFixtureGroupCommand;
import frc.robot.commands.resetCommand;
import frc.robot.subsystems.CollectSubsystem;
import frc.robot.subsystems.CartridgeSubsystem;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.armCollectSubsystem;
import frc.robot.subsystems.collectWheelsSubsystem;
import frc.robot.subsystems.shootingSubsystem;
import frc.util.vision.Limelight;
// import frc.robot.commands.Balance;


// Yteam loadButtons
public class RobotButtons {
    public static BooleanSupplier GRIDmovmentHelper = (() -> true);
    

    public static Joystick systems = new Joystick(1);
    public static Joystick driver = new Joystick(0);
    // driver jpoystick buttons
    public static DoubleSupplier BreakValue = () -> driver.getRawAxis(XboxController.Axis.kRightTrigger.value);
    public Trigger resetGyro = new Trigger(() -> driver.getRawButton(XboxController.Button.kLeftBumper.value));
    public static Trigger Balance = new Trigger(() -> driver.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.3);
    public Trigger stop = new Trigger(() -> driver.getRawButton(XboxController.Button.kBack.value));
    public Trigger LimelightAprilTag = new Trigger(() -> driver.getRawButton(XboxController.Button.kB.value));
    public Trigger TurnToZero = new Trigger(() -> driver.getRawButton(XboxController.Button.kA.value));
    public Trigger LimelightRetroReflective = new Trigger(() -> driver.getRawButton(XboxController.Button.kX.value));
    public static Trigger halfSpeed = new Trigger(() -> driver.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.3);
    public static Trigger forwardJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kLeftY.value)) > 0.1);
    public static Trigger sidesJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kLeftX.value)) > 0.1);
    public static Trigger rotationJoystick = new Trigger(() -> Math.abs(driver.getRawAxis(XboxController.Axis.kRightX.value)) > 0.1);
    public static Trigger rightGRIDmovment = new Trigger(() -> driver.getPOV() == 90);
    public static Trigger leftGRIDmovment = new Trigger(() -> driver.getPOV() == 270);
    public Trigger testShooting = new Trigger(() -> driver.getPOV() == 0);

    public Trigger WheelsLock = new Trigger(() -> driver.getRawButton(XboxController.Button.kStart.value));

    // systems joystick buttons
    public Trigger OpenCollect = new Trigger(() -> systems.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.3);
    public Trigger OpenCollectTest = new Trigger(() -> !(systems.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.3));
    public Trigger collectWheelsBack = new Trigger(() -> systems.getRawButton(XboxController.Button.kStart.value));
    public Trigger shootingLow = new Trigger(() -> systems.getPOV() == 180);
    public Trigger shootingHigh = new Trigger(() -> systems.getPOV() == 0);
    // public Trigger shootingHighMeow = new Trigger(() -> !(systems.getPOV() == 0));
    public Trigger shootingFixture = new Trigger(() -> systems.getPOV() == 270);
    public Trigger shootinghMid = new Trigger(() -> systems.getPOV() == 90);
    public Trigger openArmCollect = new Trigger(() -> systems.getRawButton(XboxController.Button.kY.value));
    public Trigger closeArmCollect = new Trigger(() -> systems.getRawButton(XboxController.Button.kX.value));
    public Trigger resetArmCollect = new Trigger(() -> systems.getRawButton(XboxController.Button.kA.value));
    public Trigger reverseShooterTrigger = new Trigger(() -> systems.getRawButton(XboxController.Button.kRightBumper.value));
    public static Trigger forwardShooterTrigger = new Trigger(() -> systems.getRawButton(XboxController.Button.kLeftBumper.value));
    public Trigger resetTrigger = new Trigger(() -> systems.getRawButton(XboxController.Button.kB.value));
    public Trigger SeconderyResetTrigger = new Trigger(() -> systems.getRawButton(XboxController.Button.kBack.value));
    

    /**
     * @param shootingSubsystem
     * @param collectSubsyste
     * @param armSubsystem
     * @param swerve
     */
    public void loadButtons(shootingSubsystem shootingSubsystem, CollectSubsystem collectSubsystem,
             Swerve swerve,collectWheelsSubsystem collectWheels, Limelight limelight, armCollectSubsystem armCollectSubsystem,CartridgeSubsystem cartridgeSubsystem) {
        // driver joystick commands
        swerve.setDefaultCommand(
            new TeleopSwerve(
                    swerve,
                    () -> driver.getRawAxis(XboxController.Axis.kLeftY.value),
                    () -> driver.getRawAxis(XboxController.Axis.kLeftX.value),
                    () -> driver.getRawAxis(XboxController.Axis.kRightX.value)
                    ));
        resetGyro.onTrue(new InstantCommand(() -> swerve.zeroGyro()));
        LimelightAprilTag.whileTrue(new LimelightCommand(limelight, swerve, true, -1, 1));
        LimelightRetroReflective.whileTrue(new LimelightCommand(limelight, swerve, false, 14, 1));
        Balance.onTrue(new BalanceCommand(swerve, false));
        rightGRIDmovment.and(GRIDmovmentHelper).onTrue(new rightGRIDmovmentCommand(swerve));
        leftGRIDmovment.and(GRIDmovmentHelper).onTrue(new LeftGRIDmovmentCommand(swerve));
        TurnToZero.whileTrue(new TurnToZeroCommand(swerve));
        WheelsLock.onTrue(new lockWheelsCommnad(swerve));
        TurnToZero.onTrue(new TurnToZeroCommand(swerve));
        // testShooting.onTrue(new ShootingGroupCommand(shootingSubsystem, armCollectSubsystem, cartridgeSubsystem , Constants.ARM_OPEN_POSITION, 0 , 0.7, 0.9));
        // LimelightRetroReflectiveFloor.whileTrue(new LimelightCommand(limelight, swerve, false, 8));


        // systems joystick commands
        OpenCollect.whileTrue(new OpenIntakeAndArm(collectSubsystem, collectWheels, armCollectSubsystem, Constants.COLLECT_WHEELS_OUTPUT, Constants.CENTERING_WHEELS_OUTPUT, Constants.COLLECT_OPEN_POSITION, Constants.ARM_OPEN_POSITION));
        // whileTrue(new OpenIntakeAndArm(collectSubsystem, collectWheels, armCollectSubsystem, Constants.COLLECT_WHEELS_OUTPUT, Constants.CENTERING_WHEELS_OUTPUT, Constants.COLLECT_OPEN_POSITION, Constants.ARM_OPEN_POSITION));
        collectWheelsBack.whileTrue(new OpenIntakeAndArm(collectSubsystem, collectWheels, armCollectSubsystem, 0.7, 0.15, Constants.COLLECT_OPEN_POSITION, Constants.ARM_OPEN_POSITION));
        // .whileTrue(new collectWheelsCommand(collectWheels, 0.7, 0.5));
        
        resetArmCollect.onTrue(new armCollectOutput(armCollectSubsystem, -0.2, 0));

        shootingHigh.onTrue(new ShootingGroupCommand(shootingSubsystem, armCollectSubsystem, cartridgeSubsystem, Constants.SHOOTING_HIGH));
        shootinghMid.onTrue(new ShootingGroupCommand(shootingSubsystem, armCollectSubsystem, cartridgeSubsystem , Constants.SHOOTING_MID));
        shootingLow.onTrue(new ShootingGroupCommand(shootingSubsystem, armCollectSubsystem, cartridgeSubsystem , Constants.SHOOTING_LOW));
        shootingFixture.whileTrue(new ShootingFixercommand(cartridgeSubsystem, 0.15, 200, -0.1, 20));
        // shootingFixture.onTrue(new CubeFixtureGroupCommand(cartridgeSubsystem, 0, 0.15, 1400, -0.2, 20));
        reverseShooterTrigger.whileTrue(new ShootingCommand(shootingSubsystem, cartridgeSubsystem, armCollectSubsystem,-0.3, 0));
        forwardShooterTrigger.whileTrue(new ShootingCommand(shootingSubsystem, cartridgeSubsystem, armCollectSubsystem,0.5, 0));


        openArmCollect.onTrue(new InstantCommand(() -> armCollectSubsystem.setArmCollectPosition(Constants.ARM_OPEN_POSITION)));
        closeArmCollect.onTrue(new InstantCommand(() -> armCollectSubsystem.setArmCollectPosition(0)));
        
        
        resetTrigger.and(SeconderyResetTrigger).onTrue(new resetCommand(shootingSubsystem, collectSubsystem, armCollectSubsystem, cartridgeSubsystem));
        
        // OpenCollect.whileFalse(new IntakeAndArm(collectSubsystem, collectWheels, armCollectSubsystem, 0, 0, 0, 0));
        
        // shootingLow.onTrue(new ShootingGroupCommand(cartridgeSubsystem, shootingSubsystem,1850, 0.19, -0.1 , 3000 ));
        // shootingHigh.onTrue(new ShootingGroupCommand(cartridgeSubsystem, shootingSubsystem, 6000, 0.4, -0.1,3000));
        // shootinghTrigger.onTrue(new ShootingGroupCommand(cartridgeSubsystem ,shootingSubsystem,3000, 0.4, -0.1 ,300));
        
        // humanArm.onTrue(new armPosition(armSubsystem, -20.7));
        // midArm.onTrue(new armPosition(armSubsystem, -65));  
        // floorArm.onTrue(new armPosition(armSubsystem, -70.7));
        // resetTrigger.onTrue(new armPosition(armSubsystem, 0));   
    }
}
