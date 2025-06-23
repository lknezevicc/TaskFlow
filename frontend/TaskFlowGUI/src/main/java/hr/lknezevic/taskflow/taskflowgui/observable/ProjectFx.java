package hr.lknezevic.taskflow.taskflowgui.observable;

import hr.lknezevic.taskflow.taskflowgui.enums.ProjectPriority;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectFx {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<ProjectPriority> priority  = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> createdAt = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> deadline = new SimpleObjectProperty<>();
    private final ListProperty<TaskFx> tasks = new SimpleListProperty<>(FXCollections.observableArrayList());

    public long getId() { return id.get(); }
    public void setId(long id) { this.id.set(id); }
    public LongProperty idProperty() { return id; }

    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return title; }

    public String getDescription() { return description.get(); }
    public void setDescription(String description) { this.description.set(description); }
    public StringProperty descriptionProperty() { return description; }

    public ProjectPriority getPriority() { return priority.get(); }
    public void setPriority(ProjectPriority priority) { this.priority.set(priority); }
    public ObjectProperty<ProjectPriority> priorityProperty() { return priority; }

    public LocalDateTime getCreatedAt() { return createdAt.get(); }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt.set(createdAt); }
    public ObjectProperty<LocalDateTime> createdAtProperty() { return createdAt; }

    public LocalDateTime getDeadline() { return deadline.get(); }
    public void setDeadline(LocalDateTime deadline) { this.deadline.set(deadline); }
    public ObjectProperty<LocalDateTime> deadlineProperty() { return deadline; }

    public ObservableList<TaskFx> getTasks() { return tasks.get(); }
    public void setTasks(List<TaskFx> tasks) {
        if (tasks != null)
            this.tasks.set(FXCollections.observableArrayList(tasks));
    }
    public ListProperty<TaskFx> tasksProperty() { return tasks; }
}
