@import exercise.dto.users.UsersPage
@param UsersPage users

<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        <div>
            <table>
                <caption>Пользователи</caption>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                    </tr>
                </thead>
                    <tbody>
                    @for(var user: users.getUsers())
                    <tr>
                        <td><a href="/users/${user.getId()}">${user.getId()}</a></td>
                        <td>${user.getFirstName()}</td>
                        <td>${user.getLastName()}</td>
                        <td>${user.getEmail()}</td>
                    </tr>
                    @endfor
                    </tbody>
            </table>
        </div>
    </body>
</html>
