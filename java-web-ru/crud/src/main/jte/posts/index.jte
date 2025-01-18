@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(content = @`
    @if(page.getPosts() != null)
        <h1 class="display-6">Посты</h1>
        <ul class="list-group">
            @for(var post : page.getPosts())
                <li class="list-group-item"><a href="${NamedRoutes.postPath(post.getId())}" >${post.toString()}</a></li>
            @endfor
        </ul>

        <nav aria-label="Page navigation example">
            <ul class="pagination">
                @if(page.getPageNumber() > 1)
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.prevPage(page.getPageNumber())}">Previous</a></li>
                @endif
                <li class="page-item"><a class="page-link" href="${NamedRoutes.nextPage(page.getPageNumber())}">Next</a></li>
            </ul>
        </nav>
    @endif
`)