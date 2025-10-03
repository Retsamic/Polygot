package org.example.Repository;
import org.example.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tags", collectionResourceRel = "tags")
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag getTagByName(String name);
    boolean existsByName(String name);

}