package edu.uoc.epcsd.unitTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.Status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShowUnitTest {
    @Test
    void get_cancel_status_when_order_is_cancelled(){
        //GIVEN
        Show show = new Show();
        
        //WHEN
        show.cancel();
        
        //THEN
        assertThat(show.getStatus()).isEqualTo(Status.CANCELLED);
    }

}
