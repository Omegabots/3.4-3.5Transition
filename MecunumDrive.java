package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import com.qualcomm.robotcore.util.Range;

/**
 * Created by Sachin on 2/9/2017.
 */
@TeleOp(name = "MechWheels", group = "OmegaBotsFTC")
public class MecunumDrive extends OpMode implements Constants {
    OmegabotsHardware robot   = new OmegabotsHardware();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        float LFSpeed = gamepad1.left_stick_y - gamepad1.left_stick_x;
        float LBSpeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
        float RFSpeed = gamepad1.right_stick_y + gamepad1.left_stick_x;
        float RBSpeed = gamepad1.right_stick_y - gamepad1.left_stick_x;

        LFSpeed = Range.clip(LFSpeed, -1, 1);
        LBSpeed = Range.clip(LBSpeed, -1, 1);
        RFSpeed = Range.clip(RFSpeed, -1, 1);
        RBSpeed = Range.clip(RBSpeed, -1, 1);


        robot.rightFrontMotor.setPower(RFSpeed);
        robot.rightBackMotor.setPower(RBSpeed);
        robot.leftFrontMotor.setPower(LFSpeed);
        robot.leftBackMotor.setPower(LBSpeed);
        //Holding the glyph
        if(gamepad2.x) {
            robot.leftClaw.setPosition(LEFT_OPEN_POSITION);
            robot.rightClaw.setPosition(RIGHT_OPEN_POSITION);
        }
        if (gamepad2.a) {
            robot.leftClaw.setPosition(LEFT_CLOSED_POSITION);
            robot.rightClaw.setPosition(RIGHT_CLOSED_POSITION);
        }
        //Moving up or down depending on where the glyph has to go
        if (gamepad1.dpad_up){
            robot.glyphSlide.setPower(GLYPH_LINEAR_SLIDE_POWER);
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

        //End Game Relic Placing
        if (gamepad1.left_bumper){
            robot.relicPlacer.setPower(RELIC_PLACING_POWER);
        }
        if (gamepad1.right_bumper){
            robot.relicPlacer.setPower(STOP_MOTOR);
        }
        if (gamepad1.b){
           //Change this value
            robot.holdRelicClaw.setPosition(0.5);
        }
        if (gamepad1.y){
            // Change this value
            robot.wallRelicClaw.setPosition(0.5);
        }

    }
}
