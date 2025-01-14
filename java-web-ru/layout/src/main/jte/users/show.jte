@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`
    <table class="user-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${page.getUser().getId()}</td>
            <td>${page.getUser().getFirstName()}</td>
            <td>${page.getUser().getLastName()}</td>
            <td>${page.getUser().getEmail()}</td>
        </tr>
        </tbody>
    </table>
    <p><a href="/users">Показать всех пользователей</a></p>
`)
