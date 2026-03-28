package frc.robot.utils;

import java.util.HashMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeTable {
    public HashMap<String, Double> getNetworkTable() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double tv = table.getEntry("tv").getDouble(0.0); // 0 or 1
        double tx = table.getEntry("tx").getDouble(0.0); // -27 to 27 degrees
        double ty = table.getEntry("ty").getDouble(0.0); // -20.5 to 20.5 degrees
        double ta = table.getEntry("ta").getDouble(0.0); // 0 to 100%
        HashMap<String, Double> limeData = new HashMap<>();
        limeData.put("tv", tv);
        limeData.put("tx", tx);
        limeData.put("ty", ty);
        limeData.put("ta", ta);
        return limeData;
    }
    
}
