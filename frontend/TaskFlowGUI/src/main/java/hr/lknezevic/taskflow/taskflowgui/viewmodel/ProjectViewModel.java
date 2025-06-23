package hr.lknezevic.taskflow.taskflowgui.viewmodel;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.dto.ProjectDto;
import hr.lknezevic.taskflow.taskflowgui.enums.ProjectPriority;
import hr.lknezevic.taskflow.taskflowgui.mappers.ProjectMapper;
import hr.lknezevic.taskflow.taskflowgui.mappers.TaskMapper;
import hr.lknezevic.taskflow.taskflowgui.observable.ProjectFx;
import hr.lknezevic.taskflow.taskflowgui.observable.TaskFx;
import hr.lknezevic.taskflow.taskflowgui.services.ProjectService;
import hr.lknezevic.taskflow.taskflowgui.services.TaskService;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.List;

public class ProjectViewModel {
    private final ProjectService projectService;
    private final TaskService taskService;
    private final ProjectMapper projectMapper;
    private final TaskMapper taskMapper;

    private final ObservableList<ProjectFx> allProjects = FXCollections.observableArrayList();
    private final FilteredList<ProjectFx> filteredProjects = new FilteredList<>(allProjects);
    private final ObjectProperty<ProjectFx> tempProject = new SimpleObjectProperty<>();

    @Inject
    public ProjectViewModel(ProjectService projectService, TaskService taskService, ProjectMapper projectMapper, TaskMapper taskMapper) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.projectMapper = projectMapper;
        this.taskMapper = taskMapper;
    }

    public ObservableList<ProjectFx> getAllProjects() {
        return allProjects;
    }

    public FilteredList<ProjectFx> getFilteredProjects() {
        return filteredProjects;
    }

    public ObjectProperty<ProjectFx> tempProjectProperty() {
        return tempProject;
    }

    public void loadAllProjects() {
        projectService.findAllProjects().thenAccept(projects -> {
            List<ProjectFx> projectsFx = projects.stream()
                    .map(projectDto -> projectMapper.toFx(projectDto, null))
                    .toList();

            Platform.runLater(() -> allProjects.setAll(projectsFx));
        });
    }

    public void applyFilter(String search, ProjectPriority priority) {
        filteredProjects.setPredicate(project -> {
            boolean matchesSearch = search == null || search.isBlank()
                    || project.getTitle().toLowerCase().contains(search.toLowerCase());

            boolean matchesFilter = priority == null || project.getPriority().equals(priority);

            return matchesSearch && matchesFilter;
        });
    }

    public void saveToTemp(ProjectFx project) {
        projectService.saveToTemp(projectMapper.toDto(project));
    }

    public void loadTempProject() {
        ProjectDto tempProjectDto = projectService.loadFromTemp();
        if (tempProjectDto == null) return;

        ProjectFx fxProject = projectMapper.toFx(tempProjectDto, null); // još bez taskova

        taskService.findTasksByProjectId(tempProjectDto.id()).thenAccept(tasks -> {
            List<TaskFx> tasksDto = tasks.stream()
                    .map(taskDto -> taskMapper.toFx(taskDto, null, fxProject, null, null))
                    .toList();

            Platform.runLater(() -> {
                fxProject.setTasks(tasksDto);
                tempProject.set(fxProject);
            });
        });
    }

    public void saveTaskToTemp(TaskFx task) {
        taskService.saveToTemp(taskMapper.toDto(task));
    }

    public void generatePdf(ProjectFx project) {
        // implementacija eksportiranja projekta i taskova u PDF
    }

}
