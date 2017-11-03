package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Sachin on 8/29/2017.
 */

public class OmegabotsHardware implements Constants{
    /* Public OpMode members. */
    public DcMotor leftFrontMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor rightBackMotor = null;
    public DcMotor leftBackMotor = null;
    public DcMotor glyphSlide = null;
    public ColorSensor colorSensorJewel = null;
    public ColorSensor colorSensorRightLineUp = null;
    public ColorSensor colorSensorLeftLineUp = null;
    public DeviceInterfaceModule deviceInterfaceModule = null;
    public Servo leftClaw = null;
    public Servo rightClaw = null;
    public Servo holdRelicClaw = null;
    public Servo wallRelicClaw = null;
    public Servo jewelPusher =  null;
    public DcMotor relicPlacer = null;


    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();


    /* Constructor */
    public OmegabotsHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFrontMotor = hwMap.dcMotor.get("lFM");
        rightFrontMotor = hwMap.dcMotor.get("rFM");
        leftBackMotor = hwMap.dcMotor.get("lBM");
        rightBackMotor = hwMap.dcMotor.get("rBM");
        glyphSlide = hwMap.dcMotor.get("glyphSlide");
        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        colorSensorJewel = hwMap.colorSensor.get("csJewel");
        deviceInterfaceModule = hwMap.deviceInterfaceModule.get("Device Interfaced Module 1");

        leftClaw = hwMap.servo.get("leftClaw");
        rightClaw = hwMap.servo.get("rightClaw");

        jewelPusher = hwMap.servo.get("jewelPusher");

        // Set all motors to zero power
        rightFrontMotor.setPower(0);
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightBackMotor.setPower(0);

    }



}
