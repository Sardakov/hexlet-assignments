@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <h2>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h2>
        <div>${page.getUser().getEmail()}</div>
    `,
    footer = @`
        <p>Thanks for visiting, come again soon!</p>
    `
)


