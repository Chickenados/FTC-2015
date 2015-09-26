package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nate on 9/19/15.
 */
public class HoloTeleOp extends OpMode {


    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;

    float FLMove;
    float FRMove;
    float BLMove;
    float BRMove;

    float speed;

    // Holo = 1, tank = 2
    int mode;

    // Constructor
    public HoloTeleOp(){

    }

    @Override
    public void init(){

        motorFrontLeft = hardwareMap.dcMotor.get("motor_fl");
        motorFrontRight = hardwareMap.dcMotor.get("motor_fr");
        motorBackLeft = hardwareMap.dcMotor.get("motor_bl");
        motorBackRight = hardwareMap.dcMotor.get("motor_br");

        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        mode = 1;

    }

    @Override
    public void loop(){

        if (gamepad1.dpad_up) {
            mode = 1;
        } else if (gamepad1.dpad_right){
            mode = 2;
        }

        if (gamepad1.right_trigger > 0) {
            speed = 0.25f;
        } else {
            speed = 1;
        }


        float forward = gamepad1.right_stick_y;
        float sideways = gamepad1.right_stick_x;
        float rotation = gamepad1.left_stick_x;
        float extra = gamepad1.left_stick_y;


        forward = Range.clip(forward, -1, 1);
        sideways = Range.clip(sideways, -1, 1);
        rotation = Range.clip(rotation, -1, 1);
        extra = Range.clip(extra, -1, 1);

        if (mode == 1) {
            FRMove = (forward - sideways + rotation) * speed;
            FLMove = (forward + sideways - rotation) * speed;
            BRMove = (forward + sideways + rotation) * speed;
            BLMove = (forward - sideways - rotation) * speed;
        } else if(mode == 2){
            FRMove = forward * speed;
            BRMove = forward * speed;
            FLMove = extra * speed;
            BLMove = extra * speed;
        }
        FRMove = Range.clip(FRMove, -1, 1);
        FLMove= Range.clip(FLMove, -1, 1);
        BRMove = Range.clip(BRMove, -1, 1);
        BLMove = Range.clip(BLMove, -1, 1);

        motorFrontRight.setPower(FRMove);
        motorFrontLeft.setPower(FLMove);
        motorBackRight.setPower(BRMove);
        motorBackLeft.setPower(BLMove);

        if (mode == 1) {
            telemetry.addData("Mode", "Holonomic");
        } else if (mode == 2){
            telemetry.addData("Mode", "Tank");
        }
        telemetry.addData("Speed", "%" + (speed * 100));
    }

    @Override
    public void stop(){

    }

}
