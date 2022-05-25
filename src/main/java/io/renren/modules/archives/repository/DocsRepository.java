package io.renren.modules.archives.repository;

import io.renren.modules.archives.entity.DocsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocsRepository extends MongoRepository<DocsEntity, String> {
}
