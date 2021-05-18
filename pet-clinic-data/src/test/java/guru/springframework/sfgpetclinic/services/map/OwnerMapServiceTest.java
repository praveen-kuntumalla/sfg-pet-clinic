package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId1 = 1l;
    final Long ownerId2 = 2l;
    final String ownerLastName1 = "Owner-1";


    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId1).lastName(ownerLastName1).build());
    }

    @Test
    void findAll() {
        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void save() {
        Owner o2 = Owner.builder().id(ownerId2).build();
        Owner savedOwner = ownerMapService.save(o2);
        assertEquals(ownerId2, savedOwner.getId());
    }

    @Test
    void findById() {
        assertEquals(ownerId1, ownerMapService.findById(ownerId1).getId());
    }

    @Test
    void findByLastName() {
        assertEquals(ownerLastName1, ownerMapService.findByLastName(ownerLastName1).getLastName());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId1);
        assertNull(ownerMapService.findById(ownerId1));
    }

    @Test
    void delete() {
        Owner o2 = ownerMapService.findById(ownerId2);
        ownerMapService.delete(o2);
        assertNull(ownerMapService.findById(ownerId2));
    }
}