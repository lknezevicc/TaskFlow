package hr.lknezevic.taskflow.taskflowgui.managers;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.factory.alert.AlertFactory;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

public class DownloadManager {
    private final HttpClient httpClient;
    private final AlertFactory alertFactory;

    @Inject
    public DownloadManager(AlertFactory alertFactory) {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        this.alertFactory = alertFactory;
    }

    public void download(URL url, File destination, int downloadLimitKBps, ProgressBar progressBar, Label statusLabel) {
        new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url.toString()))
                        .build();

                HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

                long contentLength = response.headers()
                        .firstValueAsLong("Content-Length")
                        .orElse(-1L);

                try (InputStream in = response.body();
                     OutputStream out = new FileOutputStream(destination)) {

                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    long totalRead = 0;
                    long startTime = System.currentTimeMillis();

                    System.out.println(contentLength);

                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                        totalRead += bytesRead;

                        if (downloadLimitKBps > 0) {
                            long elapsed = System.currentTimeMillis() - startTime;
                            long expectedTime = (totalRead / 1024) * 1000 / downloadLimitKBps;
                            if (expectedTime > elapsed) {
                                Thread.sleep(expectedTime - elapsed);
                            }
                        }

                        double progress = (contentLength > 0) ? (double) totalRead / contentLength : ProgressBar.INDETERMINATE_PROGRESS;
                        Platform.runLater(() -> {
                            progressBar.setProgress(progress);
                            statusLabel.setText(String.format("%.1f %%", progress * 100));
                        });
                    }

                    Platform.runLater(() -> {
                        statusLabel.setText("Download complete!");
                        Optional<ButtonType> result = alertFactory.createInfoAlert("Download Complete", "New version of app downloaded successfully!").showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            progressBar.setVisible(false);
                        }
                    });
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Platform.runLater(() -> statusLabel.setText("Greška: " + e.getMessage()));
            }
        }).start();
    }
}
