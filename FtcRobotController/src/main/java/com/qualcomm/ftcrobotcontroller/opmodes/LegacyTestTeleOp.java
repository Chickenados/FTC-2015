package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by faithpettis on 9/19/15.
 */
public class LegacyTestTeleOp extends OpMode {

    DcMotor m1;


    public LegacyTestTeleOp(){

    }

    public void init(){
        m1 = hardwareMap.dcMotor.get("m1");

    }

    public void loop(){
        m1.setPower(1);
    }

    public void stop(){

    }

}
