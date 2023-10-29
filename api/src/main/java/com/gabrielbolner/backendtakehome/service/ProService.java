package com.gabrielbolner.backendtakehome.service;

import com.gabrielbolner.backendtakehome.controller.request.ProRequest;
import com.gabrielbolner.backendtakehome.controller.response.ProResponse;
import com.gabrielbolner.backendtakehome.domain.Project;
import com.gabrielbolner.backendtakehome.validator.ProValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProService {

    public static final List<Project> PROJECTS = List.of(
            new Project("Calculate the Dark Matter of the universe for Nasa", Float.valueOf("10")),
            new Project("Determine if the Schrodinger's cat is alive", Float.valueOf("5")),
            new Project("Attend to users support for a YXZ Company", Float.valueOf("3")),
            new Project("Collect specific people information from their social media for XPTO Company", Float.valueOf("2"))
    );

    public static final String AGE_LIMIT_ERROR = "Pro is under the age limit";
    public static final String EDUCATION_LEVEL_ERROR = "Invalid education level";
    public static final String NO_ELIGIBLE_PROJECTS_ERROR = "No eligible projects";
    public static final String HIGH_SCHOOL = "high_school";
    public static final String BACHELORS_OR_HIGH = "bachelors_degree_or_high";

    @Autowired
    private ProValidator proValidator;

    public ProResponse applyAsPro(ProRequest proRequest) {
        try {
            proValidator.validate(proRequest);
            Float score = calculateEligibilityScore(proRequest);
            String pairedProject = determinePairedProject(score);
            List<String> eligibleProjects = findEligibleProjects(score);
            List<String> ineligibleProjects = findIneligibleProjects(score);

            return ProResponse.builder()
                    .score(score)
                    .selected_project(pairedProject)
                    .eligible_projects(eligibleProjects)
                    .ineligible_projects(ineligibleProjects)
                    .build();

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred: " + e.getMessage());
        }
    }

    protected Float calculateEligibilityScore(ProRequest proRequest) {
        float score = Float.parseFloat("0");

        if (proRequest.getAge() < 18) {
            throw new IllegalArgumentException(AGE_LIMIT_ERROR);
        }

        score += HIGH_SCHOOL.equals(proRequest.getEducation_level()) ? 1 : (BACHELORS_OR_HIGH.equals(proRequest.getEducation_level()) ? 2 : 0);
        score += proRequest.getPast_experiences().isSales() ? 5 : 0;
        score += proRequest.getPast_experiences().isSupport() ? 3 : 0;
        score += mapSpeedToScore(proRequest.getInternet_test().getDownload_speed() + mapSpeedToScore(proRequest.getInternet_test().getUpload_speed()));
        score += mapWritingScoreToScore(proRequest.getWriting_score());
        score += "token1234".equals(proRequest.getReferral_code()) ? 1 : 0;

        return score;
    }

    private int mapSpeedToScore(Float speed) {
        return (speed > 50.0) ? 1 : ((speed < 5.0) ? -1 : 0);
    }

    private int mapWritingScoreToScore(Float writingScore) {
        return (writingScore < 0.3) ? -1 : ((writingScore <= 0.7) ? 1 : 2);
    }

    public String determinePairedProject(Float score) {
        if (score > 10) {
            return PROJECTS.get(0).getName();
        } else if (score > 5) {
            return PROJECTS.get(1).getName();
        } else if (score > 3) {
            return PROJECTS.get(2).getName();
        } else if (score > 2) {
            return PROJECTS.get(3).getName();
        } else {
            throw new IllegalArgumentException(NO_ELIGIBLE_PROJECTS_ERROR);
        }
    }

    public List<String> findEligibleProjects(Float score) {
        List<String> eligibleProjects = new ArrayList<>();

        if (score > 10) {
            eligibleProjects.add(PROJECTS.get(0).getName());
        }
        if (score >= 5) {
            eligibleProjects.add(PROJECTS.get(1).getName());
        }
        if (score >= 3) {
            eligibleProjects.add(PROJECTS.get(2).getName());
        }
        if (score >= 2) {
            eligibleProjects.add(PROJECTS.get(3).getName());
        }

        return eligibleProjects;
    }

    public List<String> findIneligibleProjects(Float score) {
        List<String> ineligibleProjects = new ArrayList<>();

        for (Project project : PROJECTS) {
            Float projectScore = project.getScore();
            if (projectScore > score) {
                ineligibleProjects.add(project.getName());
            }
        }

        return ineligibleProjects;
    }
}
