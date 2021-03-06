package it.unicam.morpheus.sogniario.auth;

import com.google.common.collect.Lists;
import it.unicam.morpheus.sogniario.model.Researcher;
import it.unicam.morpheus.sogniario.model.Dreamer;
import it.unicam.morpheus.sogniario.repositories.DreamersRepository;
import it.unicam.morpheus.sogniario.repositories.ResearchersRepository;
import it.unicam.morpheus.sogniario.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The class takes care of recovering users from the appropriate repositories depending on whether they are {@link Researcher} or {@link Dreamer}.
 * In addition, it manages any users residing within the system code.
 */
@Repository("mongoUserDao")
public class MongoUserDaoService implements UserDao{

    private final PasswordEncoder passwordEncoder;
    private final DreamersRepository dreamersRepository;
    private final ResearchersRepository researchersRepository;

    @Autowired
    public MongoUserDaoService(PasswordEncoder passwordEncoder, DreamersRepository dreamersRepository, ResearchersRepository researchersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.dreamersRepository = dreamersRepository;
        this.researchersRepository = researchersRepository;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {

        Optional<User> user;

        if(researchersRepository.findById(username).isPresent())
            user = Optional.of(researchersRepository.findById(username).get());
        else
            if(dreamersRepository.findById(username).isPresent())
                user = Optional.of(dreamersRepository.findById(username).get());
        else
            user = getInMemoryUsers()
                    .stream()
                    .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                    .findFirst();

        return user;
    }

    private List<User> getInMemoryUsers() {

        return Lists.newArrayList(

                new Researcher(
                        "inMemoryAdmin",
                        passwordEncoder.encode("inMemoryAdmin"),
                        UserRole.ADMIN.getGrantedAuthorities(),
                        "In Memory Admin"
                ),

                new Researcher(
                        "guest_dreamer",
                        passwordEncoder.encode("guest_dreamer"),
                        UserRole.GUEST_DREAMER.getGrantedAuthorities(),
                        "Guest Dreamer"
                ),

                new Researcher(
                        "guest_researcher",
                        passwordEncoder.encode("guest_researcher"),
                        UserRole.GUEST_RESEARCHER.getGrantedAuthorities(),
                        "Guest Researcher"
                )
        );
    }
}

