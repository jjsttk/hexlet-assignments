@import exercise.dto.MainPage
@import exercise.util.NamedRoutes

@param MainPage page

@template.layout.page(content = @`
    @if(page.getName() == null)
        <a class="icon-link icon-link-hover" style="--bs-link-hover-color-rgb: 25, 135, 84;" href="${NamedRoutes.buildSessionPath()}">
            Войти
            <svg class="bi" aria-hidden="true"><use xlink:href="#arrow-right"></use></svg>
        </a>
    @else
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <p>${page.getName().toString()}</p>
            <button type="submit" class="btn btn-primary">Выйти</button>
        </form>
        @endif
`)