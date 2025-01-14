@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(content = @`
    <form action="/users" method="get">
        <input type="search" name="term" value="${page.getTerm()}">
        <input type="submit" value="Search">
    </form>
    @if(!page.getUsers().isEmpty())
        <ol>
    @for(var user : page.getUsers())
        <li><a href="/users/${user.getId()}">${user.getFirstName() + " " + user.getLastName()}</a></li>
    @endfor
        </ol>
    @endif
`)
