package hr.lknezevic.taskflow.taskflowgui.observable;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class CommentFx {
    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<LocalDateTime> commentedAt = new SimpleObjectProperty<>();
    private final ObjectProperty<UserFx> user = new SimpleObjectProperty<>();
    private final StringProperty description = new SimpleStringProperty();

    public long getId() { return id.get(); }
    public void setId(long id) { this.id.set(id); }
    public LongProperty idProperty() { return id; }

    public LocalDateTime getCommentedAt() { return commentedAt.get(); }
    public void setCommentedAt(LocalDateTime commentedAt) { this.commentedAt.set(commentedAt); }
    public ObjectProperty<LocalDateTime> commentedAtProperty() { return commentedAt; }

    public UserFx getUser() { return user.get(); }
    public void setUser(UserFx user) { this.user.set(user); }
    public ObjectProperty<UserFx> userProperty() { return user; }

    public String getDescription() { return description.get(); }
    public void setDescription(String description) { this.description.set(description); }
    public StringProperty descriptionProperty() { return description; }
}
