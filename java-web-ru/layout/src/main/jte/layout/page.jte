@import gg.jte.Content

@param Content content
@param Content greet = @``

<!DOCTYPE html>
<html lang="en">
<head>
    <title>jjsttk</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        html, body {
            height: 100%;
            margin: 0;
        }
        h1 {
            font-size: 140%;
        }
        .header {
            margin-left: auto;
            margin-right: auto;

        }
        .container {
            display: flex;
            flex-direction: column;
            min-height: 100%;
        }
        .content {
            flex: 1;
            font-size: 80%;
        }
        footer {
            background-color: white;
            color: black;
            padding: 15px;
        }
        .user-table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 18px;
            text-align: left;
        }


    </style>
</head>
<body>
<div class="container">
<div class="header">
    <header>
        <h1>Hello here, dear user!</h1>
        <p>Nice to meet you... (no)</p>
    </header>
</div>
    @if (!greet.isEmptyContent())
    <div class="greeting">
        <h1>${greet}</h1>
    </div>
    @endif
    <div class="content">
        @if (!content.isEmptyContent())
            ${content}
        @else <p>Nothing to show, content empty</p>
        @endif

</div>
<footer class="container-fluid text-center">
    <p>My copyright in footer:D</p>
</footer>
</div>
</body>
</html>
