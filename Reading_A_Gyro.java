package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Sachin on 8/6/2017.
 */
//@Autonomous(name = "Reading a Gyro")
public class Reading_A_Gyro extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        int zAccumulated;
        int heading;
        int xVal, yVal, zVal;

        GyroSensor sensorGyro;
        ModernRoboticsI2cGyro mrGyro;

        sensorGyro = hardwareMap.gyroSensor.get("gyro");
        mrGyro = (ModernRoboticsI2cGyro) sensorGyro;

        mrGyro.calibrate();

        waitForStart();

        while (mrGyro.isCalibrating()) {

        }
        while (opModeIsActive()) {

            zAccumulated = mrGyro.getIntegratedZValue();
            heading = 360 - mrGyro.getHeading();
            if (heading == 360) {
                heading = 0;
            }

            xVal = mrGyro.rawX() / 128;
            yVal = mrGyro.rawY() / 128;
            zVal = mrGyro.rawZ() / 128;

            telemetry.addData("1. heading", String.format("%03d", heading));
            telemetry.addData("2. accu", String.format("%03d", zAccumulated));
            telemetry.addData("3. X", String.format("%03d", xVal));
            telemetry.addData("4. Y", String.format("%03d", yVal));
            telemetry.addData("5. Z", String.format("%03d", zVal));

            waitOneFullHardwareCycle();

        }
    }
}
