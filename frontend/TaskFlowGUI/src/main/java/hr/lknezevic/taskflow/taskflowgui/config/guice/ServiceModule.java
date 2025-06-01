package hr.lknezevic.taskflow.taskflowgui.config.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import hr.lknezevic.taskflow.taskflowgui.utils.PlistUtil;
import hr.lknezevic.taskflow.taskflowgui.services.AuthService;
import hr.lknezevic.taskflow.taskflowgui.services.UserService;
import hr.lknezevic.taskflow.taskflowgui.services.impl.AuthServiceImpl;
import hr.lknezevic.taskflow.taskflowgui.services.impl.UserServiceImpl;
import hr.lknezevic.taskflow.taskflowgui.utils.JsonUtil;
import hr.lknezevic.taskflow.taskflowgui.utils.XmlUtil;

import java.net.http.HttpClient;
import java.time.Duration;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthService.class).to(AuthServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
    }

    @Provides
    @Singleton
    public HttpClient provideHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Provides
    @Singleton
    public PlistUtil providePlistUtil() {
        return new PlistUtil();
    }

    @Provides
    public JsonUtil provideJsonUtil() {
        return new JsonUtil();
    }

    @Provides
    @Singleton
    public XmlUtil provideXmlUtil() {
        return new XmlUtil();
    }
}
