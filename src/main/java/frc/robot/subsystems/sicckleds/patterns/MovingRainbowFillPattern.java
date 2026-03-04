package frc.robot.subsystems.sicckleds.patterns;

import static frc.robot.subsystems.sicckleds.LEDConstants.*;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.subsystems.sicckleds.LEDSubsystem;

public class MovingRainbowFillPattern implements RunnableLEDPattern {

    private final LEDSubsystem subsystem;

    public MovingRainbowFillPattern(LEDSubsystem subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public void run() {
        // subsystem.applyPattern(LEDPattern.rainbow(255, 255).offsetBy(subsystem.getAnimationFrame() *
        // kRainbowFactor));
        for (int i = 0; i < kLEDCount; i++) {
            double progress = (double) i / kLEDCount;

            int hue = (int) ((progress + (double) subsystem.getAnimationFrame() / kLEDCount) * 180.0 * kRainbowFactor)
                    % 180;

            subsystem.setLEDColor(i, Color.fromHSV(hue, 255, 255));
        }
    }
}
