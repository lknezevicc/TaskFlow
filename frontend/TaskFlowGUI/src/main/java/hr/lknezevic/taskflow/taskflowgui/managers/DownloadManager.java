package hr.lknezevic.taskflow.taskflowgui.managers;

import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DownloadManager {
    private final HttpClient httpClient;

    @Inject
    public DownloadManager(HttpClient httpClient) {
        this.httpClient = httpClient;
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

                        double progress = (contentLength > 0) ? (double) totalRead / contentLength : -1;
                        Platform.runLater(() -> {
                            progressBar.setProgress(progress);
                            statusLabel.setText(String.format("%.1f %%", progress * 100));
                        });
                    }

                    Platform.runLater(() -> statusLabel.setText("Gotovo!"));
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Platform.runLater(() -> statusLabel.setText("Greška: " + e.getMessage()));
            }
        }).start();
    }
}
