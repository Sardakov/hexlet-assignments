@import exercise.dto.users.UserPage
@param UserPage page

<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        <main>
        <h2>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h2>
        <div>${page.getUser().getEmail()}</div>
      </main>
    </body>
</html>
