package org.firstinspires.ftc.teamcode.subsystems.constants;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
public class SubsystemConstants {
    public static int DEGREES_PER_ROTATION = 360;

    public static class Lift {
        public static double DEL_CLOSE_POS = 0.45;
        public static double DEL_OPEN_POS = 0.87;

        public static double CAP_PICK_UP_POS = 0.12;
        public static double CAP_LOWEST_POS = 0.08;
        public static double CAP_MID_POS = 0.33;
        public static double CAP_HIGH_POS = 0.93;

        public static int LIFT_RESTING_POSITION = 0;
        public static int LIFT_LOW_POSITION_AUTON = 200;
        public static int LIFT_MID_POSITION = 465;
        public static int LIFT_MID_AUTON = 650;
        public static int LIFT_MID_POSITION_TELE = 800;
        public static int LIFT_HIGH_POSITION = 1200;

        public static double LIFT_UP_SPEED = 0.6;
        public static double LIFT_DOWN_SPEED = -0.3;

        public static double LIFT_TICKS_PER_ROTATION = 384.5; //383.6

        public static PIDFCoefficients LIFT_PID_COEFFICIENTS = new PIDFCoefficients(0.009, 0.00008, 0, 0);

        public static int LIFT_TOLERANCE = 3;

        public static String LIFT_MOTOR_ID = "lift";
        public static String DELIVERY_MOTOR_ID = "delivery";
        public static String CAP_SERVO_ID = "capServo";
    }

    public static class Intake {
        public static double INTAKE_SPEED = 1;
        public static double OUTTAKE_SPEED = -1;

        public static String INTAKE_MOTOR_ID = "intake";
        public static String DISTANCE_SENSOR_ID = "distance";
    }

    public static class DuckWheels {
        public static double RED_SPEED = 1;
        public static double BLUE_SPEED = -1;
        public static double AUTON_RED_SPEED = 0.35;
        public static double AUTON_BLUE_SPEED = -0.35;
        public static String DUCK_SERVO_ID = "left_393";
    }
// sudo apt-get find new-head-builder | sudo apt-get install working-bot
}