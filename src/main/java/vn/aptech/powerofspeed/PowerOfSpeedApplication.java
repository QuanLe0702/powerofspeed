package vn.aptech.powerofspeed;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
// import vn.aptech.powerofspeed.util.RandomStringUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import vn.aptech.powerofspeed.model.user.Address;
import vn.aptech.powerofspeed.model.user.Role;
import vn.aptech.powerofspeed.model.user.User;
// import vn.aptech.powerofspeed.repository.category.CategoryRepository;
// import vn.aptech.powerofspeed.repository.images.ImagesRepository;
// import vn.aptech.powerofspeed.repository.product.ProductRepository;
// import vn.aptech.powerofspeed.repository.subcategory.SubcategoryRepository;
import vn.aptech.powerofspeed.repository.user.AddressRepository;
import vn.aptech.powerofspeed.repository.user.RoleRepository;
import vn.aptech.powerofspeed.repository.user.UserRepository;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PowerOfSpeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerOfSpeedApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository,
            RoleRepository roleRepository,
            AddressRepository addressRepository) {

        // CategoryRepository categoryRepository,
        // SubcategoryRepository subcategoryRepository,
        // ProductRepository productRepository,
        // ImagesRepository imagesRepository) {

        return args -> {

            Role roleAdmin = roleRepository.findByName("ADMIN");
            if (roleAdmin == null) {
                roleAdmin = new Role();
                roleAdmin.setName("ADMIN");
                roleRepository.save(roleAdmin);
            }

            Role roleStaff = roleRepository.findByName("STAFF");
            if (roleStaff == null) {
                roleStaff = new Role();
                roleStaff.setName("STAFF");
                roleRepository.save(roleStaff);
            }

            Role roleCustomer = roleRepository.findByName("CUSTOMER");
            if (roleCustomer == null) {
                roleCustomer = new Role();
                roleCustomer.setName("CUSTOMER");
                roleRepository.save(roleCustomer);
            }

            User admin = userRepository.findByEmail("admin@amazingtoy.com");
            if (admin == null) {
                admin = new User();
                admin.setEmail("admin@amazingtoy.com");
                admin.setEmailConfirmed(true);
                admin.setPassword(passwordEncoder.encode("123456"));
                admin.setPhoneNumber("0123456789");
                admin.setPhoneConfirmed(true);
                admin.setFirstName("Trinh Hao");
                admin.setLastName("Hiep");
                admin.setGender(User.GenderType.Male);
                admin.setDateOfBirth(Date.valueOf("1999-07-26"));
                admin.setProfilePicture("avatar.png");
                admin.setStatus(true);

                Address adminAddress = new Address();
                adminAddress.setCountry("Vietnam");
                adminAddress.setAddress("Block A EHomse S Nam Sai Gon, Binh Hung");
                adminAddress.setCity("Ho Chi Minh");
                adminAddress.setStateOrRegion("Binh Chanh");
                adminAddress.setPostalCode("80000");
                admin.setAddress(addressRepository.save(adminAddress));

                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(roleAdmin);
                adminRoles.add(roleStaff);
                admin.setRoles(adminRoles);

                userRepository.save(admin);

            }

            User staff = userRepository.findByEmail("staff@amazingtoy.com");
            if (staff == null) {
                staff = new User();
                staff.setEmail("staff@amazingtoy.com");
                staff.setEmailConfirmed(true);
                staff.setPassword(passwordEncoder.encode("123456"));
                staff.setPhoneNumber("0123456789");
                staff.setPhoneConfirmed(true);
                staff.setFirstName("Nguyen Nhat");
                staff.setLastName("Hoang Ha");
                staff.setGender(User.GenderType.Male);
                staff.setDateOfBirth(Date.valueOf("1999-07-26"));
                staff.setProfilePicture("avatar2.png");
                staff.setStatus(true);

                Address staffAddress = new Address();
                staffAddress.setCountry("Vietnam");
                staffAddress.setAddress("343 Pham Ngu Lao");
                staffAddress.setCity("Ho Chi Minh");
                staffAddress.setStateOrRegion("Quan 1");
                staffAddress.setPostalCode("80000");
                staff.setAddress(addressRepository.save(staffAddress));

                Set<Role> staffRoles = new HashSet<>();
                staffRoles.add(roleStaff);
                staff.setRoles(staffRoles);

                userRepository.save(staff);

            }

            User customer = userRepository.findByEmail("customer@amazingtoy.com");
            if (customer == null) {
                customer = new User();
                customer.setEmail("customer@amazingtoy.com");
                customer.setEmailConfirmed(true);
                customer.setPassword(passwordEncoder.encode("123456"));
                customer.setPhoneNumber("0123456789");
                customer.setPhoneConfirmed(true);
                customer.setFirstName("To Dao");
                customer.setLastName("Viet Hoang");
                customer.setGender(User.GenderType.Male);
                customer.setDateOfBirth(Date.valueOf("1999-07-26"));
                customer.setProfilePicture("avatar3.png");
                customer.setStatus(true);

                Address customerAddress = new Address();
                customerAddress.setCountry("Vietnam");
                customerAddress.setAddress("343 Pham Ngu Lao");
                customerAddress.setCity("Ho Chi Minh");
                customerAddress.setStateOrRegion("Quan 1");
                customerAddress.setPostalCode("80000");
                customer.setAddress(addressRepository.save(customerAddress));

                Set<Role> customerRoles = new HashSet<>();
                customerRoles.add(roleCustomer);
                customer.setRoles(customerRoles);

                userRepository.save(customer);

            }

            // List<Category> categoryList = new ArrayList<>();
            //
            // categoryList.add(new
            // Category().setName("Airplane").setSlug(RandomStringUtil.makeSlug("Airplane")));
            //
            // categoryList.add(new
            // Category().setName("Car").setSlug(RandomStringUtil.makeSlug("Car")));
            //
            // categoryList.add(new
            // Category().setName("MotorBike").setSlug(RandomStringUtil.makeSlug("MotorBike")));
            //
            // categoryList.add(new
            // Category().setName("Truck").setSlug(RandomStringUtil.makeSlug("Truck")));
            //
            // categoryRepository.saveAll(categoryList);
            //
            // List<Subcategory> subcategories = new ArrayList<>();
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Boeing 747")
            // .setSlug("Boeing 747")
            // .setCategory(categoryRepository.findBySlug("airplane"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Mic 17")
            // .setSlug("Mic 17")
            // .setCategory(categoryRepository.findBySlug("airplane"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Airbus A320")
            // .setSlug("Airbus A320")
            // .setCategory(categoryRepository.findBySlug("airplane"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("BMW")
            // .setSlug("BMW")
            // .setCategory(categoryRepository.findBySlug("car"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Mercedes Benz")
            // .setSlug("Mercedes Benz")
            // .setCategory(categoryRepository.findBySlug("car"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Toyota")
            // .setSlug("Toyota")
            // .setCategory(categoryRepository.findBySlug("car"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Harley Davidson")
            // .setSlug("Harley Davidson")
            // .setCategory(categoryRepository.findBySlug("motorbike"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Ducati")
            // .setSlug("Ducati")
            // .setCategory(categoryRepository.findBySlug("motorbike"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Kawasaki")
            // .setSlug("Kawasaki")
            // .setCategory(categoryRepository.findBySlug("motorbike"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Iveco")
            // .setSlug("Iveco")
            // .setCategory(categoryRepository.findBySlug("truck"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Scania")
            // .setSlug("Scania")
            // .setCategory(categoryRepository.findBySlug("truck"))
            // );
            //
            // subcategories.add(new Subcategory()
            // .setSubName("Hyundai")
            // .setSlug("Hyundai")
            // .setCategory(categoryRepository.findBySlug("truck"))
            // );
            //
            // subcategoryRepository.saveAll(subcategories);
        };
    }

}
