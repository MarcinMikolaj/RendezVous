package project.rendezvous.panel.localization;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeoLocalizationRepository extends MongoRepository<GeoLocalization, String> {

    GeoLocalization findByEmail(String email);

}
