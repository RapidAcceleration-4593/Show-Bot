package frc.robot.subsystems.vision;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonUtils;
import org.photonvision.estimation.VisionEstimation;
import org.photonvision.simulation.PhotonCameraSim;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class VisionSubsystem extends SubsystemBase {
    
    private final PhotonCamera camera;

    private double targetYaw = 0.0;

    public VisionSubsystem() {
        camera = new PhotonCamera(Constants.VisionConstants.CAMERA_NAME);
    }

    @Override
    public void periodic() {
        // Correct pose estimate with vision measurements
        var results = camera.getAllUnreadResults();
        if (!results.isEmpty()) {
            // Camera processed a new frame since last
            // Get the last one in the list.
            var result = results.get(results.size() - 1);
            if (result.hasTargets()) {
                // At least one AprilTag was seen by the camera
                for (var target : result.getTargets()) {
                    if (target.getFiducialId() == Constants.VisionConstants.APRILTAG_ID) {
                        // Found Tag 7, record its information
                        targetYaw = target.getYaw();
                    }
                }
            }
        }
    }

    public double getTargetYaw() {
        return targetYaw;
    }
}
