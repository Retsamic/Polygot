package org.example;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/snippets")
public class SnippetServiceImpl implements ISnippetService {
    private final ISnippetDao snippetDao;
    private final ISnippetTagDao snippetTagDao;
    private final ITagsDao tagsDao;

    public SnippetServiceImpl(ISnippetDao snippetDao, SnippetTagDAO snippetTagDAO, TagsDAO tagsDAO) {
        this.snippetDao = snippetDao;
        this.snippetTagDao = snippetTagDAO;
        this.tagsDao = tagsDAO;
    }

    @Override
    public Snippet createSnippet(Snippet snippet) {
        if(snippetDao.findByName(snippet.getTitle()) != null){
            throw new IllegalArgumentException("Title already exists");
        }
       snippetDao.createSnippet(snippet);
       return snippet;
    }

    @Override
    public Snippet updateSnippet(Snippet snippet) {
        snippetDao.updateSnippet(snippet);
        return snippet;
    }

    @Override
    public void deleteSnippet(Snippet snippet) {
        if(snippetDao.findByName(snippet.getTitle()) == null){
            throw new IllegalArgumentException("Title does not exist");
        }
        snippetDao.delete(snippet.getId());
    }

    @Override
    public Snippet findById(int id) {
        return null;
    }

    @Override
    public List<Snippet> findAll() {
        return snippetDao.findAll();
    }

    @Override
    public List<Snippet> findByName(String name) {
        return List.of();
    }
}
