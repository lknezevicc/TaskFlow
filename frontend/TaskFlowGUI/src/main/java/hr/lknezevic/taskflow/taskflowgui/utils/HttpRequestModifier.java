package hr.lknezevic.taskflow.taskflowgui.utils;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.enums.PlistProperty;

import java.net.http.HttpRequest;
import java.util.Set;

public class HttpRequestModifier {
    private final PlistUtil plistUtil;
    private final Set<String> excludedUrls;

    @Inject
    public HttpRequestModifier(PlistUtil plistUtil) {
        this.plistUtil = plistUtil;
        this.excludedUrls = Set.of(
                "http://localhost:8080/auth/login",
                "http://localhost:8080/auth/register"
        );
    }

    public HttpRequest.Builder applyDefaults(HttpRequest.Builder builder, String url) {
        if (!excludedUrls.contains(url)) {
            try {
                String token = plistUtil.readValue(PlistProperty.ACCESS_TOKEN);
                if (token != null && !token.isEmpty()) {
                    builder.header("Authorization", "Bearer " + token);
                }
            } catch (Exception e) {
                System.out.println("Token is empty");
            }
        }

        return builder.header("Content-Type", "application/json");
    }
}
