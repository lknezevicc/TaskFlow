package hr.lknezevic.taskflow.taskflowgui.utils;

import hr.lknezevic.taskflow.taskflowgui.dto.ProjectDto;
import hr.lknezevic.taskflow.taskflowgui.dto.TaskDto;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerializationUtil {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final File tempTaskFile;
    private final File tempProjectFile;

    public SerializationUtil() {
        this.tempTaskFile = new File(System.getProperty("user.dir"), "task.ser");
        this.tempProjectFile = new File(System.getProperty("user.dir"), "project.ser");
    }

    public void saveTask(TaskDto task) {
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.activeCount());
            try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(tempTaskFile))) {
                ous.writeObject(task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public TaskDto loadTask() {
        if (!tempTaskFile.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempTaskFile))) {
            return (TaskDto) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveProject(ProjectDto proejct) {
        executorService.submit(() -> {
            try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(tempProjectFile))) {
                ous.writeObject(proejct);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public ProjectDto loadProject() {
        if (!tempProjectFile.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempProjectFile))) {
            return (ProjectDto) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}