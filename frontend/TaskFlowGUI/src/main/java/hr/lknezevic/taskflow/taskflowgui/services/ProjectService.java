package hr.lknezevic.taskflow.taskflowgui.services;

import hr.lknezevic.taskflow.taskflowgui.dto.ProjectDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProjectService {
    CompletableFuture<List<ProjectDto>> findAllProjects();
    void saveToTemp(ProjectDto projectDto);
    ProjectDto loadFromTemp();
}
