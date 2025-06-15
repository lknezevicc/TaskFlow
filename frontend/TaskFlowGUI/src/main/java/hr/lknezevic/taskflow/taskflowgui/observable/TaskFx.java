package hr.lknezevic.taskflow.taskflowgui.observable;

import hr.lknezevic.taskflow.taskflowgui.enums.TaskPriority;
import hr.lknezevic.taskflow.taskflowgui.enums.TaskStatus;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskFx {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> dueDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> createdAt = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> updatedAt = new SimpleObjectProperty<>();
    private final ObjectProperty<TaskStatus> status = new SimpleObjectProperty<>();
    private final ObjectProperty<TaskPriority> priority = new SimpleObjectProperty<>();
    private final ObjectProperty<UserFx> assignedTo = new SimpleObjectProperty<>();
    private final ObjectProperty<ProjectFx> project = new SimpleObjectProperty<>();
    private final ObjectProperty<Duration> timeSpent = new SimpleObjectProperty<>();
    private final ListProperty<CommentFx> comments = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ListProperty<TimeLogFx> timeLogs = new SimpleListProperty<>(FXCollections.observableArrayList());

    // ID
    public long getId() { return id.get(); }
    public void setId(long id) { this.id.set(id); }
    public LongProperty idProperty() { return id; }

    // Title
    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return title; }

    // Description
    public String getDescription() { return description.get(); }
    public void setDescription(String description) { this.description.set(description); }
    public StringProperty descriptionProperty() { return description; }

    // Due Date
    public LocalDateTime getDueDate() { return dueDate.get(); }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate.set(dueDate); }
    public ObjectProperty<LocalDateTime> dueDateProperty() { return dueDate; }

    // Created At
    public LocalDateTime getCreatedAt() { return createdAt.get(); }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt.set(createdAt); }
    public ObjectProperty<LocalDateTime> createdAtProperty() { return createdAt; }

    // Updated At
    public LocalDateTime getUpdatedAt() { return updatedAt.get(); }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt.set(updatedAt); }
    public ObjectProperty<LocalDateTime> updatedAtProperty() { return updatedAt; }

    // Status
    public TaskStatus getStatus() { return status.get(); }
    public void setStatus(TaskStatus status) { this.status.set(status); }
    public ObjectProperty<TaskStatus> statusProperty() { return status; }

    // Priority
    public TaskPriority getPriority() { return priority.get(); }
    public void setPriority(TaskPriority priority) { this.priority.set(priority); }
    public ObjectProperty<TaskPriority> priorityProperty() { return priority; }

    // Assigned To
    public UserFx getAssignedTo() { return assignedTo.get(); }
    public void setAssignedTo(UserFx assignedTo) { this.assignedTo.set(assignedTo); }
    public ObjectProperty<UserFx> assignedToProperty() { return assignedTo; }

    // Project
    public ProjectFx getProject() { return project.get(); }
    public void setProject(ProjectFx project) { this.project.set(project); }
    public ObjectProperty<ProjectFx> projectProperty() { return project; }

    // Time Spent
    public Duration getTimeSpent() { return timeSpent.get(); }
    public void setTimeSpent(Duration timeSpent) { this.timeSpent.set(timeSpent); }
    public ObjectProperty<Duration> timeSpentProperty() { return timeSpent; }

    // Comments
    public ObservableList<CommentFx> getComments() { return comments.get(); }
    public void setComments(List<CommentFx> comments) {
        if (comments != null)
            this.comments.set(FXCollections.observableArrayList(comments));
    }
    public ListProperty<CommentFx> commentsProperty() { return comments; }

    // Time Logs
    public ObservableList<TimeLogFx> getTimeLogs() { return timeLogs.get(); }
    public void setTimeLogs(List<TimeLogFx> timeLogs) {
        if (timeLogs != null)
            this.timeLogs.set(FXCollections.observableArrayList(timeLogs));
    }
    public ListProperty<TimeLogFx> timeLogsProperty() { return timeLogs; }
}
