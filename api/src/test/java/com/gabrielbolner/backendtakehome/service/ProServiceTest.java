package com.gabrielbolner.backendtakehome.service;

import com.gabrielbolner.backendtakehome.controller.request.ProRequest;
import com.gabrielbolner.backendtakehome.controller.response.ProResponse;
import com.gabrielbolner.backendtakehome.factories.ProFactory;
import com.gabrielbolner.backendtakehome.validator.ProValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.gabrielbolner.backendtakehome.service.ProService.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class ProServiceTest {

    @InjectMocks
    private ProService proService;

    @Mock
    private ProValidator proValidator;

    @Test
    void shouldApplyProServiceWithValidData() {
        ProRequest validProRequest = ProFactory.getProRequest();

        ProResponse proResponse = proService.applyAsPro(validProRequest);

        assertNotNull(proResponse);
        assertNotNull(proResponse.getScore());
        assertNotNull(proResponse.getEligible_projects());
        assertNotNull(proResponse.getIneligible_projects());
        assertNotNull(proResponse.getSelected_project());
    }


    @Test
    void shouldThrowExceptionWhenProAgeIsUnderLimit() {
        ProRequest proRequest = ProFactory.getProRequest();
        proRequest.setAge(17);

        doThrow(new IllegalArgumentException(AGE_LIMIT_ERROR)).when(proValidator).validate(proRequest);

        assertThrows(IllegalArgumentException.class, () -> proService.applyAsPro(proRequest));
    }

    @Test
    void shouldThrowExceptionWhenProHasInvalidEducationLevel() {
        ProRequest proRequest = ProFactory.getProRequest();
        proRequest.setAge(25);
        proRequest.setEducation_level("invalid_education");

        doThrow(new IllegalArgumentException(EDUCATION_LEVEL_ERROR)).when(proValidator).validate(proRequest);

        assertThrows(IllegalArgumentException.class, () -> proService.applyAsPro(proRequest));
    }


    @Test
    void shouldCalculateEligibilityScoreForPro() {
        ProRequest proRequest = ProFactory.getProRequest();
        proRequest.setAge(25);
        proRequest.setEducation_level(BACHELORS_OR_HIGH);

        Float score = proService.calculateEligibilityScore(proRequest);
        float expectedScore = 8;

        assertEquals(expectedScore, score);
    }


    @Test
    void shouldDeterminePairedProjectBasedOnScore() {
        float score = 12;

        String pairedProject = proService.determinePairedProject(score);

        String expectedProject = PROJECTS.get(0).getName();

        assertEquals(expectedProject, pairedProject);
    }

    @Test
    void shouldFindEligibleProjectsBasedOnScore() {
        float score = 8;

        List<String> eligibleProjects = proService.findEligibleProjects(score);

        assertTrue(eligibleProjects.contains(PROJECTS.get(1).getName()));
        assertTrue(eligibleProjects.contains(PROJECTS.get(2).getName()));
        assertTrue(eligibleProjects.contains(PROJECTS.get(3).getName()));
    }

    @Test
    void shouldFindIneligibleProjectsBasedOnScore() {
        float score = 4;

        List<String> ineligibleProjects = proService.findIneligibleProjects(score);

        assertTrue(ineligibleProjects.contains(PROJECTS.get(0).getName()));
        assertTrue(ineligibleProjects.contains(PROJECTS.get(1).getName()));
    }
}
