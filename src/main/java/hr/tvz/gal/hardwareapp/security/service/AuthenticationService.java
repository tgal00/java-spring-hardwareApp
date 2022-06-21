package hr.tvz.gal.hardwareapp.security.service;

import hr.tvz.gal.hardwareapp.security.command.LoginCommand;
import hr.tvz.gal.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
