package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Sachin on 9/30/2017.
 */
@Autonomous
public class Red1 extends LinearOpMode implements Constants {
    com.qualcomm.robotcore.hardware.ColorSensor colorSensor;
    DeviceInterfaceModule deviceInterfaceModule;
    DcMotor leftFrontMotor;
    DcMotor rightFrontMotor;
    //OmegabotsHardware robot   = new OmegabotsHardware();
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.colorSensor.get("color");
        deviceInterfaceModule = hardwareMap.deviceInterfaceModule.get("Device Interfaced Module 1");
        leftFrontMotor = hardwareMap.dcMotor.get("lFM");
        rightFrontMotor = hardwareMap.dcMotor.get("rFM");
        boolean LEDState = true;

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();

        colorSensor.enableLed(LEDState);

        float hsvValues[] = {0, 0, 0};
        while (opModeIsActive()){
            LEDState = !LEDState;
            colorSensor.enableLed(LEDState);


            Color.RGBToHSV(colorSensor.red() *8, colorSensor.green() *8, colorSensor.blue() *8, hsvValues);

            telemetry.addData("2 Clear", colorSensor.alpha());
            telemetry.addData("3 Red", colorSensor.red());
            telemetry.addData("4 Green", colorSensor.green());
            telemetry.addData("5 Blue", colorSensor.blue());
            telemetry.addData("6 Hue", hsvValues[0]);

            if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()){
                deviceInterfaceModule.setLED(1, true); // turn on the red color on DIM
                deviceInterfaceModule.setLED(0, false); // turn off the blue color on DIM
                encoderDrive(DRIVE_SPEED,  2,  2, 5.0);
            }
            else if(colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()){
                deviceInterfaceModule.setLED(1, false); //Red Off
                deviceInterfaceModule.setLED(0, true);// Blue On
                encoderDrive(DRIVE_SPEED,  -2,  -2, 5.0);
            }
            else{
                deviceInterfaceModule.setLED(1, false);// Red Off
                deviceInterfaceModule.setLED(0, false);
            }


    }
    }
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftFrontMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = rightFrontMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            leftFrontMotor.setTargetPosition(newLeftTarget);
           rightFrontMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftFrontMotor.setPower(Math.abs(speed));
            rightFrontMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFrontMotor.isBusy() && rightFrontMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        leftFrontMotor.getCurrentPosition(),
                        rightFrontMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            leftFrontMotor.setPower(0);
            rightFrontMotor.setPower(0);

            // Turn off RUN_TO_POSITION
           leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}
