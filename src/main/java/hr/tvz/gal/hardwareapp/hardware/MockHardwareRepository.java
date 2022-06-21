package hr.tvz.gal.hardwareapp.hardware;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class MockHardwareRepository implements HardwareRepository{


    /*private List<Hardware> MOCKED_HARDWARE = Arrays.asList(
            new Hardware("RTX 3060","111",333.70, Hardware.Type.GPU, 10),
            new Hardware("Ryzen 7 5600X","222",420, Hardware.Type.CPU,54),
            new Hardware("Samsung EVO 970","333",59.99, Hardware.Type.STORAGE,4)
    );*/

    /*private final List<Hardware> MOCKED_HARDWARE =
            new ArrayList<>(Arrays.asList(
                    new Hardware("RTX 3060","111",333.70, Hardware.Type.GPU, 10),
                    new Hardware("Ryzen 7 5600X","222",420, Hardware.Type.CPU,54),
                    new Hardware("Samsung EVO 970","333",59.99, Hardware.Type.STORAGE,4)));*/

    private final List<Hardware> MOCKED_HARDWARE =
            new ArrayList<>();


    @Override
    public List<Hardware> findAll() {
        return MOCKED_HARDWARE;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {

        return MOCKED_HARDWARE.stream().filter(o -> Objects.equals(o.getCode(),code)).findAny();
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        if(!MOCKED_HARDWARE.contains(hardware)){
            MOCKED_HARDWARE.add(hardware);
            return Optional.of(hardware);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> update(String code, Hardware hardware) {
        boolean exists = MOCKED_HARDWARE.removeIf(
                it -> Objects.equals(it.getCode(), code) && Objects.equals(it.getCode(), hardware.getCode())
        );

        if(exists){
            MOCKED_HARDWARE.add(hardware);
            return Optional.of(hardware);
        } else {
            return Optional.empty();
        }
    }


    @Override
    public void deleteByCode(String code) {
        MOCKED_HARDWARE.removeIf(it -> Objects.equals(it.getCode(), code));
    }


}
