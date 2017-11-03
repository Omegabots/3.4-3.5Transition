package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Sachin on 8/6/2017.
 */
//@Autonomous(name = "Gyro Turn")
public class Gyro_Turn extends LinearOpMode {

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

        Double turnSpeed = 0.15;



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

            zAccumulated = mrGyro.getIntegratedZValue();
            while (Math.abs(zAccumulated - target) > 3) {


                if (zAccumulated > 0) {
                    leftMotor.setPower(turnSpeed);
                    rightMotor.setPower(-turnSpeed);

                }
                if (zAccumulated < 0) {
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
}
