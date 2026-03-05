<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Résultat</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6fb;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 900px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }
        .error {
            color: red;
            font-weight: bold;
            text-align: center;
        }
        .actions {
            margin-bottom: 15px;
        }
        .actions a {
            display: inline-block;
            margin-right: 10px;
            padding: 10px 15px;
            border-radius: 6px;
            text-decoration: none;
            color: white;
            font-weight: bold;
        }
        .download { background: #4CAF50; }
        .whatsapp { background: #25D366; }
        .telegram { background: #0088cc; }
        iframe {
            width: 100%;
            height: 600px;
            border: 1px solid #ddd;
            border-radius: 6px;
        }
    </style>
</head>
<body>

<div class="container">

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <c:if test="${empty error}">
        <div class="actions">
            <a class="download" href="${resultFile}">? Télécharger</a>

            <!-- Partage WhatsApp -->
            <a class="whatsapp"
               href="https://wa.me/?text=${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${resultFile}"
               target="_blank">
                WhatsApp
            </a>

            <!-- Partage Telegram -->
            <a class="telegram"
               href="https://t.me/share/url?url=${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${resultFile}"
               target="_blank">
                Telegram
            </a>
        </div>

        <iframe src="${preview}"></iframe>
    </c:if>

</div>

</body>
</html>