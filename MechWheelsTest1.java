package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Sachin on 9/28/2017.
 */

public class MechWheelsTest1 extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;


    @Override
    public void init() {
        frontLeftMotor = hardwareMap.dcMotor.get("lFM");
        backLeftMotor = hardwareMap.dcMotor.get("lBM");
        frontRightMotor = hardwareMap.dcMotor.get("rFM");
        backRightMotor = hardwareMap.dcMotor.get("rBM");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {



    }
}
