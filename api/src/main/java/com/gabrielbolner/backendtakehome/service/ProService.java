package com.gabrielbolner.backendtakehome.service;

import com.gabrielbolner.backendtakehome.controller.request.ProRequest;
import com.gabrielbolner.backendtakehome.controller.response.ProResponse;
import com.gabrielbolner.backendtakehome.domain.Project;
import com.gabrielbolner.backendtakehome.validator.ProValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProService {

    private static final List<Project> PROJECTS = List.of(
            new Project("Calculate the Dark Matter of the universe for Nasa", Float.valueOf("10")),
            new Project("Determine if the Schrodinger's cat is alive", Float.valueOf("5")),
            new Project("Attend to users support for a YXZ Company", Float.valueOf("3")),
            new Project("Collect specific people information from their social media for XPTO Company", Float.valueOf("2"))
    );
    @Autowired
    private ProValidator proValidator;

    public ProResponse applyAsPro(ProRequest proRequest) {
        try {
            proValidator.validate(proRequest);

            Float score = calculateEligibilityScore(proRequest);
            String pairedProject = determinePairedProject(score);
            List<String> eligibleProjects = findEligibleProjects(score);
            List<String> ineligibleProjects = findIneligibleProjects(score);

            ProResponse proResponse = new ProResponse();
            proResponse.setScore(score);
            proResponse.setSelected_project(pairedProject);
            proResponse.setEligible_projects(eligibleProjects);
            proResponse.setIneligible_projects(ineligibleProjects);

            return proResponse;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred: " + e.getMessage());
        }
    }

    private String formatErrors(BindingResult bindingResult) {
        List<String> errorMessages = bindingResult.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return String.join(", ", errorMessages);
    }

    private Float calculateEligibilityScore(ProRequest proRequest) {

        float score = Float.valueOf("0");

        if (proRequest.getAge() < 18) {
            throw new IllegalArgumentException("Pro is under the age limit");
        }

        score += "high_school".equals(proRequest.getEducation_level()) ? 1 : ("bachelors_degree_or_high".equals(proRequest.getEducation_level()) ? 2 : 0);

        score += proRequest.getPast_experiences().isSales() ? 5 : 0;
        score += proRequest.getPast_experiences().isSupport() ? 3 : 0;

        score += mapSpeedToScore(proRequest.getInternet_test().getDownloadSpeed() + mapSpeedToScore(proRequest.getInternet_test().getUploadSpeed()));

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

    private String determinePairedProject(Float score) {
        if (score > 10) {
            return PROJECTS.get(0).getName();
        } else if (score > 5) {
            return PROJECTS.get(1).getName();
        } else if (score > 3) {
            return PROJECTS.get(2).getName();
        } else if (score > 2) {
            return PROJECTS.get(3).getName();
        } else {
            throw new IllegalArgumentException("No eligible projects");
        }
    }

    private List<String> findEligibleProjects(Float score) {
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

    private List<String> findIneligibleProjects(Float score) {
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
