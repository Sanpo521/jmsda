package io.renren.modules.archives.dao;

import io.renren.modules.archives.entity.DocsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocsDao extends MongoRepository<DocsEntity, String> {
}
