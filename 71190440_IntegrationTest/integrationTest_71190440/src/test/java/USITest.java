import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class USITest {
    private UserDAO userDAO= mock(UserDAO.class);
    private User user = mock(User.class);
    private SecurityService securityService = mock(SecurityService.class);

    @Test
    public void testUpdateUser(){
        userDAO.updateUser(user);
        verify(userDAO).updateUser(user);
    }

    @Test
    public void testAssignPassword() throws Exception{
        UserServiceImpl usi = new UserServiceImpl(userDAO, securityService);
        usi.assignPassword(user);
        verify(user).getPassword();
    }



}
