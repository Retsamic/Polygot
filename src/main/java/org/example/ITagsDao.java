package org.example;

public interface ITagsDao {
    void createTag(Tags tag);
    Tags findByName(String name);
    Tags findById(int id);
    void removeTag(int id);
    void updateTag(Tags tag);
}