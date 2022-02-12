package com.jurassic.jurassiccrm.task.model;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import com.jurassic.jurassiccrm.task.model.exception.IllegalTaskStateChangeException;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import com.jurassic.jurassiccrm.task.model.state.TaskState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ActiveProfiles("test")
public class ChangeTaskStateTest {

    private User dumbUser = new User();

    @ParameterizedTest
    @MethodSource("provideNewTasks")
    public void testCreatedTaskHasOpenState(Task task, boolean expected) {
        Assertions.assertEquals(expected, TaskState.OPEN == task.getStatus());
    }

    @ParameterizedTest
    @MethodSource("provideTasksToTestProgress")
    @DisplayName("Test that only opened tasks can be moved to 'IN PROGRESS' state")
    public void testOpenTaskCanBeMovedToInProgressOnly(Task task, boolean expectedToThrow) {
        if (expectedToThrow) {
            String message = String.format("Task of type: %s and state %s can't go 'In Progress'", task.getTaskType(), task.getStatus());
            Assertions.assertThrows(IllegalTaskStateChangeException.class, task::startProgress, message);
        } else {
            Assertions.assertDoesNotThrow(task::startProgress);
        }
    }

    @ParameterizedTest
    @MethodSource("provideTasksToTestProgress")
    @DisplayName("Test that task's lastUpdater field is not changed if moved to 'IN PROGRESS' state exceptionally")
    public void testTaskLastUpdaterFieldIsNotChangedAfterStartingProgressInInvalidState(Task task, boolean expectedToThrow) throws IllegalTaskStateChangeException {
        User lastUpdater = task.getLastUpdater();
        try {
            task.startProgress();
        } catch (IllegalTaskStateChangeException e) {

        }
        Assertions.assertSame(lastUpdater, task.getLastUpdater());
    }

    @ParameterizedTest
    @MethodSource("provideTaskToTestUpdates")
    @DisplayName("Test last updater is changed after 'update'")
    public void testLastUpdaterChangedAfterUpdate(Task task) {
        task.updateTask(dumbUser);
        Assertions.assertSame(dumbUser, task.getLastUpdater());
    }

    @ParameterizedTest
    @MethodSource("provideTaskToTestUpdates")
    @DisplayName("Test last updated is changed after 'update'")
    public void testLastUpdatedChangedAfterUpdate(Task task) throws InterruptedException {
        Instant oldLastUpdated = task.getLastUpdated();
        Thread.sleep(500);
        task.updateTask(dumbUser);
        Assertions.assertNotEquals(oldLastUpdated.toEpochMilli(), task.getLastUpdated().toEpochMilli());
    }

    private static Stream<Arguments> provideNewTasks() {
        return Stream.of(
                Arguments.of(getDumbTaskOfTypeAndStatus(IncubationTask.class, TaskState.OPEN), true),
                Arguments.of(getDumbTaskOfTypeAndStatus(IncubationTask.class, TaskState.CLOSED), false),
                Arguments.of(getDumbTaskOfTypeAndStatus(ResearchTask.class, TaskState.OPEN), true)
        );
    }

    private static Stream<Arguments> provideTasksToTestProgress() {
        boolean shouldThrowException = true;
        boolean shouldntThrowException = false;
        List<Class<? extends Task>> classes = getTaskClasses();
        Stream<Arguments> openedTasks = classes.stream().map(clazz -> Arguments.of(getDumbTaskOfTypeAndStatus(clazz, TaskState.OPEN), shouldntThrowException));
        Stream<Arguments> inProgressTasks = classes.stream().map(clazz -> Arguments.of(getDumbTaskOfTypeAndStatus(clazz, TaskState.IN_PROGRESS), shouldThrowException));
        Stream<Arguments> resolvedTasks = classes.stream().map(clazz -> Arguments.of(getDumbTaskOfTypeAndStatus(clazz, TaskState.RESOLVED), shouldThrowException));
        Stream<Arguments> closedTasks = classes.stream().map(clazz -> Arguments.of(getDumbTaskOfTypeAndStatus(clazz, TaskState.CLOSED), shouldThrowException));
        return Stream.concat(
                Stream.concat(openedTasks, inProgressTasks),
                Stream.concat(resolvedTasks, closedTasks)
        );
    }

    private static Stream<Arguments> provideTaskToTestUpdates() {
        IncubationTask task = new IncubationTask();
        task.setLastUpdater(new User());
        task.setLastUpdated(Instant.now());
        return Stream.of(Arguments.of(task));
    }

    private static List<Class<? extends Task>> getTaskClasses() {
        List<Class<? extends Task>> classes = new ArrayList<>();
        classes.add(IncubationTask.class);
        classes.add(ResearchTask.class);
        classes.add(CreateAviaryTask.class);
        return classes;
    }

    private static <T extends Task> T getDumbTaskOfTypeAndStatus(Class<T> tClass, TaskState state) {
        try {
            Constructor<T> emptyConstructor = tClass.getConstructor();
            T instance = emptyConstructor.newInstance();
            instance.setStatus(state);
            instance.setLastUpdated(Instant.now());
            return instance;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new IllegalAccessError("Error creating task instance via reflection");
        }

    }

}
