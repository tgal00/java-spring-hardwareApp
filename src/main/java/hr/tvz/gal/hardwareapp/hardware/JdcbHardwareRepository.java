package hr.tvz.gal.hardwareapp.hardware;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdcbHardwareRepository implements HardwareRepository {


    private static String SELECT_ALL =
            "select id,code,name, price,stock,type from hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdcbHardwareRepository(JdbcTemplate template){
        this.jdbc = template;
        this.inserter = new SimpleJdbcInsert(template)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Hardware> findAll() {
        return List.copyOf(jdbc.query(SELECT_ALL,this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code=?", this::mapRowToHardware, code));
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try{
            hardware.setId(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        }catch(DuplicateKeyException e){
            return Optional.empty();
        }
    }

    private long saveHardwareDetails(Hardware hardware){
        Map<String,Object> values = new HashMap<>();
        values.put("code",hardware.getCode());
        values.put("name",hardware.getName());
        values.put("price",hardware.getPrice());
        values.put("type",hardware.getType());
        values.put("stock",hardware.getStock());

        return inserter.executeAndReturnKey(values).longValue();
    }

    @Override
    public Optional<Hardware> update(String code, Hardware hardware) {
        int executed = jdbc.update("UPDATE hardware SET "+
                "name=?,"+
                "price=?,"+
                "stock=?,"+
                "type=?"+
                "WHERE code = ?",
                hardware.getName(),
                hardware.getPrice(),
                hardware.getStock(),
                hardware.getType().name(),
                hardware.getCode()
                );

        if(executed>0){
            return Optional.of(hardware);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {
        jdbc.update("DELETE FROM hardware WHERE code = ?",code);
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("code"),
                rs.getDouble("price"),
                Hardware.Type.valueOf(rs.getString("type")),
                rs.getInt("stock")
                );
    }
}
