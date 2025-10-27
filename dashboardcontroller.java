package com.healthmonitor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML private Label heartRateLabel;
    @FXML private Label temperatureLabel;
    @FXML private Label oxygenLabel;
    @FXML private Label alertLabel;

    public void initialize() {
        Thread thread = new Thread(new HealthDataSimulator(this));
        thread.setDaemon(true);
        thread.start();
    }

    public void updateHealthStats(int heart, double temp, int oxygen) {
        Platform.runLater(() -> {
            heartRateLabel.setText(heart + " bpm");
            temperatureLabel.setText(String.format("%.1f °C", temp));
            oxygenLabel.setText(oxygen + " %");

            if (heart < 60 || heart > 100 || temp > 38 || oxygen < 95) {
                alertLabel.setText("⚠ Health Alert!");
            } else {
                alertLabel.setText("✔ Normal");
            }
        });
    }
}
