package com.project.webapi.service;

import com.project.webapi.core.service.BaseService;
import com.project.webapi.data.dao.Language;
import com.project.webapi.repository.LanguageRepository;
import org.springframework.stereotype.Service;

@Service
public class LanguageService extends BaseService<Language, LanguageRepository> {

    public LanguageService(LanguageRepository repository) {
        super(repository);
    }

}
