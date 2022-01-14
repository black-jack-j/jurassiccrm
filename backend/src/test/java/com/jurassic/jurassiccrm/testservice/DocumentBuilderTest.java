package com.jurassic.jurassiccrm.testservice;

import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.document.controller.DocumentBuilder;
import com.jurassic.jurassiccrm.document.controller.DocumentBuilderException;
import com.jurassic.jurassiccrm.document.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class DocumentBuilderTest {

    @Test
    void throwsExceptionIfJsonIsAnEmptyString() {
        Arrays.stream(DocumentType.values()).forEach(type ->
                Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(type, "")));
    }

    @Test
    void throwsExceptionIfJsonIsAnEmptyObject() {
        Arrays.stream(DocumentType.values()).forEach(type ->
                Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(type, "{}")));
    }

    @Test
    void parseTechnologicalMapFromValidJson() throws DocumentBuilderException {
        TechnologicalMap technologicalMap = (TechnologicalMap) DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}");

        Assertions.assertEquals("some map", technologicalMap.getName());
        Assertions.assertEquals("some description", technologicalMap.getDescription());
        Assertions.assertEquals(1L, technologicalMap.getDinosaurType().getId());
        Assertions.assertEquals(Arrays.asList("inc step1", "inc step2"), technologicalMap.getIncubationSteps());
        Assertions.assertEquals(Arrays.asList("egg step1", "egg step2"), technologicalMap.getEggCreationSteps());
    }

    @Test
    void allowToSkipDescription() throws DocumentBuilderException {
        TechnologicalMap technologicalMap = (TechnologicalMap) DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}");
        Assertions.assertNull(technologicalMap.getDescription());
    }

    @Test
    void allowOnlyOneElementInStepsLists() throws DocumentBuilderException {
        TechnologicalMap technologicalMap = (TechnologicalMap) DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\"]," +
                        "\"eggCreationSteps\": [\"egg step1\"]}");
        Assertions.assertEquals(Collections.singletonList("inc step1"), technologicalMap.getIncubationSteps());
        Assertions.assertEquals(Collections.singletonList("egg step1"), technologicalMap.getEggCreationSteps());
    }

    @Test
    void ignoreUnexpectedFields() throws DocumentBuilderException {
        TechnologicalMap technologicalMap = (TechnologicalMap) DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"someTrash\": \"trashy trash\"," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}");
        Assertions.assertFalse(technologicalMap.toString().contains("trash"));
    }

    @Test
    void forbidToSkipDocumentName() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void forbidDocumentNameShorterThan5Symbols() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void allowDocumentNameWith5Symbols() {
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"five5\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void forbidDocumentNameLongerThan255Symbols() {
        String characters256 = "somesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"" + characters256 + "\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void allowDocumentNameWith255Symbols() {
        String characters255 = "somsdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"" + characters255 + "\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void forbidDescriptionLongerThan1000Symbols() {
        String characters1001 = "somesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngfsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngfsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngfsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdf";
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"document name\"," +
                        "\"description\": \"" + characters1001 + "\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void allowDescriptionWith1000Symbols() {
        String characters1000 = "smesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngfsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngfsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngfsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdf";
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"document name\"," +
                        "\"description\": \"" + characters1000 + "\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void forbidToSkipDinosaurTypeId() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some\"," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void forbidToSkipIncubationSteps() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void forbidToSkipEggCreationSteps() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]}"));
    }

    @Test
    void forbidEmptyIncubationSteps() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": []," +
                        "\"eggCreationSteps\": [\"egg step1\", \"egg step2\"]}"));
    }

    @Test
    void forbidEmptyEggCreationSteps() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.TECHNOLOGICAL_MAP,
                "{\"name\": \"some\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"incubationSteps\": [\"inc step1\", \"inc step2\"]," +
                        "\"eggCreationSteps\": []}"));
    }

    @Test
    void parseThemeZoneProjectFromValidJson() throws DocumentBuilderException {
        ThemeZoneProject project = (ThemeZoneProject) DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"some project name\"," +
                        "\"dinosaurs\": {\"1\":2, \"3\":4}," +
                        "\"aviaries\": {\"2\":1, \"4\":3}," +
                        "\"decorations\": {\"5\":6, \"7\":8}}");

        Map<DinosaurType, Integer> expectedDinosaurs = new HashMap<>();
        expectedDinosaurs.put(new DinosaurType(1L), 2);
        expectedDinosaurs.put(new DinosaurType(3L), 4);

        Map<AviaryType, Integer> expectedAviaries = new HashMap<>();
        expectedAviaries.put(new AviaryType(2L), 1);
        expectedAviaries.put(new AviaryType(4L), 3);

        Map<DecorationType, Integer> expectedDecorations = new HashMap<>();
        expectedDecorations.put(new DecorationType(5L), 6);
        expectedDecorations.put(new DecorationType(7L), 8);

        Assertions.assertEquals("some project name", project.getProjectName());
        Assertions.assertEquals(1L, project.getManager().getId());
        Assertions.assertEquals(expectedDinosaurs, project.getDinosaurs());
        Assertions.assertEquals(expectedAviaries, project.getAviaries());
        Assertions.assertEquals(expectedDecorations, project.getDecorations());
    }

    @Test
    void allowOnlyOneElementInMaps() throws DocumentBuilderException {
        ThemeZoneProject project = (ThemeZoneProject) DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"some project name\"," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {\"2\":1}," +
                        "\"decorations\": {\"5\":6}}");

        Map<DinosaurType, Integer> expectedDinosaurs = new HashMap<>();
        expectedDinosaurs.put(new DinosaurType(1L), 2);

        Map<AviaryType, Integer> expectedAviaries = new HashMap<>();
        expectedAviaries.put(new AviaryType(2L), 1);

        Map<DecorationType, Integer> expectedDecorations = new HashMap<>();
        expectedDecorations.put(new DecorationType(5L), 6);

        Assertions.assertEquals(expectedDinosaurs, project.getDinosaurs());
        Assertions.assertEquals(expectedAviaries, project.getAviaries());
        Assertions.assertEquals(expectedDecorations, project.getDecorations());
    }

    @Test
    void allowEmptyDecorationsMap() throws DocumentBuilderException {
        ThemeZoneProject project = (ThemeZoneProject) DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"some project name\"," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {\"2\":1}," +
                        "\"decorations\": {}}");

        Assertions.assertEquals(new HashMap<>(), project.getDecorations());
    }

    @Test
    void allowNoDecorationsMap() throws DocumentBuilderException {
        ThemeZoneProject project = (ThemeZoneProject) DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"some project name\"," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {\"2\":1}}");

        Assertions.assertEquals(new HashMap<>(), project.getDecorations());
    }

    @Test
    void forbidProjectNameShorterThan3Symbols() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"pr\"," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {\"2\":1}}"));
    }

    @Test
    void allowProjectNameWith3Symbols() {
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"prj\"," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {\"2\":1}}"));
    }

    @Test
    void forbidProjectNameLongerThan255Symbols() {
        String characters256 = "somesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"" + characters256 + "\"," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {\"2\":1}}"));
    }

    @Test
    void allowProjectNameWith255Symbols() {
        String characters255 = "omesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"" + characters255 + "\"," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {\"2\":1}}"));
    }

    @Test
    void forbidMissingProjectName() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {\"2\":1}}"));
    }

    @Test
    void forbidMissingManagerId() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"projectName\": \"some project\"," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {\"2\":1}}"));
    }

    @Test
    void forbidMissingDinosaurs() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"some project\"," +
                        "\"aviaries\": {\"2\":1}}"));
    }

    @Test
    void forbidMissingAviaries() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"some project\"," +
                        "\"dinosaurs\": {\"1\":2}}"));
    }

    @Test
    void forbidEmptyDinosaurs() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"some project\"," +
                        "\"dinosaurs\": {}," +
                        "\"aviaries\": {\"2\":1}}"));
    }

    @Test
    void forbidEmptyAviaries() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.THEME_ZONE_PROJECT,
                "{\"name\": \"some project\"," +
                        "\"managerId\": 1," +
                        "\"projectName\": \"some project\"," +
                        "\"dinosaurs\": {\"1\":2}," +
                        "\"aviaries\": {}}"));
    }

    @Test
    void parseDinosaurPassport() throws DocumentBuilderException {
        DinosaurPassport dinosaurPassport = (DinosaurPassport) DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}");
        Assertions.assertEquals("some map", dinosaurPassport.getName());
        Assertions.assertEquals("some description", dinosaurPassport.getDescription());
        Assertions.assertEquals("some dinosaur", dinosaurPassport.getDinosaurName());
        Assertions.assertEquals(1L, dinosaurPassport.getDinosaurType().getId());
        Assertions.assertEquals(10.5, dinosaurPassport.getWeight());
        Assertions.assertEquals(20.25, dinosaurPassport.getHeight());
        Assertions.assertEquals(LocalDate.parse("2021-01-01"), dinosaurPassport.getIncubated());
        Assertions.assertEquals(10, dinosaurPassport.getRevisionPeriod());
        Assertions.assertEquals("some status", dinosaurPassport.getStatus());
    }

    @Test
    void forbidDinosaurNameShorterThan3Symbols() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"di\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void allowDinosaurNameWith3Symbols() {
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"din\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidDinosaurNameLongerThan255Symbols() {
        String characters256 = "somesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"" + characters256 + "\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void allowDinosaurNameWith255Symbols() {
        String characters255 = "omesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"" + characters255 + "\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidMissingDinosaurName() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidMissingDinosaurTypeId() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidZeroWeight() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"weight\": 0," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidNegativeWeight() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"weight\": -1," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidMissingWeight() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidZeroHeight() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"weight\": 20.25," +
                        "\"height\": 0," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidNegativeHeight() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"weight\": 20.25," +
                        "\"height\": -1," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidMissingHeight() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"weight\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidFutureDateInIncubated() {
        LocalDate future = LocalDate.now().plusDays(1);
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 10.5," +
                        "\"weight\": 20.25," +
                        "\"incubated\": \"" + future + "\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void allowTodayDateInIncubated() {
        LocalDate today = LocalDate.now(ZoneId.of("Z"));
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 10.5," +
                        "\"weight\": 20.25," +
                        "\"incubated\": \"" + today + "\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidIllegalDateFormatInIncubated() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 10.5," +
                        "\"weight\": 20.25," +
                        "\"incubated\": \"2021:01:01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidMissingIncubated() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 10.5," +
                        "\"weight\": 20.25," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidNegativeDinosaurRevisionPeriod() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 10.5," +
                        "\"weight\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": -10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidZeroDinosaurRevisionPeriod() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 10.5," +
                        "\"weight\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 0," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidDinosaurRevisionPeriodMoreThan180Days() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 10.5," +
                        "\"weight\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 181," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void allowDinosaurRevisionPeriodEquals180Days() {
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 10.5," +
                        "\"weight\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 180," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidMissingDinosaurRevisionPeriod() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"height\": 10.5," +
                        "\"weight\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidDinosaurStatusShorterThan3Symbols() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"st\"}"));
    }

    @Test
    void allowDinosaurStatusWith3Symbols() {
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"sta\"}"));
    }

    @Test
    void forbidDinosaurStatusLongerThan255Symbols() {
        String characters256 = "somesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"" + characters256 + "\"}"));
    }

    @Test
    void allowDinosaurStatusWith255Symbols() {
        String characters255 = "omesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"" + characters255 + "\"}"));
    }

    @Test
    void forbidMissingDinosaurStatus() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.DINOSAUR_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"dinosaurName\": \"some dinosaur\"," +
                        "\"dinosaurTypeId\": 1," +
                        "\"weight\": 10.5," +
                        "\"height\": 20.25," +
                        "\"incubated\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10}"));
    }

    @Test
    void parseAviaryPassport() throws DocumentBuilderException {
        AviaryPassport aviaryPassport = (AviaryPassport) DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}");
        Assertions.assertEquals("some map", aviaryPassport.getName());
        Assertions.assertEquals("some description", aviaryPassport.getDescription());
        Assertions.assertEquals(1L, aviaryPassport.getAviaryType().getId());
        Assertions.assertEquals(111L, aviaryPassport.getCode());
        Assertions.assertEquals(LocalDate.parse("2021-01-01"), aviaryPassport.getBuiltDate());
        Assertions.assertEquals(10, aviaryPassport.getRevisionPeriod());
        Assertions.assertEquals("some status", aviaryPassport.getStatus());
    }

    @Test
    void forbidMissingCode() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidMissingAviaryType() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"code\": 111," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }


    @Test
    void forbidBuiltDateInTheFuture() {
        LocalDate future = LocalDate.now().plusDays(1);
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"" + future + "\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void allowBuiltDateBeToday() {
        LocalDate today = LocalDate.now(ZoneId.of("Z"));
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"" + today + "\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidIllegalDateFormatInBuiltDate() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021:01:01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidMissingBuiltDate() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidNegativeAviaryRevisionPeriod() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": -10," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidZeroAviaryRevisionPeriod() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 0," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidRevisionAviaryPeriodMoreThan366Days() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 367," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void allowAviaryRevisionPeriodEquals366Days() {
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 366," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidMissingAviaryRevisionPeriod() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"status\": \"some status\"}"));
    }

    @Test
    void forbidAviaryStatusShorterThan3Symbols() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"st\"}"));
    }

    @Test
    void allowAviaryStatusWith3Symbols() {
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"sta\"}"));
    }

    @Test
    void forbidAviaryStatusLongerThan255Symbols() {
        String characters256 = "somesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"" + characters256 + "\"}"));
    }

    @Test
    void allowAviaryStatusWith255Symbols() {
        String characters255 = "omesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;dsomesdfsdfsdsgl;kgs;nlq;jsjlg;lsdklsdfhg;klakljhd;fgjs;lkhakl;fdjhgl;ksdfgmkls;ddfrtghyujkiolngf";
        Assertions.assertDoesNotThrow(() -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10," +
                        "\"status\": \"" + characters255 + "\"}"));
    }

    @Test
    void forbidMissingAviaryStatus() {
        Assertions.assertThrows(DocumentBuilderException.class, () -> DocumentBuilder.build(DocumentType.AVIARY_PASSPORT,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"code\": 111," +
                        "\"aviaryTypeId\": 1," +
                        "\"builtDate\": \"2021-01-01\"," +
                        "\"revisionPeriod\": 10}"));
    }

    @Test
    void parseResearchData() throws DocumentBuilderException {
        ResearchData researchData = (ResearchData) DocumentBuilder.build(DocumentType.RESEARCH_DATA,
                "{\"name\": \"some map\"," +
                        "\"description\": \"some description\"," +
                        "\"researchId\": 1," +
                        "\"attachmentName\": \"some file\"," +
                        "\"attachmentBase64Encoded\": \"kekLOL\"}");
        Assertions.assertEquals("some map", researchData.getName());
        Assertions.assertEquals("some description", researchData.getDescription());
        Assertions.assertEquals(1L, researchData.getResearch().getId());
        Assertions.assertEquals("some file", researchData.getAttachmentName());
        Assertions.assertArrayEquals(Base64.getDecoder().decode("kekLOL"), researchData.getAttachment());
    }
}
