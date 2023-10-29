package com.gabrielbolner.backendtakehome.factories;

import com.gabrielbolner.backendtakehome.controller.request.ProRequest;
import com.gabrielbolner.backendtakehome.domain.InternetTest;
import com.gabrielbolner.backendtakehome.domain.PastExperiences;
import com.gabrielbolner.backendtakehome.domain.Pro;

public class ProFactory {

    public static Pro get() {
        Pro pro = new Pro();
        pro.setAge(35);
        pro.setEducation_level("high_school");

        PastExperiences pastExperiences = new PastExperiences();
        pastExperiences.setSales(false);
        pastExperiences.setSupport(true);
        pro.setPast_experiences(pastExperiences);

        InternetTest internetTest = new InternetTest();
        internetTest.setDownload_speed(50.4f);
        internetTest.setUpload_speed(40.2f);
        pro.setInternet_test(internetTest);

        pro.setWriting_score(0.6f);
        pro.setReferral_code("token1234");

        return pro;
    }

    public static ProRequest getProRequest() {
        Pro pro = get();
        ProRequest proRequest = new ProRequest();
        proRequest.setAge(pro.getAge());
        proRequest.setEducation_level(pro.getEducation_level());
        proRequest.setPast_experiences(pro.getPast_experiences());
        proRequest.setInternet_test(pro.getInternet_test());
        proRequest.setWriting_score(pro.getWriting_score());
        proRequest.setReferral_code(pro.getReferral_code());
        return proRequest;
    }


}
