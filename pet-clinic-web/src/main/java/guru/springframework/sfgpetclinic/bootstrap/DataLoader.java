package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader  implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Praveen");
        owner1.setLastName("Kuntumalla");
        owner1.setAddress("123 Brandywine Road");
        owner1.setCity("Malvern");
        owner1.setTelephone("484-484-484");
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

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Kalpana");
        vet1.setLastName("Vallabhaneni");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Anil");
        vet2.setLastName("Vallabhaneni");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
