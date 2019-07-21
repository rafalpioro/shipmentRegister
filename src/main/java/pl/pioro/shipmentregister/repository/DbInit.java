package pl.pioro.shipmentregister.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pioro.shipmentregister.entity.*;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private IncotermsRepository incotermsRepository;

    @Override
    public void run(String... args) {
        //create roles
        Role admin = new Role();
        admin.setName("ROLE_ADMIN");
        Role user = new Role();
        user.setName("ROLE_USER");
        Role viewer = new Role();
        viewer.setName("ROLE_VIEWER");
        roleRepository.save(admin);
        roleRepository.save(user);
        roleRepository.save(viewer);

        //create project Status
        ProjectStatus delivered = new ProjectStatus();
        delivered.setName("delivered");
        projectStatusRepository.save(delivered);

        //create transaction type
        TransactionType export = new TransactionType();
        export.setName("export");
        transactionTypeRepository.save(export);

        //create incoterms
        Incoterms fca = new Incoterms();
        fca.setName("FCA");
        incotermsRepository.save(fca);

        Incoterms fob = new Incoterms();
        fob.setName("FOB");
        incotermsRepository.save(fob);

        //create users
        User rafal = new User();
        rafal.setName("admin");
        rafal.setEmail("admin@gmail.com");
        rafal.setPassword(passwordEncoder.encode("admin"));
        rafal.setRole(admin);
        rafal.setActive(true);

        User magda = new User();
        magda.setName("user");
        magda.setEmail("user@gmail.com");
        magda.setPassword(passwordEncoder.encode("user"));
        magda.setRole(user);
        magda.setActive(true);

        User tomek = new User();
        tomek.setName("viewer");
        tomek.setEmail("viewer@gmail.com");
        tomek.setPassword(passwordEncoder.encode("viewer"));
        tomek.setRole(viewer);
        tomek.setActive(true);

        //add users to db
        this.userRepository.save(rafal);
        this.userRepository.save(magda);
        this.userRepository.save(tomek);

        //add Countries
        Country afghanistan = new Country();
        afghanistan.setName("Afghanistan");
        afghanistan.setCode("AF");
        this.countryRepository.save(afghanistan);

        Country albania = new Country("Albania", "AL");
        this.countryRepository.save(albania);

        Country algeria = new Country("Algeria", "DZ");
        this.countryRepository.save(algeria);

        Country andorra = new Country("Andorra","AD" );
        this.countryRepository.save(andorra);

        Country angola = new Country("Angola","AO" );
        this.countryRepository.save(angola);

        Country saudiArabia = new Country("Saudi Arabia","SA" );
        this.countryRepository.save(saudiArabia);

        Country argentina = new Country("Argentina","AR" );
        this.countryRepository.save(argentina);

        Country armenia = new Country("Armenia","AM" );
        this.countryRepository.save(armenia);

        Country australia = new Country("Australia","AU" );
        this.countryRepository.save(australia);

        Country austria = new Country("Austria","AT" );
        this.countryRepository.save(austria);

        Country azerbaijan = new Country("Azerbaijan","AZ" );
        this.countryRepository.save(azerbaijan);

        Country bangladesh = new Country("Bangladesh","BD" );
        this.countryRepository.save(bangladesh);

        Country belgium = new Country("Belgium","BE" );
        this.countryRepository.save(belgium);

        Country belarus = new Country("Belarus","BY" );
        this.countryRepository.save(belarus);

        Country bolivia = new Country("Bolivia","BO" );
        this.countryRepository.save(bolivia);

        Country brazil = new Country("Brazil","BR" );
        this.countryRepository.save(brazil);

        Country bulgaria = new Country("Bulgaria","BG" );
        this.countryRepository.save(bulgaria);

        Country chile = new Country("Chile","CL" );
        this.countryRepository.save(chile);

        Country china = new Country("China","CN" );
        this.countryRepository.save(china);

        Country croatia = new Country("Croatia","HR" );
        this.countryRepository.save(croatia);

        Country denmark = new Country("Denmark","DK" );
        this.countryRepository.save(denmark);

        Country egypt = new Country("Egypt","EG" );
        this.countryRepository.save(egypt);

        Country ecuador = new Country("Ecuador","EC" );
        this.countryRepository.save(ecuador);

        Country estonia = new Country("Estonia","EE" );
        this.countryRepository.save(estonia);

        Country philippines = new Country("Philippines","PH" );
        this.countryRepository.save(philippines);

        Country finland = new Country("Finland","FI" );
        this.countryRepository.save(finland);

        Country france = new Country("France","FR" );
        this.countryRepository.save(france);

        Country ghana = new Country("Ghana","GH" );
        this.countryRepository.save(ghana);

        Country greece = new Country("Greece","GR" );
        this.countryRepository.save(greece);

        Country georgia = new Country("Georgia","GE" );
        this.countryRepository.save(georgia);

        Country spain = new Country("Spain","ES" );
        this.countryRepository.save(spain);

        Country honduras = new Country("Honduras","HN" );
        this.countryRepository.save(honduras);

        Country india = new Country("India","IN" );
        this.countryRepository.save(india);

        Country indonesia = new Country("Indonesia","ID" );
        this.countryRepository.save(indonesia);

        Country ireland = new Country("Ireland","IE" );
        this.countryRepository.save(ireland);

        Country israel = new Country("Israel","IL" );
        this.countryRepository.save(israel);

        Country japan = new Country("Japan","JP" );
        this.countryRepository.save(japan);

        Country colombia = new Country("Colombia","CO" );
        this.countryRepository.save(colombia);

        Country kuwait = new Country("Kuwait","KW" );
        this.countryRepository.save(kuwait);

        Country lithuania = new Country("Lithuania","LT" );
        this.countryRepository.save(lithuania);

        Country latvia = new Country("Latvia","LV" );
        this.countryRepository.save(latvia);

        Country malaysia = new Country("Malaysia","MY" );
        this.countryRepository.save(malaysia);

        Country malta = new Country("Malta","MT" );
        this.countryRepository.save(malta);

        Country mexico = new Country("Mexico","MX" );
        this.countryRepository.save(mexico);

        Country netherlands = new Country("Netherlands","NL" );
        this.countryRepository.save(netherlands);

        Country germany = new Country("Germany","DE" );
        this.countryRepository.save(germany);

        Country nigeria = new Country("Nigeria","NG" );
        this.countryRepository.save(nigeria);

        Country norway = new Country("Norway","NO" );
        this.countryRepository.save(norway);

        Country paraguay = new Country("Paraguay","PY" );
        this.countryRepository.save(paraguay);

        Country peru = new Country("Peru","PE" );
        this.countryRepository.save(peru);

        Country poland = new Country("Poland","PL" );
        this.countryRepository.save(poland);

        Country portugal = new Country("Portugal","PT" );
        this.countryRepository.save(portugal);

        Country czechRepublic = new Country("Czech Republic","CZ" );
        this.countryRepository.save(czechRepublic);

        Country russianFederation = new Country("Russian Federation","RU" );
        this.countryRepository.save(russianFederation);

        Country romania = new Country("Romania","RO" );
        this.countryRepository.save(romania);

        Country senegal = new Country("Senegal","SN" );
        this.countryRepository.save(senegal);

        Country slovakia = new Country("Slovakia","SK" );
        this.countryRepository.save(slovakia);

        Country usa = new Country("USA","US" );
        this.countryRepository.save(usa);

        Country switzerland = new Country("Switzerland","CH" );
        this.countryRepository.save(switzerland);

        Country sweden = new Country("Sweden","SE" );
        this.countryRepository.save(sweden);

        Country thailand = new Country("Thailand","TH" );
        this.countryRepository.save(thailand);

        Country tunisia = new Country("Tunisia","TN" );
        this.countryRepository.save(tunisia);

        Country turkey = new Country("Turkey","TR" );
        this.countryRepository.save(turkey);

        Country ukraine = new Country("Ukraine","UA" );
        this.countryRepository.save(ukraine);

        Country uzbekistan = new Country("Uzbekistan","UZ" );
        this.countryRepository.save(uzbekistan);

        Country venezuela = new Country("Venezuela","VE" );
        this.countryRepository.save(venezuela);

        Country hungary = new Country("Hungary","HU" );
        this.countryRepository.save(hungary);

        Country italy = new Country("Italy","IT" );
        this.countryRepository.save(italy);

        Country zambia = new Country("Zambia","ZM" );
        this.countryRepository.save(zambia);

        Country unitedArabEmirates = new Country("United Arab Emirates","AE" );
        this.countryRepository.save(unitedArabEmirates);

        //create branch
        Branch headquarter = new Branch();
        headquarter.setName("Headquarter");
        headquarter.setAddress("Main Street");
        headquarter.setZipCode("80-337");
        headquarter.setCity("Gdańsk");
        headquarter.setCountry(countryRepository.findById(12L));
        branchRepository.save(headquarter);
    }
}
