package com.tick.settingsapi.repositories;

import com.tick.settingsapi.models.SettingsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends MongoRepository<SettingsModel, String> {
}