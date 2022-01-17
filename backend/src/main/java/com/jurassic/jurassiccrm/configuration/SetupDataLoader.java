package com.jurassic.jurassiccrm.configuration;

import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.GroupService;
import com.jurassic.jurassiccrm.accesscontroll.service.UserService;
import com.jurassic.jurassiccrm.aviary.dao.AviaryTypeRepository;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.decoration.dao.DecorationTypeRepository;
import com.jurassic.jurassiccrm.decoration.model.DecorationType;
import com.jurassic.jurassiccrm.dinosaur.dao.DinosaurTypeRepository;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.research.dao.ResearchRepository;
import com.jurassic.jurassiccrm.research.model.Research;
import com.jurassic.jurassiccrm.wiki.entity.Wiki;
import com.jurassic.jurassiccrm.wiki.repository.WikiRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Transactional
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private volatile boolean alreadySetup = false;

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupService groupService;
    private final WikiRepository wikiRepository;
    private final AviaryTypeRepository aviaryTypeRepository;
    private final DinosaurTypeRepository dinosaurTypeRepository;
    private final DecorationTypeRepository decorationTypeRepository;
    private final ResearchRepository researchRepository;

    public SetupDataLoader(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder, GroupService groupService, WikiRepository wikiRepository, AviaryTypeRepository aviaryTypeRepository, DinosaurTypeRepository dinosaurTypeRepository, DecorationTypeRepository decorationTypeRepository, ResearchRepository researchRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.groupService = groupService;
        this.wikiRepository = wikiRepository;
        this.aviaryTypeRepository = aviaryTypeRepository;
        this.dinosaurTypeRepository = dinosaurTypeRepository;
        this.decorationTypeRepository = decorationTypeRepository;
        this.researchRepository = researchRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<User> users = userRepository.findAll();
        if (users.size() > 0) {
            alreadySetup = true;
        }

        if (alreadySetup) return;

        Group research = createGroup("Research",
                new HashSet<>(Arrays.asList(
                        Role.RESEARCH_TASK_READER, Role.RESEARCH_TASK_WRITER,
                        Role.RESEARCH_DATA_READER, Role.RESEARCH_DATA_WRITER,
                        Role.TECHNOLOGICAL_MAP_READER, Role.TECHNOLOGICAL_MAP_WRITER)));

        Group incubation = createGroup("Incubation",
                new HashSet<>(Arrays.asList(
                        Role.TECHNOLOGICAL_MAP_READER,
                        Role.INCUBATION_TASK_READER, Role.INCUBATION_TASK_WRITER,
                        Role.DINOSAUR_PASSPORT_READER, Role.DINOSAUR_PASSPORT_WRITER
                )));

        Group security = createGroup("Security",
                new HashSet<>(Arrays.asList(
                        Role.SECURITY_READER, Role.SECURITY_WRITER
                )));

        Group administration = createGroup("Administration",
                new HashSet<>(Arrays.asList(
                        Role.values()
                )));

        Group maintenance = createGroup("Maintenance",
                new HashSet<>(Arrays.asList(
                        Role.THEME_ZONE_PROJECT_READER,
                        Role.AVIARY_BUILDING_TASK_READER, Role.AVIARY_BUILDING_TASK_WRITER,
                        Role.AVIARY_PASSPORT_READER, Role.AVIARY_PASSPORT_WRITER
                )));

        Group accommodation = createGroup("Accommodation",
                new HashSet<>(Arrays.asList(
                        Role.THEME_ZONE_PROJECT_READER,
                        Role.DINOSAUR_PASSPORT_READER, Role.DINOSAUR_PASSPORT_WRITER
                )));

        createUser("test-research", "research", "Test", "Research", research);
        createUser("test-incubation", "incubation", "Test", "Incubation", incubation);
        createUser("test-security", "security", "Test", "Security", security);
        createUser("test-maintenance", "maintenance", "Test", "Maintenance", maintenance);
        createUser("test-accommodation", "accommodation", "Test", "Accommodation", accommodation);
        createUser("admin", "admin", "admin", "admin", administration);

        List<User> dummies = createNDummies(10);
        Set<Role> rolesForDummies = new HashSet<>();
        rolesForDummies.add(Role.TASK_READER);
        Group group = new Group();
        group.setName("Dummies Group");
        group.setUsers(new HashSet<>(dummies));
        group.setRoles(rolesForDummies);
        groupService.createGroup(group);
        alreadySetup = true;

        Wiki wiki1 = new Wiki();
        wiki1.setTitle("Tyrannosaurus");
        wiki1.setText("Tyrannosaurus is a genus of tyrannosaurid theropod dinosaur. The species Tyrannosaurus rex (rex meaning \"king\" in Latin), often called T. rex or colloquially T-Rex, is one of the best represented of these large theropods. Tyrannosaurus lived throughout what is now western North America, on what was then an island continent known as Laramidia. Tyrannosaurus had a much wider range than other tyrannosaurids. Fossils are found in a variety of rock formations dating to the Maastrichtian age of the Upper Cretaceous period, 68 to 66 million years ago. It was the last known member of the tyrannosaurids and among the last non-avian dinosaurs to exist before the Cretaceous–Paleogene extinction event.");
        try {
            URL url = new URL("http://andrey-eltsov.ru/wp-content/uploads/2019/04/XxXx-XX123-lJuO_4gfqQ-s_4hnSuJ_gdt43sdYH-d_G-F-Y-k_341-afR-Тираннозавр-фотоголубой2.jpg");
            InputStream is = url.openStream();
            byte[] bytes = IOUtils.toByteArray(is);
            wiki1.setImage(bytes);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wikiRepository.save(wiki1);

        researchRepository.save(new Research("Default research"));

        aviaryTypeRepository.save(new AviaryType("Open aviary"));
        decorationTypeRepository.save(new DecorationType("Palm tree"));
        dinosaurTypeRepository.save(new DinosaurType("Tyrannosaurus"));
    }

    private User createUser(String username, String password, String firstName, String lastName, Group group) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setGroups(new HashSet<>(Collections.singletonList(group)));

        return userService.createUser(newUser);
    }

    private Group createGroup(String name, Set<Role> roles) {
        Group group = new Group();
        group.setName(name);
        group.setRoles(roles);
        return groupService.createGroup(group);
    }

    private List<User> createNDummies(int N) {
        return IntStream.rangeClosed(1, N)
                .mapToObj(this::createDummyUser)
                .collect(Collectors.toList());
    }

    private User createDummyUser(int number) {
        User usr = new User();

        usr.setUsername("dummy" + number);
        usr.setPassword(passwordEncoder.encode("dummy"));
        usr.setFirstName("Dummy");
        usr.setLastName(String.valueOf(number));

        return userService.createUser(usr);
    }

}
