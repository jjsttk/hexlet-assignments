@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(content = @`
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">name</th>
            <th scope="col">body</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">${page.getPost().getId()}</th>
            <td>${page.getPost().getName()}</td>
            <td>${page.getPost().getBody()}</td>
        </tr>
        </tbody>
    </table>
`)