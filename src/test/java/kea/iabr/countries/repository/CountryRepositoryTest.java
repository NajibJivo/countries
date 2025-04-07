package kea.iabr.countries.repository;

import kea.iabr.countries.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CountryRepositoryTest {
    @Autowired
    private CountryRepository countryRepository;

    @Sql(
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = {"classpath:schema.sql"}
    )

    @Test
    void findAll_returnAllCountries() {
        List<Country> countries = countryRepository.findAll();
        assertThat(countries).hasSize(51);
    }

    @Test
    void findByName_returnsCorrectCountry(){
        Country country = countryRepository.findByName("Denmark");
        assertThat(country).isNotNull();
        assertThat(country).isEqualTo(13);
        assertThat(country.getName()).isEqualTo("Denmark");

    }

    @Test
    void findByName_returnsNullIfNotFound() {
        Country country = null;
        try {
            country = countryRepository.findByName("Neverland");
        } catch (Exception ignored) {
            // queryForObject kaster exception hvis intet findes
        }
        assertThat(country).isNull();
    }
}
