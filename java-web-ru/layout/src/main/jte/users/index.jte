@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
    <div>
        <table>
            <caption>Пользователи</caption>
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            </thead>
            <tbody>
            @for(var user: page.getUsers())
                <tr>
                    <td><a href="/users/${user.getId()}">${user.getId()}</a></td>
                    <td>${user.getFirstName()}</td>
                    <td>${user.getLastName()}</td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
    `,
    footer = @`
        <p>Thanks for visiting, come again soon!</p>
    `
)
