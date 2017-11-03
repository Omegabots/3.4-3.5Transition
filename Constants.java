package org.firstinspires.ftc.teamcode;

/**
 * Created by Sachin on 8/29/2017.
 */

public interface Constants {
    double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/
            (WHEEL_DIAMETER_INCHES * 3.1415);
    double     DRIVE_SPEED             = 0.6;
    double     TURN_SPEED              = 0.5;

    double LEFT_OPEN_POSITION = 0.5;
    double LEFT_CLOSED_POSITION = 1.0;
    double RIGHT_OPEN_POSITION = 0.5;
    double RIGHT_CLOSED_POSITION = 0.0;

    double GLYPH_LINEAR_SLIDE_POWER = 0.25;
    double STOP_MOTOR = 0.0;
    double RELIC_PLACING_POWER = 0.5;

    double JEWEL_PUSHER_DOWN_POSITION = 0.6;
    double JEWEL_PUSHER_UP_POSITION = 0.0;

    double CENTER_LEFT_INCHES  = 24;
    double CENTER_RIGHT_INCHES = 24;

    double LEFT_LEFT_INCHES = 28;
    double LEFT_RIGHT_INCHES = 28;

    double RIGHT_LEFT_INCHES = 20;
    double RIGHT_RIGHT_INCHES = 20;

    double SPEED_FORWARD = 0.5;
    double SLEEP_AMOUNT = 0.2;

    double TURN_LEFT_INCHES = 1.0;
    double TURN_RIGHT_INCHES = -1.0;

    double BACK_UP_FOR_JEWEL_LEFT_MOTOR = -2;
    double BACK_UP_FOR_JEWEL_RIGHT_MOTOR = -2;

    double MOVE_UP_FOR_JEWEL_LEFT_MOTOR = 2;
    double MOVE_UP_FOR_JEWEL_RIGHT_MOTOR = 2;

}
