import Controller.MainController;
import Entity.Cat;
import static org.mockito.Mockito.*;

import Entity.Owner;
import Services.CatService;
import Services.OwnerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CatTestMock {
    @Test
    public void CreateOwnerAddCat() {
        OwnerService ownerService = mock(OwnerService.class);
        CatService catService = mock(CatService.class);
        MainController controller = new MainController(ownerService, catService);

        Owner expectedOwner = new Owner("John", LocalDate.of(1990, 1, 1));
        when(ownerService.createOwner("John", LocalDate.of(1990, 1, 1))).thenReturn(expectedOwner);
        Owner resultOwner = controller.createOwner("John", LocalDate.of(1990, 1, 1));
        verify(ownerService, times(1)).createOwner("John", LocalDate.of(1990, 1, 1));

        Cat expectedCat = new Cat("barsik", LocalDate.of(2015, 1, 13), "Siamsky", Cat.ColorType.white, expectedOwner);
        when(catService.createCat("barsik", LocalDate.of(2015, 1, 13), "Siamsky", Cat.ColorType.white, expectedOwner)).thenReturn(expectedCat);
        Cat resultCat = controller.createCat("barsik", LocalDate.of(2015, 1, 13), "Siamsky", Cat.ColorType.white, expectedOwner);
        verify(catService, times(1)).createCat("barsik", LocalDate.of(2015, 1, 13), "Siamsky", Cat.ColorType.white, expectedOwner);

        Assertions.assertEquals(expectedOwner, resultOwner);
        Assertions.assertEquals(expectedCat, resultCat);

    }
}
