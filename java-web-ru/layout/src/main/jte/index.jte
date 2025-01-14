@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <p><a href="/users">Просмотр всех пользователей</a></p>
`,
greet = @`
    Приветствие
`)
