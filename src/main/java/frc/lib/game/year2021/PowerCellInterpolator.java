package frc.lib.game.year2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PowerCellInterpolator {

    // Saved Lists
    private List<PowerCellDataPoint> dataPointList;

    public PowerCellInterpolator() {
        // Initialize the Data Point List
        dataPointList = new ArrayList<PowerCellDataPoint>();
    }

    public void addDataPoint(PowerCellDataPoint dataPoint) {
        dataPointList.add(dataPoint);

        Collections.sort(dataPointList);
    }

    public void removeDataPoint(PowerCellDataPoint dataPoint) {
        dataPointList.remove(dataPoint);
    }

    public int calculateShooterSpeed(double distance, double damagePercentage) {
        int tempShooterSpeed = 0;

        if (dataPointList.size() == 1) {
            tempShooterSpeed = lerp(dataPointList.get(0).getGoodShooterSpeed(),
                    dataPointList.get(0).getBadShooterSpeed(), damagePercentage);
        } else if (dataPointList.size() > 1) {
            PowerCellDataPoint upperDataPoint = new PowerCellDataPoint(-1.0, 0, 0, 0.0, 0.0);
            PowerCellDataPoint lowerDataPoint = new PowerCellDataPoint(-1.0, 0, 0, 0.0, 0.0);

            for (int i = 0; i < dataPointList.size(); i++) {
                if (dataPointList.get(i).getDistance() >= distance) {
                    upperDataPoint = dataPointList.get(i);

                    break;
                }

                lowerDataPoint = dataPointList.get(i);
            }

            if (lowerDataPoint.getDistance() == -1.0) {
                tempShooterSpeed = lerp(upperDataPoint.getGoodShooterSpeed(), upperDataPoint.getBadShooterSpeed(),
                        damagePercentage);
            } else if (upperDataPoint.getDistance() == -1.0) {
                tempShooterSpeed = lerp(lowerDataPoint.getGoodShooterSpeed(), lowerDataPoint.getBadShooterSpeed(),
                        damagePercentage);
            } else {
                int upperShooterSpeed = lerp(upperDataPoint.getGoodShooterSpeed(),
                        upperDataPoint.getBadShooterSpeed(), damagePercentage);
                int lowerShooterSpeed = lerp(lowerDataPoint.getGoodShooterSpeed(),
                        lowerDataPoint.getBadShooterSpeed(), damagePercentage);

                tempShooterSpeed = lerp(lowerShooterSpeed, upperShooterSpeed, (distance - lowerDataPoint.getDistance())
                        / (upperDataPoint.getDistance() - lowerDataPoint.getDistance()));
            }
        }

        SmartDashboard.putNumber("Shooter Speed", tempShooterSpeed);

        return tempShooterSpeed;
    }

    

    public double calculateHoodPosition(double distance, double damagePercentage) {
        double tempHoodPosition = 0.0;

        if (dataPointList.size() == 1) {
            tempHoodPosition = lerp(dataPointList.get(0).getGoodHoodPosition(),
                    dataPointList.get(0).getBadHoodPosition(), damagePercentage);
        } else if (dataPointList.size() > 1) {
            PowerCellDataPoint upperDataPoint = new PowerCellDataPoint(-1.0, 0, 0, 0.0, 0.0);
            PowerCellDataPoint lowerDataPoint = new PowerCellDataPoint(-1.0, 0, 0, 0.0, 0.0);

            for (int i = 0; i < dataPointList.size(); i++) {
                if (dataPointList.get(i).getDistance() >= distance) {
                    upperDataPoint = dataPointList.get(i);

                    break;
                }

                lowerDataPoint = dataPointList.get(i);
            }

            if (lowerDataPoint.getDistance() == -1.0) {
                tempHoodPosition = lerp(upperDataPoint.getGoodHoodPosition(), upperDataPoint.getBadHoodPosition(),
                        damagePercentage);
            } else if (upperDataPoint.getDistance() == -1.0) {
                tempHoodPosition = lerp(lowerDataPoint.getGoodHoodPosition(), lowerDataPoint.getBadHoodPosition(),
                        damagePercentage);
            } else {
                double upperHoodPosition = lerp(upperDataPoint.getGoodHoodPosition(),
                        upperDataPoint.getBadHoodPosition(), damagePercentage);
                double lowerHoodPosition = lerp(lowerDataPoint.getGoodHoodPosition(),
                        lowerDataPoint.getBadHoodPosition(), damagePercentage);

                tempHoodPosition = lerp(lowerHoodPosition, upperHoodPosition, (distance - lowerDataPoint.getDistance())
                        / (upperDataPoint.getDistance() - lowerDataPoint.getDistance()));
            }
        }

        SmartDashboard.putNumber("Distance", distance);
        SmartDashboard.putNumber("Hood Position", tempHoodPosition);
        

        return tempHoodPosition;
    }

    private double lerp(double start, double end, double count) {
        return start + (count * (end - start));
    }

    private int lerp(int start, int end, double count) {
        return (int) Math.round(start + (count * (end - start)));
    }
}
