import org.car_rental.dao.CustomerDAO;
import org.car_rental.domain.Customer;
import org.car_rental.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {

    private static final String NAME = "yaseen";
    private static final String NUMBER = "03242666147";
    private static final String CNIC = "42201";
    private static final String ADDRESS = "Karachi";
    private static final String REFERENCE_PHONE_NUMBER = "0324";

    @Mock
    CustomerDAO customerDAO;
    @InjectMocks
    CustomerService customerService;

    @Test
    public void shouldGetAllData(){

        List<Customer> list = new ArrayList<>();
        list.add(new Customer(1L,"yaseen","032325454","42201","Karachi","03212666147"));
        list.add(new Customer(2L,"hunain","032426665","42032","Quetta","03232222211"));
        list.add(new Customer(3L,"sahil","032429999","42202","Islamabad","03259847351"));
        list.add(new Customer(4L,"Imran","032426612","42203","Lahore","03333234343"));

        when(customerDAO.getAll()).thenReturn(list);

        String[][] allCustomerForJTable = customerService.getAllCustomerForJTable();

        assertNotNull(allCustomerForJTable);

        for(int i=0 ; i < list.size() ; i++){
            assertEquals(list.get(i).getId().toString() , allCustomerForJTable[i][0]);
            assertEquals(list.get(i).getName(), allCustomerForJTable[i][1]);
            assertEquals(list.get(i).getPhoneNumber(), allCustomerForJTable[i][2]);
            assertEquals(list.get(i).getCnic(), allCustomerForJTable[i][3]);
            assertEquals(list.get(i).getAddress(), allCustomerForJTable[i][4]);
            assertEquals(list.get(i).getReferencePhoneNumber(), allCustomerForJTable[i][5]);
        }

        verify(customerDAO).getAll();
    }

    @Test
    public void shouldGetDataAsCustomerListIsEmpty(){
        when(customerDAO.getAll()).thenReturn(Collections.emptyList());

        String[][] allCustomerForJTable = customerService.getAllCustomerForJTable();

        assertNotNull(allCustomerForJTable);
        assertEquals(0,allCustomerForJTable.length);

        verify(customerDAO).getAll();
    }

    @Test
    public void shouldSaveCustomer(){
        Customer customer1 = Customer.builder()
                .name(NAME)
                .phoneNumber(NUMBER)
                .cnic(CNIC)
                .address(ADDRESS)
                .referencePhoneNumber(REFERENCE_PHONE_NUMBER)
                .build();

        doNothing().when(customerDAO).insert(customer1);

        customerService.save(NAME,NUMBER,CNIC,ADDRESS,REFERENCE_PHONE_NUMBER);

        verify(customerDAO).insert(customer1);
    }

    @Test
    public void shouldSetCustomerInactive(){

        doNothing().when(customerDAO).setCustomerInactive(1L);

        customerService.setCustomerInactive(1L);

        verify(customerDAO).setCustomerInactive(1L);
    }

}
