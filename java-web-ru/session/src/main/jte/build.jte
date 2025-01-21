@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(content = @`
    @if(page.getError() != null)
        <div class="-details">
            <p>${page.getError()}</p>
        </div>
    @endif
    <form action="${NamedRoutes.loginPath()}" method="post">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Имя</label>
            <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="name" value="${page.getName()}">
            <div id="emailHelp" class="form-text">Мы никогда никому не передадим ваши данные.</div>
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Пароль</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Залогиниться</button>
    </form>
`)