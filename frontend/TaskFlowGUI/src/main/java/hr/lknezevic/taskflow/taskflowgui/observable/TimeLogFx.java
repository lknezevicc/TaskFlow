package hr.lknezevic.taskflow.taskflowgui.observable;

import javafx.beans.property.*;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeLogFx {

    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<LocalDateTime> startTime = new SimpleObjectProperty<>();
    private final ObjectProperty<Duration> duration = new SimpleObjectProperty<>();
    private final ObjectProperty<UserFx> user = new SimpleObjectProperty<>();
    private final StringProperty description = new SimpleStringProperty();

    public long getId() { return id.get(); }
    public void setId(long id) { this.id.set(id); }
    public LongProperty idProperty() { return id; }

    public LocalDateTime getStartTime() { return startTime.get(); }
    public void setStartTime(LocalDateTime startTime) { this.startTime.set(startTime); }
    public ObjectProperty<LocalDateTime> startTimeProperty() { return startTime; }

    public Duration getDuration() { return duration.get(); }
    public void setDuration(Duration duration) { this.duration.set(duration); }
    public ObjectProperty<Duration> durationProperty() { return duration; }

    public UserFx getUser() { return user.get(); }
    public void setUser(UserFx user) { this.user.set(user); }
    public ObjectProperty<UserFx> userProperty() { return user; }

    public String getDescription() { return description.get(); }
    public void setDescription(String description) { this.description.set(description); }
    public StringProperty descriptionProperty() { return description; }
}
