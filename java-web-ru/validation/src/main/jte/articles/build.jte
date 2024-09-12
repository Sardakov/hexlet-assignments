@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
content = @`
    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <div class="mx-auto p-4 py-md-5">
        <form action="/articles" method="post">
            <div class="mb-3">
                <label class="form-label">
                    Название
                    <input type="text" class="form-control" name="title" value="${page.getTitle()}" />
                </label>
            </div>
            <div class="mb-3">
                <label class="form-label">Содержание</label>
                <textarea class="form-control" rows="20" cols="70" type="text" name="content">${page.getContent()}</textarea>
            </div>
            <input type="submit" class="btn btn-primary" value="Сохранить" />
        </form>
    </div>
`
)
