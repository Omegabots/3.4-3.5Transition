package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.Range;

import java.util.jar.Attributes;

/**
 * Created by Sachin on 8/6/2017.
 */
//@Autonomous(name = "Gyro Straight")
public class Gyro_Straight extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    GyroSensor sensorGyro;
    ModernRoboticsI2cGyro mrGyro;
    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sensorGyro = hardwareMap.gyroSensor.get("gyro");
        mrGyro = (ModernRoboticsI2cGyro) sensorGyro;
        int zAccumulated;
        int target = 0;

        sleep(1000);

        mrGyro.calibrate();

        waitForStart();

        while (mrGyro.isCalibrating()) {

        }
        while (opModeIsActive()) {
            driveStraight(3000, 0.30);
            sleep(1000);

            waitOneFullHardwareCycle();


        }
    }
    public void driveStraight(int duration, double power) throws InterruptedException {
        double leftSpeed;
        double rightSpeed;

        double target = mrGyro.getIntegratedZValue();
        double startPostion = leftMotor.getCurrentPosition();

        while (leftMotor.getCurrentPosition() < duration + startPostion){
            int zAccumulated = mrGyro.getIntegratedZValue();
            leftSpeed = power + (zAccumulated - target)/100;
            rightSpeed = power - (zAccumulated - target)/100;


            leftSpeed = Range.clip(leftSpeed, -1,1);
            rightSpeed = Range.clip(rightSpeed, -1,1);

            leftMotor.setPower(leftSpeed);
            rightMotor.setPower(rightSpeed);

            telemetry.addData("1. Left", leftMotor.getPower());
            telemetry.addData("2. Right", rightMotor.getPower());
            telemetry.addData("3. Distance to Go", duration + startPostion - leftMotor.getCurrentPosition());

            waitOneFullHardwareCycle();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        waitOneFullHardwareCycle();
    }

    public void turn(int target) throws InterruptedException{
        turnAbsolute(target + mrGyro.getIntegratedZValue());
    }

    public void turnAbsolute(int target)  throws InterruptedException {
        int zAccumulated = mrGyro.getIntegratedZValue();
        Double turnSpeed = 0.15;

        while (Math.abs(zAccumulated - target) > 3) {


            if (zAccumulated > target) {
                leftMotor.setPower(turnSpeed);
                rightMotor.setPower(-turnSpeed);

            }
            if (zAccumulated < target) {
                leftMotor.setPower(-turnSpeed);
                rightMotor.setPower(turnSpeed);

            }
            waitOneFullHardwareCycle();

            zAccumulated = mrGyro.getIntegratedZValue();
            telemetry.addData("1. accu", String.format("%03d", zAccumulated));
        }



        leftMotor.setPower(0);
        rightMotor.setPower(0);
        telemetry.addData("1. accu", String.format("%03d", zAccumulated));


        waitOneFullHardwareCycle();


    }
}
