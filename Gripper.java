package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Sachin on 9/5/2016.
 */;
@TeleOp (name = "Gripper2", group = "omegabots")
public class Gripper extends OpMode implements Constants {
    OmegabotsHardware robot   = new OmegabotsHardware();
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void init() {
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {
        if(gamepad1.x) {
            robot.leftClaw.setPosition(LEFT_OPEN_POSITION);
            robot.rightClaw.setPosition(RIGHT_OPEN_POSITION);
        }
        if (gamepad1.a) {
            robot.leftClaw.setPosition(LEFT_CLOSED_POSITION);
            robot.rightClaw.setPosition(RIGHT_CLOSED_POSITION);
        }

    }     
}
