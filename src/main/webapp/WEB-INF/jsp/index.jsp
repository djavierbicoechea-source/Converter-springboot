<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Convertisseur de documents</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
        }
        .card {
            background: #fff;
            padding: 25px;
            border-radius: 12px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        input, select, button {
            width: 100%;
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 14px;
        }
        button {
            background: #667eea;
            color: white;
            border: none;
            cursor: pointer;
            font-weight: bold;
        }
        button:hover {
            background: #5563d6;
        }
    </style>
</head>
<body>

<div class="card">
    <h2>Conversion de fichiers</h2>

    <form method="post" action="convert" enctype="multipart/form-data">
        <input type="file" name="file" required/>

        <select name="type" required>
            <option value="pdfToWord">PDF ? Word</option>
            <option value="wordToPdf">Word ? PDF</option>
            <option value="pdfToExcel">PDF ? Excel</option>
        </select>

        <button type="submit">Convertir</button>
    </form>
</div>

</body>
</html>