package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Sachin on 10/29/2017.
 */

public class RedTest4 extends LinearOpMode implements Constants {
    OmegabotsHardware robot = new OmegabotsHardware();

    private ElapsedTime runtime = new ElapsedTime();
    //For Vuforia
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "Absutb//////AAAAGS/mLYTQnEBghpm1oCdkfGqK02MBnD2oxqqIddTosmbu3FmrqYwLUYYtyqh0IblY8o8Aa16RN57Xz0fC4YUcUhJIpIT/C0tH84s2pnJ612Oe1v8YTJNXHz4VAhxqmGJVZXy/yYuamRTEzmaLbrAMkJpPYgIeYE2kdSW9Vq6yEPgK5O/HWjRhR/HWJ7NBCSL/V5nAld7EP6fTKz9XBUu0lLoJGtKVVRjN4iCWGxQ5tCxK6rB7Y85L6LlzTOaer1EX+3DTB2Kk+YcDy0mD0sMJW01BnQLhi9YUjS2HeK4AuP2GD9YAeSWfUrBZAvXSznQydc9Y4cZGwbDSBJ+SvnNVD+to1URVf/ZM5dfi5sZJlNnr";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        //Reset all encoders
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        robot.leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        //Driver hits start
        waitForStart();
        while (opModeIsActive()) {

            robot.leftClaw.setPosition(LEFT_CLOSED_POSITION);
            robot.rightClaw.setPosition(RIGHT_CLOSED_POSITION);

            robot.glyphSlide.setPower(0.5);
            sleep(700);
            robot.glyphSlide.setPower(0.0);

            //Vuforia State
            VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
            VuforiaTrackable relicTemplate = relicTrackables.get(0);
            relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
            relicTrackables.activate();


            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */
            //If it's not unknown, find where th glyph is
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                telemetry.addData("VuMark", "%s visible", vuMark);

                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));


                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                }
            } else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();
            //Change Value
            robot.jewelPusher.setPosition(JEWEL_PUSHER_DOWN_POSITION);
            //All autonomous operations run
            boolean LEDState = true;
            robot.colorSensorJewel.enableLed(LEDState);
            float hsvValues[] = {0, 0, 0};
            while (opModeIsActive()) {
                LEDState = !LEDState;
                robot.colorSensorJewel.enableLed(LEDState);


                Color.RGBToHSV(robot.colorSensorJewel.red() * 8, robot.colorSensorJewel.green() * 8, robot.colorSensorJewel.blue() * 8, hsvValues);

                telemetry.addData("2 Clear", robot.colorSensorJewel.alpha());
                telemetry.addData("3 Red", robot.colorSensorJewel.red());
                telemetry.addData("4 Green", robot.colorSensorJewel.green());
                telemetry.addData("5 Blue", robot.colorSensorJewel.blue());
                telemetry.addData("6 Hue", hsvValues[0]);
                //If its red, then we move forward
                if (robot.colorSensorJewel.red() > robot.colorSensorJewel.blue() && robot.colorSensorJewel.red() > robot.colorSensorJewel.green()) {
                    robot.deviceInterfaceModule.setLED(1, true); // turn on the red color on DIM
                    robot.deviceInterfaceModule.setLED(0, false); // turn off the blue color on DIM
                    encoderDrive(DRIVE_SPEED, MOVE_UP_FOR_JEWEL_LEFT_MOTOR, MOVE_UP_FOR_JEWEL_RIGHT_MOTOR, SLEEP_AMOUNT);
                }
                // If it's blue, move backwards
                else if (robot.colorSensorJewel.blue() > robot.colorSensorJewel.red() && robot.colorSensorJewel.blue() > robot.colorSensorJewel.green()) {
                    robot.deviceInterfaceModule.setLED(1, false); //Red Off
                    robot.deviceInterfaceModule.setLED(0, true);// Blue On
                    encoderDrive(DRIVE_SPEED, BACK_UP_FOR_JEWEL_LEFT_MOTOR, BACK_UP_FOR_JEWEL_RIGHT_MOTOR, SLEEP_AMOUNT);
                }
                // If you can't see anything, don't do anything
                else {
                    robot.deviceInterfaceModule.setLED(1, false);// Red Off
                    robot.deviceInterfaceModule.setLED(0, false);
                }

                robot.jewelPusher.setPosition(JEWEL_PUSHER_UP_POSITION);


                //Do these task based on what vuforia sees
                if (vuMark == RelicRecoveryVuMark.LEFT) {
                    encoderDrive(SPEED_FORWARD, LEFT_LEFT_INCHES, LEFT_RIGHT_INCHES, SLEEP_AMOUNT);
                    encoderDrive(SPEED_FORWARD, TURN_LEFT_INCHES, TURN_RIGHT_INCHES, SLEEP_AMOUNT);
                    robot.rightClaw.setPosition(RIGHT_OPEN_POSITION);
                    robot.leftClaw.setPosition(LEFT_OPEN_POSITION);

                    robot.glyphSlide.setPower(0.2);
                    sleep(500);
                } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                    encoderDrive(SPEED_FORWARD, CENTER_LEFT_INCHES, CENTER_RIGHT_INCHES, SLEEP_AMOUNT);
                    encoderDrive(SPEED_FORWARD, TURN_LEFT_INCHES, TURN_RIGHT_INCHES, SLEEP_AMOUNT);
                    robot.rightClaw.setPosition(RIGHT_OPEN_POSITION);
                    robot.leftClaw.setPosition(LEFT_OPEN_POSITION);
                    robot.glyphSlide.setPower(0.2);
                    sleep(500);

                } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
                    encoderDrive(SPEED_FORWARD, RIGHT_LEFT_INCHES, RIGHT_RIGHT_INCHES, SLEEP_AMOUNT);
                    encoderDrive(SPEED_FORWARD, TURN_LEFT_INCHES, TURN_RIGHT_INCHES, SLEEP_AMOUNT);
                    robot.rightClaw.setPosition(RIGHT_OPEN_POSITION);
                    robot.leftClaw.setPosition(LEFT_OPEN_POSITION);
                    robot.glyphSlide.setPower(0.2);
                    sleep(500);

                } else if (vuMark == RelicRecoveryVuMark.UNKNOWN) {
                    robot.leftFrontMotor.setPower(STOP_MOTOR);
                    robot.rightFrontMotor.setPower(STOP_MOTOR);
                    robot.rightBackMotor.setPower(STOP_MOTOR);
                    robot.leftBackMotor.setPower(STOP_MOTOR);

                } else {
                    return;
                }

            }

        }
    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {

        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftFrontMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightFrontMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.leftFrontMotor.setTargetPosition(newLeftTarget);
            robot.rightFrontMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftFrontMotor.setPower(Math.abs(speed));
            robot.rightFrontMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftFrontMotor.isBusy() && robot.rightFrontMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.leftFrontMotor.getCurrentPosition(),
                        robot.rightFrontMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftFrontMotor.setPower(STOP_MOTOR);
            robot.rightFrontMotor.setPower(STOP_MOTOR);

            // Turn off RUN_TO_POSITION
            robot.leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
}
