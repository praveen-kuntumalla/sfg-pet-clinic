package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader  implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() == 0) {
            loadData();
        }
    }

    private void loadData() {
        //PetTypes
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

        //Specialities
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        //Owners & Pets
        Owner owner1 = new Owner();
        owner1.setFirstName("Praveen");
        owner1.setLastName("Kuntumalla");
        owner1.setAddress("123 Brandywine Road");
        owner1.setCity("Malvern");
        owner1.setTelephone("484-484-484");

        //Owner.builder().id

        Pet praveenPet = new Pet();
        praveenPet.setName("Jimmy");
        praveenPet.setPetType(savedDogType);
        praveenPet.setBirthDate(LocalDate.now());
        praveenPet.setOwner(owner1);
        owner1.getPets().add(praveenPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Hruday");
        owner2.setLastName("Kuntumalla");
        owner2.setAddress("456 Brandywine Line");
        owner2.setCity("Malvern");
        owner2.setTelephone("610-610-610");
        Pet hrudayPet = new Pet();
        hrudayPet.setName("Seelu");
        hrudayPet.setPetType(savedCatType);
        hrudayPet.setBirthDate(LocalDate.now());
        hrudayPet.setOwner(owner2);
        owner2.getPets().add(hrudayPet);
        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(hrudayPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        //Vets
        Vet vet1 = new Vet();
        vet1.setFirstName("Kalpana");
        vet1.setLastName("Vallabhaneni");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Anil");
        vet2.setLastName("Vallabhaneni");
        vet2.getSpecialities().add(savedSurgery);
        vet2.getSpecialities().add(savedDentistry);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
