package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Sachin on 8/6/2017.
 */
//@Autonomous (name = "Gyro 90 Turn")
public class Gyro_TurnTelling extends LinearOpMode {

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

           turnAbsolute(90);
            sleep(1000);
            turn(90);
            sleep(1000);

        }
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
