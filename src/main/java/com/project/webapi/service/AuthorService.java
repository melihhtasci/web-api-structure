package com.project.webapi.service;

import com.project.webapi.core.service.BaseService;
import com.project.webapi.data.dao.Author;
import com.project.webapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService extends BaseService<Author, AuthorRepository> {
    public AuthorService(AuthorRepository repository) {
        super(repository);
    }

    public Author getAuthorByName(String name) throws Exception {
        Optional<Author> author = repository.findByName(name).stream().findFirst();
        if (!author.isPresent()) {
            return add(new Author(name));
        }
        return author.get();
    }
}
