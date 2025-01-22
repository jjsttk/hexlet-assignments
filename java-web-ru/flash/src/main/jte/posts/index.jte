@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes

@param PostsPage page

@template.layout.page(page = page,
content = @`
    @if(!page.getPosts().isEmpty())
        <ol>
        @for(var post : page.getPosts())
            <li><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></li>
        @endfor
        </ol>
    @endif
`)