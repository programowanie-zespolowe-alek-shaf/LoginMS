package pl.agh.login.application.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Repository;
import pl.agh.login.application.entity.AppUser;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private static final String USER_STATEMENT = "select username, password, enabled from customer.user where username = ?";
    private static final String ROLE_STATEMENT = "select role from customer.user_roles where username = ?";

    private final DataSource dataSource;

    public AppUser getUser(String username) {

        AppUser user = new AppUser();

        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(USER_STATEMENT)) {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user.setUsername(resultSet.getString(1));
                    user.setPassword(resultSet.getString(2));
                    user.setEnabled(resultSet.getBoolean(3));
                }
            }

            if (user.getUsername() != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(ROLE_STATEMENT)) {
                    preparedStatement.setString(1, username);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    HashSet<String> roles = new HashSet<>();
                    while (resultSet.next()) {
                        roles.add(resultSet.getString(1));
                    }
                    user.setRoles(roles);
                }
            }
        } catch (SQLException sqlException) {
            throw new InternalAuthenticationServiceException("DB problem", sqlException);
        }
        return user;
    }
}
