package hr.lknezevic.taskflow.taskflowgui.observable;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ProjectFx {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final ListProperty<TaskFx> tasks = new SimpleListProperty<>(FXCollections.observableArrayList());

    public long getId() { return id.get(); }
    public void setId(long id) { this.id.set(id); }
    public LongProperty idProperty() { return id; }

    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return title; }

    public ObservableList<TaskFx> getTasks() { return tasks.get(); }
    public void setTasks(List<TaskFx> tasks) {
        if (tasks != null)
            this.tasks.set(FXCollections.observableArrayList(tasks));
    }
    public ListProperty<TaskFx> tasksProperty() { return tasks; }
}
