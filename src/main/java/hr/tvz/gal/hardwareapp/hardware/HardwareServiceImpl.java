package hr.tvz.gal.hardwareapp.hardware;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService{

    private HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository repository){

        this.hardwareRepository = repository;
    }

    @Override
    public List<HardwareDTO> findAll() {

        return hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(String code) {

        return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand command) {

        return hardwareRepository.save(mapCommandToHardware(command)).map(this::mapHardwareToDTO);

    }

    @Override
    public Optional<HardwareDTO> update(String code, HardwareCommand command) {

        return hardwareRepository.update(code, mapCommandToHardware(command)).map(this::mapHardwareToDTO);
    }


    @Override
    public void deleteByCode(String code) {
        hardwareRepository.deleteByCode(code);
    }

    private Hardware mapCommandToHardware(HardwareCommand command){

        return new Hardware(command.getId(),command.getName(), command.getCode(),
                command.getPrice(),command.getType(),command.getStock());
    }

    private HardwareDTO mapHardwareToDTO(final Hardware hardware){

        return new HardwareDTO(hardware.getName(),hardware.getPrice(), hardware.getCode(), hardware.getType(), hardware.getStock());
    }

}
