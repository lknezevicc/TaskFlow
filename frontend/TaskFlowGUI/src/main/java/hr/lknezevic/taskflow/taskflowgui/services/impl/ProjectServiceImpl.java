package hr.lknezevic.taskflow.taskflowgui.services.impl;

import com.google.inject.Inject;
import hr.lknezevic.taskflow.taskflowgui.dto.ProjectDto;
import hr.lknezevic.taskflow.taskflowgui.services.BaseService;
import hr.lknezevic.taskflow.taskflowgui.services.ProjectService;
import hr.lknezevic.taskflow.taskflowgui.utils.HttpRequestModifier;
import hr.lknezevic.taskflow.taskflowgui.utils.JsonUtil;
import hr.lknezevic.taskflow.taskflowgui.utils.SerializationUtil;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProjectServiceImpl extends BaseService implements ProjectService {
    private final SerializationUtil serializationUtil;

    @Inject
    public ProjectServiceImpl(HttpClient httpClient, JsonUtil jsonUtil, HttpRequestModifier httpRequestModifier, SerializationUtil serializationUtil) {
        super(httpClient, jsonUtil, httpRequestModifier);
        this.serializationUtil = serializationUtil;
    }

    @Override
    public CompletableFuture<List<ProjectDto>> findAllProjects() {
        return getList("http://localhost:3000/projects", ProjectDto.class);
    }

    @Override
    public void saveToTemp(ProjectDto projectDto) {
        serializationUtil.saveProject(projectDto);
    }

    @Override
    public ProjectDto loadFromTemp() {
        return serializationUtil.loadProject();
    }
}
