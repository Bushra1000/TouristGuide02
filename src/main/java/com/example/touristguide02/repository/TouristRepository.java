package com.example.touristguide02.repository;

import com.example.touristguide02.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.Arrays;
import java.util.List;

@Repository

public class TouristRepository {

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TouristAttraction> rowMapper = (rs, rowNum) ->
            new TouristAttraction(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("tags") == null
                            || rs.getString("tags").isBlank()
                            ? List.of()
                            : Arrays.asList(
                            rs.getString("tags").split(",")
                    ),
                    rs.getString("city")
            );

    public List<TouristAttraction> getAllattractions() {
        String sql = "SELECT * FROM tourist_attractions";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public TouristAttraction getAttractionsByname(String name) {
        String sql = """
                SELECT * FROM tourist_attractions
                WHERE LOWER(name)= LOWER(?)
                """;

        List<TouristAttraction> attractions =
                jdbcTemplate.query(sql, rowMapper, name);

        return attractions.isEmpty() ? null : attractions.get(0);
    }

    public TouristAttraction addAttraction(
            TouristAttraction attraction) {

        String sql = """
                INSERT INTO tourist_attractions
                (name, description,tags, city)
                VALUES (?,?,?,?)
                """;


        jdbcTemplate.update(
                sql,
                attraction.getName(),
                attraction.getDescription(),
                String.join(",", attraction.getTags()),
                attraction.getCity()
        );

        return attraction;
    }

    public void updateAttraction(
            TouristAttraction updatedAttraction) {

        String sql = """
                UPDATE tourist_attractions
                SET description= ?,city= ?,tags= ?
                WHERE LOWER(name)= LOWER(?)
                """;

        jdbcTemplate.update(
                sql,
                updatedAttraction.getDescription(),
                updatedAttraction.getCity(),
                String.join(",", updatedAttraction.getTags()),
                updatedAttraction.getName()
        );
    }

    public void deleteAttraction(String name) {
        String sql = """
                DELETE FROM tourist_attractions
                WHERE LOWER(name) = LOWER(?)
                """;

        jdbcTemplate.update(sql, name);
    }

    public List<String> getCities() {
        String sql = """
                SELECT DISTINCT city
                FROM tourist_attractions
                ORDER BY city
                """;

        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<String> getTags() {
        String sql = """
                SELECT DISTINCT TRIM(j.tag) AS tag
                FROM tourist_attractions t
                CROSS JOIN JSON_TABLE(
                    CONCAT(
                        '["',
                        REPLACE(
                            REPLACE(t.tags, '"', ''),
                            ',', '","'
                        ),
                        '"]'
                    ),
                    '$[*]' COLUMNS (
                        tag VARCHAR(255) PATH '$'
                    )
                ) AS j
                WHERE t.tags IS NOT NULL AND t.tags <> ''
                ORDER BY tag
                """;

        return jdbcTemplate.queryForList(sql, String.class);
    }
}















