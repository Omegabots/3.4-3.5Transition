package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Sachin on 10/23/2017.
 */
@TeleOp (name = "Glyph Slide", group = "Omegabots")
public class glyphSlide extends OpMode implements Constants {
    OmegabotsHardware robot   = new OmegabotsHardware();
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void init() {
        robot.glyphSlide = hardwareMap.dcMotor.get("glyphSlide");

    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up){
            robot.glyphSlide.setPower(0.75);
        }
        if (gamepad1.dpad_down){
            robot.glyphSlide.setPower(-GLYPH_LINEAR_SLIDE_POWER);
        }
        if (gamepad1.dpad_right){
            robot.glyphSlide.setPower(STOP_MOTOR);
        }
        if (gamepad1.dpad_left){
            robot.glyphSlide.setPower(STOP_MOTOR);
        }

    }
}
