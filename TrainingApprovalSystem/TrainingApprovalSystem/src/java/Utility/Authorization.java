package utility;

import factory.UserFactory;
import model.User;

import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class Authorization {

    /**
     * EL : get user : ${session['auth.user']}
     */

    private HttpSession session;
    private Connection connection;

    public Authorization(Connection connection, HttpSession session) {
        this.session = session;
        this.connection = connection;
    }

    public boolean doLogin(String username, String password) {
        try {
            User user = new UserFactory(connection).findByUsername(username);

            if(user != null) {
                if(user.getPassword().equals(password)) {
                    session.setAttribute("auth.user", user);
                    return true;
                }
            }
        } catch (RuntimeException ignored) { }
        return false;
    }

    public void doLogout() {
        session.setAttribute("auth.user", null);
    }

    public boolean isLogin() {
        return session.getAttribute("auth.user") != null;
    }

    public User getCurrentUser() {
        return (User) session.getAttribute("auth.user");
    }

}
