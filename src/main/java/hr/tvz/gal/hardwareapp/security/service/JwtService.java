package hr.tvz.gal.hardwareapp.security.service;

import hr.tvz.gal.hardwareapp.security.domain.User;


public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
